package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private By cartItems = By.cssSelector("[data-test='inventory-item']");
    private By itemName = By.cssSelector("[data-test='inventory-item-name']");
    private By itemPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By itemQuantity = By.cssSelector("[data-test='item-quantity']");
    private By checkoutButton = By.id("checkout");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfItems() {
        waitForElement(cartItems);
        return driver.findElements(cartItems).size();
    }

    public String getItemName() {
        return getText(itemName);
    }

    public String getItemPrice() {
        return getText(itemPrice);
    }

    public String getItemQuantity() {
        return getText(itemQuantity);
    }

    public void clickCheckout() {
        click(checkoutButton);
    }


    // Names of all items in the cart
    public List<String> getItemNames() {
        waitForElement(itemName);
        List<WebElement> nameElements = driver.findElements(itemName);
        List<String> names = new ArrayList<>();
        for (WebElement name : nameElements) {
            names.add(name.getText());
        }
        return names;
    }

    // Remove an item using its id, for example "sauce-labs-backpack"
    public void removeItem(String productId) {
        click(By.cssSelector("[data-test='remove-" + productId + "']"));
    }
}