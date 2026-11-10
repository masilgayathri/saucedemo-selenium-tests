package com.saucedemo.utils;

public final class TestData {

    private TestData() {
    }

    //Website URL
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String INVENTORY_URL = BASE_URL + "inventory.html";

    // Demo login details as shown on the SauceDemo login page
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PASSWORD = "secret_sauce";

    // Checkout details
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String POSTAL_CODE = "NG1 1AA";

    // Expected values on the overview and complete pages
    public static final String PAYMENT_INFO = "SauceCard #31337";
    public static final String SHIPPING_INFO = "Free Pony Express Delivery!";
    public static final double TAX_RATE = 0.08;
    public static final String ORDER_COMPLETE_MESSAGE = "Thank you for your order!";
    public static final String CHECKOUT_COMPLETE_URL = BASE_URL + "checkout-complete.html";
    public static final String ORDER_DISPATCH_MESSAGE =
            "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
}