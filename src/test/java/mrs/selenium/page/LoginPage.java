package mrs.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

  private WebDriver driver;
  private int port;

  private LoginPage(WebDriver webDriver, int port) {
    this.driver = webDriver;
    this.port = port;
  }

  LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  public RoomPage login(String userName, String password) {
    driver.findElement(By.id("username")).sendKeys(userName);
    driver.findElement(By.id("password")).sendKeys(password);
    var wait = new WebDriverWait(driver, 10);
    driver.findElement(By.id("loginButton")).sendKeys(Keys.ENTER);
    // 10秒間、Room画面から前日リンクを取得する
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("prevDays")));
    return new RoomPage(driver);
  }

  private void access() {
    driver.get("http://localhost:" + port);
  }

  public static LoginPage to(WebDriver webDriver, int port) {
    LoginPage loginPage = new LoginPage(webDriver, port);
    loginPage.access();
    return loginPage;
  }
}
