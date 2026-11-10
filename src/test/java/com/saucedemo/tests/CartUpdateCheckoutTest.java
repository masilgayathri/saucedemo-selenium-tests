package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutInformationPage;
import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartUpdateCheckoutTest extends BaseTest {

    @Test
    public void userCanRemoveItemsFromCartAndCompleteCheckout() {
        // 1. Log in and add all 6 products
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        ProductsPage productsPage = new ProductsPage(driver);
        for (int i = 0; i < 6; i++) {
            productsPage.addNextProductToCart();
        }
        Assert.assertEquals(productsPage.getCartCount(), 6, "Cart badge should show 6");

        // 2. Open the cart and remove 2 items
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getNumberOfItems(), 6, "Cart should have 6 items");

        cartPage.removeItem("sauce-labs-backpack");
        cartPage.removeItem("sauce-labs-bike-light");

        Assert.assertEquals(cartPage.getNumberOfItems(), 4, "Cart should have 4 items after removing 2");
        Assert.assertEquals(cartPage.getCartCount(), 4, "Cart badge should show 4");
        Assert.assertFalse(cartPage.getItemNames().contains("Sauce Labs Backpack"),
                "Backpack should be removed");
        Assert.assertFalse(cartPage.getItemNames().contains("Sauce Labs Bike Light"),
                "Bike Light should be removed");

        // 3. Checkout
        cartPage.clickCheckout();
        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        infoPage.enterDetailsAndContinue(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);

        // 4. Check the overview has only the 4 remaining items, with correct totals
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(overviewPage.getNumberOfItems(), 4, "Overview should list 4 items");

        double itemTotal = overviewPage.getItemTotal();
        double tax = overviewPage.getTax();
        double expectedTax = Math.round(itemTotal * TestData.TAX_RATE * 100) / 100.0;

        Assert.assertEquals(itemTotal, overviewPage.getSumOfItemPrices(), 0.01,
                "Item total should equal the sum of the 4 item prices");
        Assert.assertEquals(tax, expectedTax, 0.01, "Tax should be 8% of the item total");
        Assert.assertEquals(overviewPage.getTotal(), itemTotal + tax, 0.01,
                "Total should equal item total plus tax");

        // 5. Finish and check the confirmation
        overviewPage.clickFinish();
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertEquals(completePage.getCompleteMessage(), TestData.ORDER_COMPLETE_MESSAGE,
                "Confirmation message");
    }
}