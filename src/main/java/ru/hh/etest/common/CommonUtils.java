package ru.hh.etest.common;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
  private static final int DOLLAR_EXCHANGE_RATE = 29;

  public static String getUnique() {
    try {
      Thread.sleep(1);
    } catch (InterruptedException ignored) { }

    final String sUnique = String.valueOf(System.currentTimeMillis()) + String.valueOf(Math.round(Math.random() * 1000));
    char[] chars = sUnique.toCharArray();

    for (int i = 0; i < sUnique.length(); i++) {
      chars[i] += 'A' - '0';
    }

    return new String(chars);
  }

  public static String getEmail() {
    return getUnique() + "@" + "test.com";
  }

  public static String getNumber(int length) {
    Random randomGenerator = new Random();
    StringBuilder buf = new StringBuilder();

    for (int i = 0; i < length; i++) {
      buf.append(Integer.toString(randomGenerator.nextInt(10)));
    }

    return buf.toString();
  }

  public static Date getBirthday(Integer age) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.YEAR, -age);

    return cal.getTime();
  }

  public static void sleep(int second) {
    try {
      Thread.sleep(second * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static Integer findLastDigitParameterString(String text, String parameterName) {
    Pattern p = Pattern.compile(".*" + parameterName + "=(\\d+)\\D*");
    Matcher matcher = p.matcher(text);
    matcher.find();

    return Integer.valueOf(matcher.group(1));
  }

  public static Integer findLastDigitParameterInUrl(String url) {
    Pattern p = Pattern.compile(".*=(\\d+)\\D*");
    Matcher matcher = p.matcher(url);
    matcher.find();

    return Integer.valueOf(matcher.group(1));
  }

  public static boolean compare(Object[] expected, Object[] actual) {
    HashSet<Object> set1 = new HashSet<Object>(Arrays.asList(expected));
    HashSet<Object> set2 = new HashSet<Object>(Arrays.asList(actual));
    return set2.containsAll(set1);
  }

  public static String exchangeDollarsToRubles(String dollars) {
    return String.valueOf(Integer.valueOf(dollars) * DOLLAR_EXCHANGE_RATE);
  }
}
