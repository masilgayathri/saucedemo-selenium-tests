package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private By productName = By.cssSelector("[data-test='inventory-item-name']");
    private By productDescription = By.cssSelector("[data-test='inventory-item-desc']");
    private By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By addToCartButton = By.cssSelector("[data-test='add-to-cart']");
    private By removeButton = By.cssSelector("[data-test='remove']");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public void clickAddToCart() {
        click(addToCartButton);
    }

    public boolean isRemoveButtonDisplayed() {
        return waitForElement(removeButton).isDisplayed();
    }
}