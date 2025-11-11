import org.example.MainPage;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.example.User;
import org.example.UserData;
import org.example.UserMethods;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@ExtendWith(DriverExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ProfileToConstructorTest {

    public User user;
    public String accessToken;
    public WebDriver driver;
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
    @DisplayName("Проверка клика на Конструктор")
    @Description("Переход по клику на Конструктор")
    public void ConstructorClickTest(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForModalOverlay();
        mainPage.constructorClick();
    }

    @Test
    @DisplayName("Проверка клика на логотип Stellar Burgers")
    @Description("Проверка клика на логотип Stellar Burgers")
    public void clickLogoTest(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForModalOverlay();
        mainPage.clickLogo();
    }

   @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}