package ru.hh.etest.applicant;

import ru.hh.etest.selenium.AbstractTestRunner;
import ru.hh.etest.selenium.CommonWebDriver;
import ru.hh.etest.xpath.Xpath;

import com.headhunter.webapp.fixture.UserData;

public class CreateApplicant extends AbstractTestRunner{
	private CommonWebDriver driver;
	private UserData applicantInfo;
	private String message;

	public CreateApplicant(String server) {
		driver = runDiver(server);
		createApplicant();
		driver.quit();
	}

	private void createApplicant() {
			applicantInfo = new UserData();
		    driver.clickLinkAndWait("Создать резюме");
		    driver.typeByXpath(Xpath.inputByParentDivText("Фамилия"), applicantInfo.getLastName());
		    driver.typeByXpath(Xpath.inputByParentDivText("Имя"), applicantInfo.getFirstName());
		    driver.typeByXpath(Xpath.inputByParentDivText("Эл. почта"), applicantInfo.getEmail());
		    driver.typeByXpath(Xpath.inputByParentDivText("Пароль"), applicantInfo.getPassword());
		    driver.waitForElementPresent("//input[@value='Зарегистрироваться'][not(@disabled)]", 5);
		    driver.clickInputByValueAndWait("Зарегистрироваться");
		    this.message = ("Соискатель зарегистрирован! </p> Логин: " + applicantInfo.getEmail() + " </p> Пароль: " + applicantInfo.getPassword());
		    
	}

	public String getMessage() {
		return message;
	}
}
