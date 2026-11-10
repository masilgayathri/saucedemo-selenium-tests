package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductDetailsPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectItemTest extends BaseTest {

    @Test
    public void selectedItemDetailsAndCartAreCorrect() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        // Read the first product's details on the Products page
        ProductsPage productsPage = new ProductsPage(driver);
        String expectedName = productsPage.getFirstProductName();
        String expectedDescription = productsPage.getFirstProductDescription();
        String expectedPrice = productsPage.getFirstProductPrice();

        // Open the product and check its details match
        productsPage.clickFirstProduct();
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);

        Assert.assertEquals(detailsPage.getProductName(), expectedName,
                "Product name should match the Products page");
        Assert.assertEquals(detailsPage.getProductDescription(), expectedDescription,
                "Product description should match the Products page");
        Assert.assertEquals(detailsPage.getProductPrice(), expectedPrice,
                "Product price should match the Products page");

        // Add to cart and check the button and badge
        detailsPage.clickAddToCart();
        Assert.assertTrue(detailsPage.isRemoveButtonDisplayed(),
                "Button should change to Remove");
        Assert.assertEquals(detailsPage.getCartCount(), 1,
                "Cart badge should show 1");

        // Open the cart and check its contents
        detailsPage.openCart();
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(cartPage.getNumberOfItems(), 1,
                "Cart should have 1 item");
        Assert.assertEquals(cartPage.getItemName(), expectedName,
                "Item name in cart should match");
        Assert.assertEquals(cartPage.getItemPrice(), expectedPrice,
                "Item price in cart should match");
        Assert.assertEquals(cartPage.getItemQuantity(), "1",
                "Quantity should be 1");
    }
}