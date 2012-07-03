package ru.hh.etest.employer;

import ru.hh.etest.common.ImapMailClient;
import ru.hh.etest.selenium.AbstractTestRunner;
import ru.hh.etest.selenium.CommonWebDriver;

public class createEmployer extends AbstractTestRunner{
	private CommonWebDriver driver;
	private String message;
	private String employerPassword;
	
	public createEmployer(String server) {
		driver = runDiver(server);
		createEmployer(server);
		driver.quit();
	}

	private void createEmployer(String server) {
		Employer employerData = new Employer();
	    new EmployerBuilder().createNewEmployer(employerData, driver, true);
	    ImapMailClient client;
	    try {
	    	client = new ImapMailClient(server);
			employerPassword = client.getEmployerPassword(employerData.getMcp().getEmail(), false);
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    this.message = "Работодатель зарегистрирован! </p> Логин: " + employerData.getMcp().getEmail() + " <p> Пароль: " + employerPassword;
	}

	public String getMessage() {
		return message;
	}
	

}
