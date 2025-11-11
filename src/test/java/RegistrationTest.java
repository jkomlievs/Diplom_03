import io.restassured.RestAssured;
import org.example.LoginPage;
import org.example.RegisterPage;
import org.example.UserData;
import org.example.UserLogin;
import org.example.UserMethods;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@ExtendWith(DriverExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class RegistrationTest {

    private org.example.User user;
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
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registerUserTest(WebDriver driver) {

        org.example.MainPage mainPage = new org.example.MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.clickLoginButton();
        loginPage.clickButtonRegister();
        registerPage.setUserData(user.getEmail(), user.getPassword(), user.getName());
        registerPage.clickRegisterButton();
        UserLogin login = new UserLogin(user.getEmail(), user.getPassword());
        RestAssured.baseURI = org.example.UserMethods.baseURL;
        accessToken = org.example.UserMethods.loginUser(login).then().extract().path("accessToken");
        }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    public void registerUserWithWrongPasswordTest (WebDriver driver) {
         user = org.example.UserData.createWrongUser();
         org.example.MainPage mainPage = new org.example.MainPage(driver);
         LoginPage loginPage = new LoginPage(driver);
         RegisterPage registerPage = new RegisterPage(driver);
         mainPage.clickLoginButton();
         loginPage.clickButtonRegister();
         registerPage.setUserData(user.getName(), user.getEmail(), user.getPassword());
         registerPage.clickRegisterButton();
         String text = registerPage.getMessagePasswordError();
            assertEquals("Некорректный пароль", text);
        }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}