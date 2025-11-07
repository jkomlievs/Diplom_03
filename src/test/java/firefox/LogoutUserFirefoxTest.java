package firefox;

import io.qameta.allure.Description;
import io.restassured.RestAssured;

import org.example.User;
import org.example.UserData;
import org.example.UserMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LogoutUserFirefoxTest {

    public User user;
    public String accessToken;
    private WebDriver driver;
    public WebDriverWait wait;


    By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");

    @BeforeEach
    public void setUp() {

        RestAssured.baseURI = UserMethods.baseURL;

        driver = new FirefoxDriver();
        driver.get(UserMethods.baseURL);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        user = UserData.createUser();
        accessToken = UserMethods.createNewUser(user)
                .then()
                .extract()
                .path("accessToken");
    }

      @Test
      @Description("Переход в личный кабинет")
      @DisplayName("Переход в личный кабинет")
      public void   clickAccountButton() {

          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
          By profileButton = By.xpath(".//p[text()='Личный Кабинет']");

          try {
              wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));
          } catch (TimeoutException e) {

          }
          wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
      }

    @Test
    @Description("Выход из аккаунта в личном кабинете")
    @DisplayName("Выход из аккаунта в личном кабинете")
    public void clickButtonLogout() {
        By emailField = By.xpath(".//label[text()='Email']/../input");
        By passwordField = By.xpath(".//label[text()='Пароль']/../input");
        By loginInAccount = By.xpath(".//button[text()='Войти в аккаунт']");
        By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
        By loginButton = By.xpath(".//button[text()='Войти']");
        By logoutButton = By.xpath(".//button[text()='Выход']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));
        } catch (TimeoutException e) {

        }

        driver.findElement(loginInAccount).click();
        driver.findElement(emailField).sendKeys("test-super-111021@yandex.ru");
        driver.findElement(passwordField).sendKeys("12345678");
        driver.findElement(loginButton).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));
        } catch (TimeoutException e) {

        }
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));
        } catch (TimeoutException e) {

        }

        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();

        }
        @AfterEach
        public void tearDown () {
            if (driver != null) {
                driver.quit();
        }
    }
}