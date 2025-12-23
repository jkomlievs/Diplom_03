package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    public WebDriver driver;
    public WebDriverWait wait;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitForModalOverlay() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));
        } catch (Exception ignored) {
        }
    }

    public void clickProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
    }

    public void clickAccountButton() {

        driver.findElement(loginButton).click();
   }

    public void clickLoginButton() {

        driver.findElement(loginButton).click();
    }

    public void clickBunButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
        wait.until(ExpectedConditions.elementToBeClickable(bunButton)).click();

    }

    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Соусы']")));
    }


    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Начинки']")));
    }


    public void constructorClick() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    public void clickLogo(){
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
    }


    public By loginButton = By.xpath(".//button[text()='Войти в аккаунт']"); //Локатор кнопка Войти
    public By bunButton = By.xpath(".//span[text()='Булки']"); //Локатор кнопка Булки
    public By sauceButton = By.xpath(".//span[text()='Соусы']"); //Локатор кнопка Соусы
    public By fillingButton = By.xpath(".//span[text()='Начинки']"); //Локатор кнопка Начинки
    public By profileButton = By.xpath(".//p[text()='Личный Кабинет']"); //Локатор кнопка Личный кабинет
    public By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");
    public By constructorButton = By.xpath(".//p[text()='Конструктор']");
    public By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

}