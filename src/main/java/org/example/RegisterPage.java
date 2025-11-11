package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class RegisterPage {

    public WebDriver driver;

    public RegisterPage(WebDriver driver) {

        this.driver = driver;
    }

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmailField(String email) {

        driver.findElement(emailField).sendKeys(email);
    }


    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }


    public void clickLoginButton() {


        driver.findElement(loginButton).click();
    }

    public void setUserData(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    public String getMessagePasswordError() {

        return driver.findElement(passwordError).getText();
    }

    public By loginButton = By.xpath(".//a[text()='Войти']"); //Локатор для кнопки входа
    public By nameField = By.xpath(".//label[text()='Имя']/../input"); //Локатор для нажатия поля Ввести Имя пользователя
    public By emailField = By.xpath(".//label[text()='Email']/../input"); //Локатор для нажатия поля Ввести email
    public By passwordField = By.xpath(".//label[text()='Пароль']/../input"); //Локатор для нажатия поля Ввести пароль
    public By passwordError = By.xpath(".//p[text()='Некорректный пароль']"); //Локатор для уведомления об ошибке в пароле
    public By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]"); //Локатор для кнопки регистрации
    public By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
}
