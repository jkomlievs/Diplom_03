import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.example.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@ExtendWith(DriverExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginUserTest {

    private User user;
    private String accessToken;
    private WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void setUp(WebDriver driver) {

        driver.get(UserMethods.baseURL);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        RestAssured.baseURI = UserMethods.baseURL;

        user = UserData.createUser();
        accessToken = UserMethods.createNewUser(user)
                .then()
                .extract()
                .path("accessToken");
    }

    @Test
    @Description("Проверка авторизации через главную страницу")
    @DisplayName("Проверка авторизации через главную страницу")
    void authorizationMainPageTest(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginButton();
        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа через кнопку личного кабинета")
    void profilePageTest(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickAccountButton();
        loginPage.setUserLoginData(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через форму регистрации")
    void registerPageTest(WebDriver driver) {
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
    void loginRecoverPasswordTest(WebDriver driver) {
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
        if (accessToken != null) {
            UserMethods.deleteUser(accessToken);
        }
    }
}




