package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ProfilePage {

    public WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public String clickButtonLogout() {
        return driver.findElement(logoutButton).getText();
    }

    public void  getLogoutButton(){

        driver.findElement(logoutButton).click();
    }

    public By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");  //Локатор кнопки с лого на странице
    public By constructorButton = By.xpath(".//p[text()='Конструктор']");  //Локатор кнопки "Конструктор" в ЛК
    public By logoutButton = By.xpath(".//button[text()='Выход']");  //Локатор кнопки "Выход" в ЛК
}




