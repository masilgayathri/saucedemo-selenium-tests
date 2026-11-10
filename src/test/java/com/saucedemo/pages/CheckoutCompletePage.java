package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private By completeHeader = By.cssSelector("[data-test='complete-header']");
    private By backHomeButton = By.id("back-to-products");
    private By completeText = By.cssSelector("[data-test='complete-text']");
    private By generatePdfButton = By.cssSelector("[data-test='generate-pdf-order']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteMessage() {
        return getText(completeHeader);
    }

    public boolean isBackHomeButtonDisplayed() {
        return waitForElement(backHomeButton).isDisplayed();
    }

    public String getDispatchMessage() {
        return getText(completeText);
    }

    public boolean isGeneratePdfButtonDisplayed() {
        return waitForElement(generatePdfButton).isDisplayed();
    }
}