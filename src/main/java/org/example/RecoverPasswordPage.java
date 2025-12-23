package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class RecoverPasswordPage {
    public WebDriver driver;
    public By loginButton = By.xpath(".//a[text()='Войти']"); //Локатор кнопки Войти

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonLogin() {
        driver.findElement(loginButton).click();
    }
}