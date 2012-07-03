package ru.hh.etest.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AbstractTestRunner extends RemoteWebDriver {
  private DesiredCapabilities capabilities;
  private URL url;
  
  
  protected CommonWebDriver runDiver(String server) {
    capabilities = DesiredCapabilities.firefox();
    try {
		url = new URL("http://grid.pyn.ru:4141/wd/hub");
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}
    return new CommonWebDriver(server, url, capabilities);
  }

}
