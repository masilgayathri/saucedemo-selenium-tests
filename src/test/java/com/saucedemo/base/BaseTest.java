package com.saucedemo.base;

import com.saucedemo.utils.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest"); // stops Chrome password pop-ups

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(TestData.BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}