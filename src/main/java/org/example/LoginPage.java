package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public WebDriver driver;
    public  WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickButtonLogin() {

        driver.findElement(loginButton).click();
    }

    public void clickButtonRegister() {
        driver.findElement(registerButton).click();
    }

    public void clickRecoverButtonPassword() {
        driver.findElement(recoverPasswordButton).click();
    }


    public void setUserLoginData(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
    }

    public void openLoginForm() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));
        } catch (Exception ignored) {
        }
        driver.findElement(loginInAccount).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void waitForModalOverlay() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));
        } catch (Exception ignored) {
        }
    }

    public By emailField = By.xpath(".//label[text()='Email']/../input"); //Локатор поля ввода email
    public By passwordField = By.xpath(".//label[text()='Пароль']/../input"); //Локатор поля ввода пароля
    public By loginButton = By.xpath(".//button[text()='Войти']"); //Локатор кнопки Войти
    public By registerButton = By.xpath(".//a[(@class='Auth_link__1fOlj' and text()='Зарегистрироваться')]"); //Локатор кнопки Зарегистрироваться
    public By recoverPasswordButton = By.xpath(".//a[text()='Восстановить пароль']"); //Локатор кнопки Восстановить пароль
    public By loginInAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    public By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    public By logoutButton = By.xpath(".//button[text()='Выход']");
    public By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");

}
