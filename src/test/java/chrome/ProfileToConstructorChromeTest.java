package chrome;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.example.User;
import org.example.UserData;
import org.example.UserMethods;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileToConstructorChromeTest {

    public User user;
    public String accessToken;
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void setUp() {

        RestAssured.baseURI = UserMethods.baseURL;

        driver = new ChromeDriver();
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
    @DisplayName("Проверка клика на Конструктор")
    @Description("Переход по клику на Конструктор")
    public void ConstructorClickTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");
        By constructorButton = By.xpath(".//p[text()='Конструктор']");

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));
            } catch (TimeoutException e) {

            }
            wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
        }

    @Test
    @DisplayName("Проверка клика на логотип Stellar Burgers")
    @Description("Проверка клика на логотип Stellar Burgers")
    public void clickLogoTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");
        By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));
        } catch (TimeoutException e) {
        }
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();

    }
   @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}