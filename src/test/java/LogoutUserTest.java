import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.example.LoginPage;
import org.example.MainPage;
import org.example.User;
import org.example.UserData;
import org.example.UserMethods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@ExtendWith(DriverExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogoutUserTest {

    public User user;
    public String accessToken;
    private WebDriver driver;
    public WebDriverWait wait;


    @BeforeEach
    public void setUp(WebDriver driver) {


            RestAssured.baseURI = UserMethods.baseURL;
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
    public void clickAccountButton(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForModalOverlay();
        mainPage.clickProfileButton();
    }
    @Test
    @Description("Выход из аккаунта в личном кабинете")
    @DisplayName("Выход из аккаунта в личном кабинете")
    public void clickButtonLogout(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForModalOverlay();
        loginPage.openLoginForm();
        loginPage.enterEmail("test-super-111021@yandex.ru");
        loginPage.enterPassword("12345678");
        loginPage.clickLoginButton();

        loginPage.waitForModalOverlay();
        loginPage.clickProfileButton();

        loginPage.waitForModalOverlay();
        loginPage.clickLogoutButton();
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (accessToken != null) {
            UserMethods.deleteUser(accessToken);
        }
    }
}