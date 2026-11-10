package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SortProductsTest extends BaseTest {

    @Test
    public void productsCanBeSortedByPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortBy("Price (low to high)");

        List<Double> prices = productsPage.getAllPrices();
        Assert.assertEquals(prices.size(), 6, "All 6 products should still be shown");

        // Each price should be less than or equal to the next one
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                    "Prices should be in ascending order, but " + prices.get(i)
                            + " comes before " + prices.get(i + 1));
        }
    }
}