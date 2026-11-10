package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    private By items = By.cssSelector("[data-test='inventory-item']");
    private By itemPrices = By.cssSelector("[data-test='inventory-item-price']");
    private By paymentInfo = By.cssSelector("[data-test='payment-info-value']");
    private By shippingInfo = By.cssSelector("[data-test='shipping-info-value']");
    private By itemTotal = By.cssSelector("[data-test='subtotal-label']");
    private By tax = By.cssSelector("[data-test='tax-label']");
    private By total = By.cssSelector("[data-test='total-label']");
    private By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfItems() {
        waitForElement(items);
        return driver.findElements(items).size();
    }

    // Adds up the price of every item listed on the page
    public double getSumOfItemPrices() {
        waitForElement(itemPrices);
        List<WebElement> prices = driver.findElements(itemPrices);
        double sum = 0;
        for (WebElement price : prices) {
            sum = sum + getPriceFromText(price.getText());
        }
        return sum;
    }

    public String getPaymentInfo() {
        return getText(paymentInfo);
    }

    public String getShippingInfo() {
        return getText(shippingInfo);
    }

    public double getItemTotal() {
        return getPriceFromText(getText(itemTotal));
    }

    public double getTax() {
        return getPriceFromText(getText(tax));
    }

    public double getTotal() {
        return getPriceFromText(getText(total));
    }

    public void clickFinish() {
        click(finishButton);
    }
}