package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    public WebDriver driver;
    public WebDriverWait wait;

    public MainPage(WebDriver driver) {

        this.driver = driver;
    }

    public void clickAccountButton() {
        driver.findElement(loginButton).click();
   }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickBunButton() {

        driver.findElement(bunButton).click();
    }

    public void clickSauceButton() {

        driver.findElement(sauceButton).click();
    }


    public void clickButtonLogin() {

        driver.findElement(loginButton).click();
    }

    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    public String getCreateOrderButtonText(){
        return driver.findElement(createOrderButton).getText();
    }

    public String getMenuTabLocator(){
        return driver.findElement(menuTabLocator).getText();
    }


    public By loginButton = By.xpath(".//button[text()='Войти в аккаунт']"); //Локатор кнопка Войти
    public By menuTabLocator = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]"); //Локатор вкладок меню
    public By createOrderButton = By.xpath(".//button[text()='Оформить заказ']"); //Локатор кнопка Оформить заказ
    public By bunButton = By.xpath(".//span[text()='Булки']"); //Локатор кнопка Булки
    public By sauceButton = By.xpath(".//span[text()='Соусы']"); //Локатор кнопка Соусы
    public By fillingButton = By.xpath(".//span[text()='Начинки']"); //Локатор кнопка Начинки
    public By profileButton = By.xpath(".//p[text()='Личный Кабинет']"); //Локатор кнопка Личный кабинет
    public By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");
}