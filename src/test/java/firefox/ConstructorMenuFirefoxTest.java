package firefox;

import org.example.User;
import org.example.UserData;
import org.example.UserMethods;
import org.example.MainPage;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ConstructorMenuFirefoxTest {

    public User user;
    public String accessToken;
    private WebDriver driver;
    private WebDriverWait wait;

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
    @DisplayName("Переход к разделу Булки на главной странице")
    public void sectionBunTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Соусы']")));
        mainPage.clickBunButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Булки']")));
    }


    @Test
    @DisplayName("Переход к разделу Соусы на главной странице")
    public void sectionSauceTest() {
        MainPage mainPageData = new MainPage(driver);
        mainPageData.clickSauceButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Соусы']")));
    }

    @Test
    @DisplayName("Переход к разделу Начинки на главной странице")
    public void sectionFillingTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Начинки']")));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}