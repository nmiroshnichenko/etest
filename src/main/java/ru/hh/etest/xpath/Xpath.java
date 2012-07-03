package ru.hh.etest.xpath;

public class Xpath {
  public static String getInputByLabelSpan(String text) {
    return getLabelSpanString(text) + "//input";
  }

  public static String labelSpanBySpanText(String text) {
    return "//label/span[text()='" + text + "']";
  }

  public static String getTextAreaByTd(String text) {
    return getTdString(text) + "//textarea";
  }

  public static String getTextAreaByLabelSpan(String text) {
    return getLabelSpanString(text) + "//textarea";
  }

  public static String getSelectByLabelSpan(String text) {
    return getLabelSpanString(text) + "//select";
  }

  public static String getInputByTd(String text) {
    return getTdString(text) + "//input";
  }

  public static String getInputByTdSpan(String text) {
    return getTdSpanString(text) + "//input";
  }

  public static String getNumberedInputTd(String text, int position) {
    return getTdString(text) + "//input[" + position + "]";
  }

  public static String getSelectTd(String text) {
    return getTdString(text) + "//select";
  }

  private static String getTdSpanString(String text) {
    return "//tr[td/span[normalize-space(text())='" + text + "']]";
  }

  public static String getLabelSpanString(String text) {
    return "//tr[td/label/span[normalize-space(text())='" + text + "']]";
  }

  public static String getTdLabelSpanString(String text) {
    return "//tr[td/label/span[normalize-space(text()='" + text + "')]]";
  }

  public static String getInputByLabel(String text) {
    return "//label[contains(.,'" + text + "')]/input";
  }

  public static String getSpanByLi(String text) {
    return "//li/span[text()='" + text + "']";
  }

  public static String getInputByLabels(String text) {
    return "//label[contains(.,'" + text + "')]//input";
  }

  public static String getInputByName(String text) {
    return "//input[@name='" + text + "']";
  }

  public static String getInputByValue(String text) {
    return "//input[@value='" + text + "']";
  }

  public static String getTdByClass(String text) {
    return "//td[@class='" + text + "']";
  }

  public static String getInputByClass(String text) {
    return "//input[@class='" + text + "']";
  }

  public static String getInputById(String text) {
    return "//input[@id='" + text + "']";
  }

  public static String getInputByType(String text) {
    return "//input[@type='" + text + "']";
  }

  public static String getDivByClass(String text) {
    return "//div[@class='" + text + "']";
  }

  public static String getSelectByClass(String text) {
    return "//select[@class='" + text + "']";
  }

  public static String getAByClass(String text) {
    return "//a[@class='" + text + "']";
  }

  public static String getMultiSelectInput(String title, String choise) {
    return getTdString(title) + getInputByLabel(choise);
  }

  public static String getMultiSelectInputBySpan(String title, String choise) {
    return getLabelSpanString(title) + getInputByLabel(choise);
  }

  public static String getMultiSelectInputByLabelSpan(String title, String choise) {
    return getTdLabelSpanString(title) + getInputByLabels(choise);
  }

  public static String getTdString(String text) {
    return "//tr[td[normalize-space(text())='" + text + "']]";
  }

  public static String getTdByDivString(String text) {
    return "//tr[td/div[normalize-space(text())='" + text + "']]";
  }

  public static String autosearchVacancySubscribeBlock(String query) {
    return "//div[@class='b-savedsearch-body' and descendant::a[text()='"
    + query + "']]";
  }

  public static String linkResumeBuilderPageByPageNumber(int pageNumber) {
    return "//a[contains(@href,'step" + pageNumber + "')]";
  }

  public static String getTextByA(String text) {
    return "//a[contains(text(),'" + text + "')]";
  }

  public static String getTextBySpan(String text) {
    return "//span[contains(text(),'" + text + "')]";
  }

  public static String getVacancyAutoSearchUseXpathByRequestName(String requestName) {
    return "//a[preceding-sibling::div/span/a[text()='" + requestName + "']]";
  }

  public static String getResumeAutoSearchUseXpathByRequestName(String requestName) {
    return "//div[@class='b-savedsearch-employer-results' and preceding-sibling::div/span/a[contains(text(),'"
    + requestName + "')]]/a";
  }

  public static String getStarAddToFavoriteByTitle(String resumeTitle) {
    return
      "//div[@class='b-userlist-table-favorite b-select-icon b-select-icon-star-off' and contains(following-sibling::div//a[text()],'"
      + resumeTitle + "')]/div";
  }

  public static String getActivatedStarToFavoriteObject(String resumeTitle) {
    return
      "//div[@class='b-userlist-table-favorite b-select-icon b-select-icon-star-on' and contains(following-sibling::div//a[text()],'"
      + resumeTitle + "')]/div";
  }

  public static String getTextByTd(String text) {
    return "//tr[td[contains(text(), '" + text + "')]]";
  }

  public static String getInputWithDiv(String text) {
    return getTextByTd(text) + "//input";
  }

  public static String getNumberedInputWithDiv(String text, int number) {
    return getTextByTd(text) + "//input[" + number + "]";
  }

  public static String getTextareaByTdSpan(String text) {
    return getTdSpanString(text) + "//textarea";
  }

  public static String getSelectByName(String text) {
    return "//select[@name='" + text + "']";
  }

  public static String deleteVacancyAutosearchLinkByTitle(String title) {
    return "//span[preceding-sibling::span/a[contains(text(),'" + title
    + "')]]/a[text()='удалить']";
  }

  public static String inputOfRegistrationfields(String fieldName) {
    return "//div[div[contains(text(),'" + fieldName + "')]]//input";
  }

  public static String clickEditLinkResume(String text) {
    return "//div[div/span[contains(text(),'" + text + "')]]//a";
  }

  public static String inputByParentDivText(String text) {
    return "//div[normalize-space(text())='" + text + "']/input";
  }

  public static String getByClassAndName(String nameClass, String name) {
    return "//*[contains(@class, '" + nameClass + "') and @name='" + name + "']";
  }

  public static String getByClassAndValue(String nameClass, String value) {
    return "//*[contains(@class, '" + nameClass + "') and @value='" + value + "']";
  }

  public static String getByClassAndText(String nameClass, String text) {
    return "//*[@class='" + nameClass + "' and text()='" + text + "']";
  }
}
