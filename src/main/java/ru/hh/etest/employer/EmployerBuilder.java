package ru.hh.etest.employer;

import ru.hh.etest.common.CommonUtils;
import ru.hh.etest.selenium.CommonWebDriver;
import ru.hh.etest.xpath.Xpath;

import com.headhunter.webapp.fixture.EmployerCategory;

public class EmployerBuilder {
  public void createNewEmployer(Employer employer, CommonWebDriver driver, Boolean isSimilar) {
    driver.clickLinkAndWait("Разместить вакансию");
    driver.clickLinkAndWait("Регистрация нового работодателя");
    fillEmployerRegIngo(employer, driver);
    confirmAndCheckRegistration(driver);
  }

  public void openEmployerRegistrationPage(CommonWebDriver driver) {
    driver.openHomePage();
    driver.clickLinkAndWait("Разместить вакансию");
    driver.clickLinkAndWait("Регистрация нового работодателя");
  }

  public void createTutByEmployer(Employer employerData, CommonWebDriver driver) {
    driver.go("/");
    driver.clickByXpath("//span[text()='Зарегистрироваться']");
    driver.clickLinkByNormalizeText("Работодатель");
    fillEmployerRegIngo(employerData, driver);
    confirmAndCheckRegistration(driver);
  }

  public void confirmAndCheckRegistration(CommonWebDriver driver) {
    driver.clickInputByValueAndWait("Зарегистрировать компанию");
    CommonUtils.sleep(10);
    if (driver.isElementPresentByXpath("//input[@value='Все равно зарегистрироваться']")) {
      driver.clickInputByValueAndWait("Все равно зарегистрироваться");
    }

    driver.isTextPresent("для вас отправлено письмо");
  }

  public void confirmRegistration(CommonWebDriver driver, Boolean isSimilar) {
    driver.clickInputByValueAndWait("Зарегистрировать компанию");

    if (isSimilar) {
      driver.waitForElementPresent("//input[@value='Все равно зарегистрироваться']", 20);
      driver.clickInputByValueAndWait("Все равно зарегистрироваться");
    }
  }

  public void fillEmployerRegIngo(Employer employer, CommonWebDriver driver) {
    setLastName(employer.getMcp().getLastName(), driver);
    setFirstName(employer.getMcp().getFirstName(), driver);
    setEmail(employer.getMcp().getEmail(), driver);
    setType(employer.getType(), driver);
    CommonUtils.sleep(1);
    setOrganisationForm(employer.getOrganizationForm(), driver);
    setCompanyName(employer.getCompanyName(), driver);
    setDescription(employer.getDescription(), driver);
    CommonUtils.sleep(1);
    setRegion(employer.getRegion(), driver);
    setSite(employer.getSite(), driver);
    setPosition(employer.getMcpPosition(), driver);
    setPhone(employer.getPhone(), driver);
    setHowKnowAboutUs(employer.getHowKnowAboutUs(), driver);
  }

  private void setPhone(String[] phone, CommonWebDriver driver) {
    driver.typeByXpath("//input[@id='countryCodePhone']", phone[0]);
    driver.typeByXpath("//input[@id='cityCodePhone']", phone[1]);
    driver.typeByXpath("//input[@id='localCodePhone']", phone[2]);
  }

  private void setPosition(String mcpPosition, CommonWebDriver driver) {
    if (mcpPosition != null) {
      driver.typeByXpath(Xpath.inputOfRegistrationfields("Ваша должность"), mcpPosition);
    }
  }

  private void setSite(String site, CommonWebDriver driver) {
    if (site != null) {
      driver.typeByXpath(Xpath.inputOfRegistrationfields("Сайт"), site);
    }
  }

  private void setRegion(String region, CommonWebDriver driver) {
    if (region != null) {
      driver.typeByXpath(Xpath.inputOfRegistrationfields("Регион")
        + "[@type='text']", region);
    }
  }

  private void setDescription(String description, CommonWebDriver driver) {
    if (description != null) {
      driver.executeScript(
        "jsx.CallBacks.dispatch('jsxComponents-Editor-ChangeHTML', jsx.Dom.$$(document.body, '.jsxComponents-Editor'), '"
        + description + "')");
    }
  }

  private void setCompanyName(String companyName, CommonWebDriver driver) {
    if (companyName != null) {
      driver.typeByXpath("//input[@id='companyName']", companyName);
    }
  }

  private void setOrganisationForm(String organizationForm, CommonWebDriver driver) {
    if (organizationForm != null) {
      driver.selectOptionByValue("//select[@id='organizationForm']", organizationForm);
    }
  }

  private void setType(EmployerCategory type, CommonWebDriver driver) {
    switch (type) {
      case COMPANY:
        driver.clickLabelByText("Прямой работодатель");

        break;

      case AGENCY:
        driver.clickLabelByText("Кадровое агентство");

        break;

      case PROJECT_DIRECTOR:
        driver.clickLabelByText("Руководитель проекта");

        break;

      case PRIVATE_RECRUITER:
        driver.clickByXpath("//label[input[@value='PRIVATE_RECRUITER']]");

        break;

      case ANONYMOUS:
        driver.clickLabelByText("Анонимный работодатель");

        break;

      default:
        break;
    }
  }

  private void setEmail(String email, CommonWebDriver driver) {
    driver.typeByXpath(Xpath.inputOfRegistrationfields("Эл. почта"), email);
  }

  private void setFirstName(String firstName, CommonWebDriver driver) {
    driver.typeByXpath(Xpath.inputOfRegistrationfields("Имя"), firstName);
  }

  private void setLastName(String lastName, CommonWebDriver driver) {
    driver.typeByXpath(Xpath.inputOfRegistrationfields("Фамилия"), lastName);
  }

  private void setHowKnowAboutUs(String howKnowAboutUs, CommonWebDriver driver) {
    driver.selectOptionByValue("howDidYouHearAboutUsId", howKnowAboutUs);
  }
}
