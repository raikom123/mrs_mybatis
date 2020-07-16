package mrs.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoomPage {

  private WebDriver driver;

  public RoomPage(WebDriver driver) {
    this.driver = driver;
  }

  public LoginPage logout() {
    var wait = new WebDriverWait(driver, 10);
    driver.findElement(By.id("logoutButton")).sendKeys(Keys.ENTER);
    // 10秒間、パスワードテキストボックスを取得する
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
    return new LoginPage(driver);
  }

}
