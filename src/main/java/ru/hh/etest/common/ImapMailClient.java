package ru.hh.etest.common;

import java.io.IOException;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class ImapMailClient {
  private final Store store;
  private final Folder folder;

  public ImapMailClient(String server) throws MessagingException {
    store = Session.getDefaultInstance(System.getProperties(), null).getStore("imap");
    store.connect(server + ".pyn.ru", 143, "sink", "sink");
    folder = store.getDefaultFolder().getFolder("inbox");
    folder.open(Folder.READ_WRITE);
  }

  public void close() throws MessagingException {
    folder.close(false);
    store.close();
  }

  public Message[] getLastMessages(int count) throws MessagingException {
    int lastMessageNumber = folder.getMessageCount();

    return folder.getMessages(Math.max(lastMessageNumber - count, 1), lastMessageNumber);
  }

  public int getMessageCount() throws MessagingException {
    return folder.getMessageCount();
  }

  public Message[] getMessageFromXtoLast(int x) throws MessagingException {
    int lastMessageNumber = folder.getMessageCount();

    return folder.getMessages(Math.max(x, 1), lastMessageNumber);
  }

  public String getEmployerPassword(String email) throws MessagingException, IOException {
    for (int i = 0; i < 90; i++) {
      MailBox mailbox = new MailBox(getLastMessages(100));
      String password = mailbox.getPasswordByEmail(email, false);

      if (password != null) {
        return password;
      }

      CommonUtils.sleep(10);
    }

    return null;
  }

  public String getEmployerPassword(String email, boolean isResetPassword) throws MessagingException, IOException,
    InterruptedException {
    for (int i = 0; i < 30; i++) {
      MailBox mailbox = new MailBox(getLastMessages(300));
      String password = mailbox.getPasswordByEmail(email, isResetPassword);

      if (password != null) {
        return password;
      }

      Thread.sleep(10 * 1000);
    }

    return null;
  }

  public void cleanUpMessages() throws MessagingException {
    Message[] msgList = folder.getMessages();

    for (Message message : msgList) {
      message.setFlag(Flags.Flag.DELETED, true);
    }

    folder.close(true);
    folder.open(Folder.READ_WRITE);
  }
}
