package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validUserCanLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(productsPage.getPageTitle(), "Products",
                "Page title should be Products");
        Assert.assertEquals(driver.getCurrentUrl(), TestData.INVENTORY_URL,
                "User should be on the inventory page");
        Assert.assertTrue(productsPage.isProductListDisplayed(),
                "Product list should be displayed");
        Assert.assertEquals(productsPage.getProductCount(), 6,
                "Products page should show 6 products");
    }
}