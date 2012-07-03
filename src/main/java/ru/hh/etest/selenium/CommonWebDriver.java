package ru.hh.etest.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.headhunter.webapp.fixture.UserData;
import com.thoughtworks.selenium.SeleniumException;

public class CommonWebDriver extends RemoteWebDriver {
  public static final int TIME_TO_WAIT = 20000;
  public static final String PREFIX = "http://";
  public static final String BODY_LOCATOR = "//body";
  public static final String VALUE_ATTRIBUTE_NAME = "value";
  public static final String HREF_ATTRIBUTE_NAME = "href";
  public static final String LINK_WITH_PARTICULAR_HREF_XPATH_PATTERN = "//a[@href='%s']";

  private static ThreadLocal<CommonWebDriver> webDriverHolder = new ThreadLocal<CommonWebDriver>();
  private static ThreadLocal<String> loggedInUserName = new ThreadLocal<String>();
  private static ThreadLocal<String> loggedInUserPassword = new ThreadLocal<String>();
  private String mainBrowserWindowHandle;
  private String TEST_HOST;
  private boolean isLogined;
  private String SERVER_NAME;

  {
    manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
  }

  public CommonWebDriver(String server, URL addressOfRemoteServer, Capabilities capabilities) {
    super(addressOfRemoteServer, capabilities);
    TEST_HOST = PREFIX + server + ".pyn.ru";
    SERVER_NAME = server + ".pyn.ru";
    get(TEST_HOST);
  }


  public boolean isLogined() {
    return isLogined;
  }

  public void setLoginStatus(boolean loginStatus) {
    isLogined = loginStatus;
  }

  public void setTestHost(String testHost) {
    TEST_HOST = testHost;
  }

  public String getTestHost() {
    return TEST_HOST;
  }

  public static void safeStopWebDriver(WebDriver driver) {
    if (driver != null) {
       webDriverHolder.remove();
       loggedInUserName.remove();
       loggedInUserPassword.remove();
       driver.close();
    }
    driver = null;
  }

  public void selectOptionByName(String name, String option) {
    selectOption(findElementByName(name), option);
  }

  public void isTextPresent(String text) {
	    waitForElementPresent("/html/body", 10);
	  }

  public void openVacancyPage(String vacancyId) {
    go("/vacancy/" + vacancyId);
  }

  public void reloadPage() {
    get(getCurrentUrl());
  }

  public void submit(String locator) {
    WebElement form = findElementByXPath(locator);
    form.submit();
  }

  public void selectOptionByValue(String name, String option) {
    selectOptionValue(findElementByName(name), option);
  }

  public void click(WebElement element) {
    element.click();
  }

  public void clickAndWait(WebElement element) {
    click(element);
    waitForPageToLoad();
  }

  public void clickLink(String text) {
    link(text).click();
  }

  public void clickLinkAndWait(String text) {
    clickLink(text);
    waitForPageToLoad();
  }

  public void clickLinkByNormalizeText(String text) {
    clickByXpath("//a[normalize-space(text())='" + text + "']");
  }

  public boolean waitForPageToLoad() {
    long started = System.currentTimeMillis();
    long now;
    do {
      Object result = executeScript("return document['readyState'] ? 'complete' == document.readyState : true");

      if ((result != null) && (result instanceof Boolean) && (Boolean) result) {
        return true;
      } else {
        now = System.currentTimeMillis();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
      }
    } while ((now - started) > TIME_TO_WAIT);
    return false;
  }

  public void clickByXpath(String xpath) {
    click(findElementByXPath(xpath));
    waitForPageToLoad();
  }

  public void clickInputByName(String text) {
    click(findElementByName(text));
  }

  public void clickInputByValue(String value) {
    click(findElementByXPath("//input[@value='" + value + "']"));
  }

  public void clickInputByValueAndWait(String value) {
    clickInputByValue(value);
    waitForPageToLoad();
  }

  public void clickLabelByText(String text) {
    click(findElementByXPath("//label[contains(.,'" + text + "')]"));
  }

 

  public void type(WebElement element, String text) {
    element.sendKeys(text);
  }

  public void typeByName(String name, String text) {
    findElementByName(name).clear();
    type(findElementByName(name), text);
  }

  public void typeByXpath(String xpath, String text) {
    findElementByXPath(xpath).clear();
    findElementByXPath(xpath).sendKeys(text);
  }

  public void typeById(String id, String text) {
    WebElement element = findElementById(id);
    element.clear();
    element.sendKeys(text);
  }

  public void selectByXpath(String xpath) {
    findElementByXPath(xpath).click();
  }

  public void selectByName(String name) {
    findElementByName(name).click();
  }

  public void selectOption(WebElement element, String option) {
    List<WebElement> options = element.findElements(By.tagName("option"));

    for (WebElement listElement : options) {
      if (listElement.getText().equals(option)) {
        listElement.click();

        break;
      }
    }
  }

  public void selectOption(String element, String option) {
    selectOption(findElementsByXPath(element).get(0), option);
  }

  public boolean isOptionSelected(String element, String option) {
    List<WebElement> options = findElementsByXPath(element).get(0).findElements(By.tagName("option"));

    for (WebElement listElement : options) {
      if (listElement.getText().equals(option)) {
        return listElement.isSelected();
      }
    }
    return false;
  }

  public String getOptionSelected(String element) {
    List<WebElement> options = findElementsByXPath(element).get(0).findElements(By.tagName("option"));

    for (WebElement listElement : options) {
      if (listElement.isSelected()) {
        return listElement.getText();
      }
    }
    return null;
  }

  public void selectOptionValueByXpath(String path, String option) {
    selectOptionValue(findElementByXPath(path), option);
  }

  public void selectOptionValue(WebElement element, String option) {
    List<WebElement> options = element.findElements(By.tagName("option"));

    for (WebElement listElement : options) {
      if (listElement.getAttribute("value").equals(option)) {
        listElement.click();

        break;
      }
    }
  }

  public void submitForm(String text) {
    button(text).submit();
    waitForPageToLoad();
  }

  public void go(String url) {
    get(TEST_HOST + url);
    waitForPageToLoad();
  }

  public void goDirectPage(String url) {
    get(url);
  }

  public void openHomePage() {
    get(TEST_HOST);
    waitForPageToLoad();
  }

  public void openHomePage(String host) {
    if (!host.equals("")) {
      get("http://" + host + "." + TEST_HOST);
    } else {
      get(TEST_HOST);
    }
  }

  public void openHomePagePosfix(String host, String postfix) {
    if (!host.equals("")) {
      get("http://" + host + "." + SERVER_NAME + postfix);
    } else {
      get(TEST_HOST);
    }
  }

  public boolean isTextPresentOnPage(String text) {
    waitForElementPresent("/html/body", 10);
    return findElement(By.xpath("/html/body")).getText().contains(text);
  }

  public WebElement button(String text) {
    try {
      return findElement(By.xpath("//input[@type = 'submit' and @value = '" + text
          + "']"));
    } catch (NoSuchElementException e) {
      for (WebElement button : findElements(By.xpath("//button"))) {
        if (button.getText().equals(text)) {
          return button;
        }
      }

      throw e;
    }
  }

  public WebElement inputField(String text) {
    return findElement(By.name(text));
  }

  public WebElement link(String text) {
    return findElement(By.linkText(text));
  }

  public boolean elementPresent(String xpath) {
    return findElementsByXPath(xpath).size() > 0;
  }

  public void isElementPresent(String xpath) {
    findElementByXPath(xpath);
  }


  public void stopTestDriver() {
    close();
    quit();
  }

  public void isLinkVisible(String text) {
    WebElement link = findElementByLinkText(text);

    if (!link.isDisplayed()) {
      throw new SeleniumException("Link \"" + text + " \" is not visible");
    }
  }

  public boolean isLinkPresent(String text) {
    return findElementsByLinkText(text).size() > 0;
  }

  public boolean isLinkWithHrefPresent(String href) {
    return findElementsByXPath(String.format(LINK_WITH_PARTICULAR_HREF_XPATH_PATTERN, href)).size() > 0;
  }

  public void assertLinkPresent(String text) {
    findElementByLinkText(text);
  }

  public boolean isElementVisible(WebElement element) {
    return element.isDisplayed();
  }

  public boolean isElementVisibleByXpath(String xpath) {
    return isElementVisible(findElementByXPath(xpath));
  }

  public boolean isElementVisibleById(String id) {
    return isElementVisible(findElementById(id));
  }

  public String getValue(String string) {
    return findElementByXPath(string).getText();
  }

  public String getText(String string) {
    return findElementByXPath(string).getText().trim();
  }

  public String getAttribute(String locator, String attribute) {
    return findElementByXPath(locator).getAttribute(attribute);
  }

  public String getAttribute(By by, String attribute) {
      return findElement(by).getAttribute(attribute);
    }

  public void clear(String locator) {
    findElementByXPath(locator).clear();
  }

  public void login(String host, String login, String pass) {
    logoutFromCurrentSite();
    openHomePage(host);

    if (elementPresent("//li[@id='component_6']/a")) {
      clickByXpath("//li[@id='component_6']/a");
    } else {
      if (isLinkPresent("Вход")) {
        clickLink("Вход");
      }
    }
    type(inputField("username"), login);
    type(inputField("password"), pass);
    submit("//input[@value='Войти']");
    setLoginStatus(true);
  }

  public void login(String login, String pass) {
    logoutFromCurrentSite();
    if (elementPresent("//li[@id='component_6']/a")) {
      clickByXpath("//li[@id='component_6']/a");
    } else {
      if (isLinkPresent("Вход")) {
        clickLink("Вход");
      }
    }
    type(inputField("username"), login);
    type(inputField("password"), pass);
    submit("//input[@value='Войти']");
    setLoginStatus(true);
    loggedInUserName.set(login);
    loggedInUserPassword.set(pass);
  }

  public void loginWithOutLogout(String login, String pass) {
    if (elementPresent("//li[@id='component_6']/a")) {
      clickByXpath("//li[@id='component_6']/a");
    } else {
      if (isLinkPresent("Вход")) {
        clickLink("Вход");
      } else {
        if (isLinkPresent("Войти")) {
          clickLink("Войти");
        }
      }
    }
    type(inputField("username"), login);
    type(inputField("password"), pass);
    submit("//input[@value='Войти']");
    setLoginStatus(true);
    loggedInUserName.set(login);
    loggedInUserPassword.set(pass);
  }

  public boolean isUserLoggedIn(String userName, String password) {
    return userName.equals(loggedInUserName.get()) && password.equals(loggedInUserPassword.get());
  }

  public boolean isUserLoggedIn(UserData userData) {
    return isUserLoggedIn(userData.getLogin(), userData.getPassword());
  }


  public void clearLoggedInUserData() {
    loggedInUserName.remove();
    loggedInUserPassword.remove();
  }

  public void setLoggedInUserData(UserData userData) {
    loggedInUserName.set(userData.getLogin());
    loggedInUserPassword.set(userData.getPassword());
  }

  public void logoutFromCurrentSite() {
    manage().deleteAllCookies();
    String currentHost;
    try {
      currentHost = new URL(getCurrentUrl()).getHost();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    String logoffPageDirectUrl = PREFIX + currentHost + "/logoff.do";
    goDirectPage(logoffPageDirectUrl);
    manage().deleteAllCookies();
    manage().getCookies();
    goDirectPage(logoffPageDirectUrl);
    waitForPageToLoad();
    setLoginStatus(false);
  }

  public int getElementsCount(String xpath) {
    return findElementsByXPath(xpath).size();
  }

  public void waitForElementPresent(String locator, int timeout) {
    for (int i = 0; i < timeout; i++) {
      if (elementPresent(locator)) {
        return;
      }

      try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    }

  }

  public void waitForElementPresentAndVisible(String locator, int timeout) {
    for (int i = 0; i < timeout; i++) {
      if (elementPresent(locator) && isElementVisibleByXpath(locator)) {
        return;
      }

      try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    }
  }

  public void waitForTextPresent(String text, int timeout) {
    for (int i = 0; i < timeout; i++) {
      if (findElement(By.xpath("/html/body")).getText().contains(text)) {
        return;
      }

      try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    }
  }

  public void waitForTextPresent(String text) {
    waitForTextPresent(text, 60);
  }

  public void clickTextContains(String text) {
    clickByXpath("//*[contains(text(),'" + text + "')]");
  }

  public boolean isElementPresentByXpath(String xpath) {
    return findElementsByXPath(xpath).size() > 0;
  }

  public boolean isElementPresentAndVisible(String xpath) {
    return elementPresent(xpath) && isElementVisibleByXpath(xpath);
  }

  public void check(String xpath) {
    WebElement element = findElementByXPath(xpath);
    check(element);
  }

  public void check(WebElement element) {
    if (element.isSelected()) {
      return;
    }

    element.click();

    for (int i = 0; i < 10; i++) {
      if (element.isSelected()) {
        return;
      }

      try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    }
  }

  public boolean isSelected(String xpath) {
    WebElement element = findElementByXPath(xpath);

    return element.isSelected();
  }

  public void setCheckBoxValue(String xpath, boolean isSelected) {
    if (isSelected) {
      check(xpath);
    } else {
      uncheck(xpath);
    }
  }

  public void uncheck(String xpath) {
    WebElement element = findElementByXPath(xpath);
    uncheck(element);
  }

  public void uncheck(WebElement element) {
    if (!element.isSelected()) {
      return;
    }

    element.click();

    for (int i = 0; i < 10; i++) {
      if (!element.isSelected()) {
        return;
      }

      try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    }
  }

  public static void setCommonWebDriver(CommonWebDriver commonDriver) {
    webDriverHolder.set(commonDriver);
  }

  public void closeCurrentWindowAndSwitchToMain() {
    close();
    switchTo().window(mainBrowserWindowHandle);
  }
}
