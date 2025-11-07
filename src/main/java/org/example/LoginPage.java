package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
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

    public String getEnterTextInfo() {
        return driver.findElement(enterTextInfo).getText();
    }

    public void setUserLoginData(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
    }

    public By emailField = By.xpath(".//label[text()='Email']/../input"); //Локатор поля ввода email
    public By passwordField = By.xpath(".//label[text()='Пароль']/../input"); //Локатор поля ввода пароля
    public By loginButton = By.xpath(".//button[text()='Войти']"); //Локатор кнопки Войти
    public By enterTextInfo = By.xpath("//h2[text()='Вход']"); //Локатор для текста на странице входа
    public By registerButton = By.xpath(".//a[(@class='Auth_link__1fOlj' and text()='Зарегистрироваться')]"); //Локатор кнопки Зарегистрироваться
    public By recoverPasswordButton = By.xpath(".//a[text()='Восстановить пароль']"); //Локатор кнопки Восстановить пароль

}
