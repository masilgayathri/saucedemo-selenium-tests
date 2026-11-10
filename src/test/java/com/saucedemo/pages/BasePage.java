package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    protected By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait until the element is visible, then return it
    public WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until the element can be clicked, then click it
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Clear the field and type the text
    public void type(By locator, String text) {
        WebElement field = waitForElement(locator);
        field.clear();
        field.sendKeys(text);
    }

    // Read the text of an element
    public String getText(By locator) {
        return waitForElement(locator).getText();
    }

    // Returns the number on the cart badge, or 0 if there is no badge
    public int getCartCount() {
        List<WebElement> badge = driver.findElements(cartBadge);
        if (badge.size() == 0) {
            return 0;
        }
        return Integer.parseInt(badge.get(0).getText());
    }

    // Clicks the cart icon at the top right
    public void openCart() {
        click(cartIcon);
    }

    // Turns pricing text into number e.g.: "$29.99" or "Tax: $3.20" into 29.99 or 3.20
    public double getPriceFromText(String text) {
        String number = text.substring(text.indexOf("$") + 1);
        return Double.parseDouble(number);
    }
}