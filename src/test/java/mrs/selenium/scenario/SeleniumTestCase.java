package mrs.selenium.scenario;

import mrs.MrsApplication;
import mrs.selenium.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = {MrsApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class SeleniumTestCase {

  private WebDriver driver;

  @LocalServerPort private int port;

  @BeforeEach
  private void before() {
    this.driver = createWebDriver();
  }

  @Test
  public void login画面からroom画面への画面遷移の確認() {
    var loginPage = LoginPage.to(driver, port);

    // Exceptionが発生しなければ正常とする
    loginPage.login("taro-yamada", "taro-yamada");
  }

  @AfterEach
  private void after() {
    this.driver.close();
  }

  protected abstract WebDriver createWebDriver();
}
