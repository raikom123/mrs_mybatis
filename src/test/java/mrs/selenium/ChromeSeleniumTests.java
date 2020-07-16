package mrs.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import mrs.selenium.scenario.SeleniumTestCase;


public class ChromeSeleniumTests extends SeleniumTestCase {

  @Override
  protected WebDriver createWebDriver() {
    System.setProperty("webdriver.chrome.driver", "/opt/WebDriver/bin/chromedriver");
    return new ChromeDriver();
  }
}
