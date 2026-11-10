package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    // Locators for the Products page
    private By pageTitle = By.cssSelector("[data-test='title']");
    private By productList = By.cssSelector("[data-test='inventory-list']");
    private By productItems = By.cssSelector("[data-test='inventory-item']");
    private By productNames = By.cssSelector("[data-test='inventory-item-name']");
    private By productDescriptions = By.cssSelector("[data-test='inventory-item-desc']");
    private By productPrices = By.cssSelector("[data-test='inventory-item-price']");
    private By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private By anyAddToCartButton = By.cssSelector("button[data-test^='add-to-cart']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isProductListDisplayed() {
        return waitForElement(productList).isDisplayed();
    }

    public int getProductCount() {
        List<WebElement> items = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productItems));
        return items.size();
    }

    // The first product on the page (Sauce Labs Backpack)
    public String getFirstProductName() {
        return getText(productNames);
    }

    public String getFirstProductDescription() {
        return getText(productDescriptions);
    }

    public String getFirstProductPrice() {
        return getText(productPrices);
    }

    public void clickFirstProduct() {
        click(productNames);
    }

    // Add a product using its id, for example "sauce-labs-backpack"
    public void addProductToCart(String productId) {
        click(By.cssSelector("[data-test='add-to-cart-" + productId + "']"));
    }

    // Clicks the first "Add to cart" button on the page.
    // After a click, that button changes to "Remove", so the next call adds the next product.
    public void addNextProductToCart() {
        click(anyAddToCartButton);
    }

    // Choose a sort option by its visible text, for example "Price (low to high)"
    public void sortBy(String optionText) {
        Select select = new Select(waitForElement(sortDropdown));
        select.selectByVisibleText(optionText);
    }

    // Read all prices on the page as numbers, in the order shown
    public List<Double> getAllPrices() {
        waitForElement(productPrices);
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();
        for (WebElement price : priceElements) {
            prices.add(getPriceFromText(price.getText()));
        }
        return prices;
    }
}