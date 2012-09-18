package ru.hh.etest.applicant;

import java.util.concurrent.Callable;

import ru.hh.etest.selenium.AbstractTestRunner;
import ru.hh.etest.selenium.CommonWebDriver;
import ru.hh.etest.xpath.Xpath;

import com.headhunter.webapp.fixture.UserData;

public class CreateApplicant
        extends AbstractTestRunner
        implements Callable<String>{
    private String server;
	private CommonWebDriver driver;
	private UserData applicantInfo;
	private String resultText;
	private static int count;

	public CreateApplicant(String server) {
        this.server = server;
		count++; // incrementing number of created objects
        /*resultText = "CreateApplicant "
        		+ count
        		+ " at: "
        		+ server
        		+ "\n";*/
	}

    public CreateApplicant() {
        System.out.println("WARNING: nullable constructor is called");
        this.server = "bear"; //default
        count++; // incrementing number of created objects
        /*resultText = "CreateApplicant "
        		+ count
        		+ " at: "
        		+ server
        		+ "\n";*/
    }

    public String call() {
        driver = this.runDiver(this.server);
        doCreateApplicant();
        driver.quit();
        //System.out.println("CreateApplicant: " + getCount());
		return this.resultText;
    }

	private void doCreateApplicant() {
        applicantInfo = new UserData();

        System.out.println("Starting selenium actions...");
        System.out.println("TestHost: " + driver.getTestHost());
        System.out.println("current url: " + driver.getCurrentUrl());
        System.out.println("is link present: " + driver.isLinkPresent("Создать резюме"));
        driver.assertLinkPresent("Создать резюме");
        driver.clickLink("Создать резюме");
        driver.waitForPageToLoad();

        driver.typeByXpath(Xpath.inputByParentDivText("Фамилия"), applicantInfo.getLastName());
        driver.typeByXpath(Xpath.inputByParentDivText("Имя"), applicantInfo.getFirstName());
        driver.typeByXpath(Xpath.inputByParentDivText("Эл. почта"), applicantInfo.getEmail());
        driver.typeByXpath(Xpath.inputByParentDivText("Пароль"), applicantInfo.getPassword());
        driver.waitForElementPresent("//input[@value='Зарегистрироваться'][not(@disabled)]", 5);
        driver.clickInputByValueAndWait("Зарегистрироваться");
        this.resultText = ("Соискатель | "
                + applicantInfo.getEmail()
                + " | "
                + applicantInfo.getPassword());
	}
/* 
	synchronized public int getCount() {
*/		/*try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
/*		return count;
	}
	public String getResultText() {
		return resultText;
	}*/
}
