import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.UserMethods;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext context) {
        initDriver();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (driver != null) {
            driver.quit();
        }
    }

    private void initDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        if (browser.equals("firefox")) {
            startUpFirefox();
        } else {
            startUpChrome();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void startUpChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(UserMethods.IMPLICIT_WAIT));
    }

    private void startUpFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(UserMethods.IMPLICIT_WAIT));
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == WebDriver.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return driver;
    }
}
