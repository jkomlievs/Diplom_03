package chrome;

import java.time.Duration;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.example.LoginPage;
import org.example.MainPage;
import org.example.RegisterPage;
import org.example.RecoverPasswordPage;
import org.example.User;
import org.example.UserData;
import org.example.UserMethods;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginUserChromeTest {

        private User user;
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
    @Description("Проверка авторизации через главную страницу")
    @DisplayName("Проверка авторизации через главную страницу")
    void AuthorizationMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginButton();
        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа через кнопку личного кабинета")
    void ProfilePageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickAccountButton();
        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через форму регистрации")
    void RegisterPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickAccountButton();
        loginPage.clickButtonRegister();
        registerPage.clickLoginButton();

        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через форму восстановления пароля")
    void loginRecoverPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);

        mainPage.clickAccountButton();
        loginPage.clickRecoverButtonPassword();
        recoverPasswordPage.clickButtonLogin();

        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}





