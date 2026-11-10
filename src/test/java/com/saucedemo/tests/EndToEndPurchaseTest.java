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

public class EndToEndPurchaseTest extends BaseTest {

    @Test
    public void userCanCompleteAPurchase() {
        // 1. Log in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        // 2. Add four items to the cart
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart("sauce-labs-backpack");
        productsPage.addProductToCart("sauce-labs-bike-light");
        productsPage.addProductToCart("sauce-labs-bolt-t-shirt");
        productsPage.addProductToCart("sauce-labs-fleece-jacket");
        Assert.assertEquals(productsPage.getCartCount(), 4, "Cart badge should show 4");

        // 3. Review the cart
        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getNumberOfItems(), 4, "Cart should have 4 items");
        Assert.assertTrue(cartPage.getItemNames().contains("Sauce Labs Backpack"),
                "Cart should contain the Backpack");
        Assert.assertTrue(cartPage.getItemNames().contains("Sauce Labs Bike Light"),
                "Cart should contain the Bike Light");
        Assert.assertTrue(cartPage.getItemNames().contains("Sauce Labs Bolt T-Shirt"),
                "Cart should contain the Bolt T-Shirt");
        Assert.assertTrue(cartPage.getItemNames().contains("Sauce Labs Fleece Jacket"),
                "Cart should contain the Fleece Jacket");

        // 4. Enter checkout details
        cartPage.clickCheckout();
        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        infoPage.enterDetailsAndContinue(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);

        // 5. Check the order overview
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(overviewPage.getNumberOfItems(), 4, "Overview should list 4 items");
        Assert.assertEquals(overviewPage.getPaymentInfo(), TestData.PAYMENT_INFO, "Payment information");
        Assert.assertEquals(overviewPage.getShippingInfo(), TestData.SHIPPING_INFO, "Shipping information");

        double itemTotal = overviewPage.getItemTotal();
        double tax = overviewPage.getTax();
        double total = overviewPage.getTotal();
        double expectedTax = Math.round(itemTotal * TestData.TAX_RATE * 100) / 100.0;

        Assert.assertEquals(itemTotal, overviewPage.getSumOfItemPrices(), 0.01,
                "Item total should equal the sum of the item prices");
        Assert.assertEquals(tax, expectedTax, 0.01,
                "Tax should be 8% of the item total");
        Assert.assertEquals(total, itemTotal + tax, 0.01,
                "Total should equal item total plus tax");

        // 6. Finish and check the confirmation
        overviewPage.clickFinish();
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertEquals(completePage.getCompleteMessage(), TestData.ORDER_COMPLETE_MESSAGE,
                "Confirmation message");
        Assert.assertEquals(completePage.getDispatchMessage(), TestData.ORDER_DISPATCH_MESSAGE,
                "Dispatch message");
        Assert.assertTrue(completePage.isGeneratePdfButtonDisplayed(),
                "Generate PDF button should be shown");
        Assert.assertEquals(driver.getCurrentUrl(), TestData.CHECKOUT_COMPLETE_URL,
                "Should be on the order complete page");
        Assert.assertTrue(completePage.isBackHomeButtonDisplayed(), "Back Home button should be shown");
        Assert.assertEquals(completePage.getCartCount(), 0, "Cart should be empty after the order");
    }
}