import org.example.User;
import org.example.UserData;
import org.example.UserMethods;
import org.example.MainPage;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorMenuTest {

    public User user;
    public String accessToken;
    private WebDriver driver;
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
    @DisplayName("Переход к разделу Булки на главной странице")
    public void sectionBunTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
    }

    @Test
    @DisplayName("Переход к разделу Соусы на главной странице")
    public void sectionSauceTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
    }

    @Test
    @DisplayName("Переход к разделу Начинки на главной странице")
    public void sectionFillingTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingButton();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}