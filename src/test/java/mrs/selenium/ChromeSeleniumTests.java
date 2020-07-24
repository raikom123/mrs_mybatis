package mrs.selenium;

import mrs.selenium.scenario.SeleniumTestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeSeleniumTests extends SeleniumTestCase {

  @Override
  protected WebDriver createWebDriver() {
    System.setProperty("webdriver.chrome.driver", "/opt/WebDriver/bin/chromedriver");
    return new ChromeDriver();
  }
}
