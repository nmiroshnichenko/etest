package ru.hh.etest.common;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import org.apache.log4j.Logger;

public class MailBox {
  public static final Logger LOG = Logger.getLogger(MailBox.class);
  private Message[] messages;
  private ImapMailClient client;

  public MailBox(ImapMailClient client) {
    this.client = client;
  }

  public MailBox(Message[] messages) {
    this.messages = messages;
  }

  public String getPasswordByEmail(String email, boolean isResetPassword) throws IOException, MessagingException {
    for (Message message : messages) {
      Object content = message.getContent();
      String contentType = message.getDataHandler().getContentType();

      if (!contentType.contains("text/plain") && !contentType.contains("text/html") && !isResetPassword) {
        continue;
      }

      String address = ((InternetAddress) message.getAllRecipients()[0]).getAddress();

      if (!address.toUpperCase().contains(email.toUpperCase())) {
        continue;
      }

      if (isResetPassword) {
        Multipart multipart = (Multipart) content;
        content = multipart.getBodyPart(0).getContent();
      }

      if (((String) content).contains("пароль")) {
        if (isResetPassword) {
          String resetPasswordPattern = "пароль:\\s+(\\w+)\\s*";
          return parseString((String) content, resetPasswordPattern);
        } else {
          String newPasswordPattern = "(.*) - пароль";
          return parseString((String) content, newPasswordPattern);
        }
      }
    }

    return null;
  }

  private String parseString(String content, String pattern) {
    Pattern mypattern = Pattern.compile(pattern);
    Matcher matcher = mypattern.matcher(content);
    matcher.find();

    return matcher.group(1);
  }

  public Boolean isMailDelivered(Date dateForCheck, String subject, Long offset, String resumeTitle) throws MessagingException,
    IOException {
    LOG.info("offset=" + offset.toString());

    Boolean isDelivered = false;
    Message[] msgList = client.getLastMessages(50);

    for (Message message : msgList) {
      String address = message.getFrom()[0].toString();
      String ADDRESS_FROM = "no_reply@hh.ru";
      LOG.info("condition1 = "
        + (address.toLowerCase().contains(ADDRESS_FROM.toLowerCase())));

      if (address.toLowerCase().contains(ADDRESS_FROM.toLowerCase())) {
        Long sentDate = message.getSentDate().getTime();
        LOG.info("sentDate=" + sentDate.toString());
        LOG.info("dateForCheck=" + dateForCheck.getTime());

        if (
          ((sentDate < (dateForCheck.getTime() + offset + (long) 120000))
            && (sentDate > (dateForCheck.getTime() + offset - (long) 120000))) && message.getSubject().contains(subject)) {
          if ((resumeTitle != null) && !resumeTitle.equals("")) {
            String content = (String) message.getContent();

            if (content.contains(resumeTitle)) {
              isDelivered = true;
            }
          } else {
            isDelivered = true;
          }
        }
      }
    }

    return isDelivered;
  }

  static String parsePassword(String content) {
    Pattern pattern = Pattern.compile("(.*) - пароль");
    Matcher matcher = pattern.matcher(content);
    matcher.find();

    return matcher.group(1);
  }

  public Boolean isMailDelivered(String from, String to, String subject) throws MessagingException, IOException {
    Message[] msgList = client.getLastMessages(100);
    for (Message message : msgList) {
      String addressFrom = message.getFrom()[0].toString();
      String addressTo = message.getRecipients(Message.RecipientType.TO)[0].toString();
      String msgSubject = message.getSubject();
      Object content = message.getContent();

      if (from.equals(addressFrom) && to.equals(addressTo) && msgSubject.contains(subject) && content != null) {
        return true;
      }
    }
    return false;
  }
}
