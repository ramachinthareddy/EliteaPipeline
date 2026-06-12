package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final By addAddressPrompt = By.cssSelector(".add-address");
    private final By addPaymentPrompt = By.cssSelector(".add-payment");
    private final By cardNumberInput = By.id("card-number");
    private final By submitCardButton = By.id("submit-card");
    private final By cardErrorMessage = By.cssSelector(".card-error");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPromptingForAddressOrPayment() {
        return !driver.findElements(addAddressPrompt).isEmpty() || !driver.findElements(addPaymentPrompt).isEmpty();
    }

    public void enterCard(String cardNumber) {
        driver.findElement(cardNumberInput).clear();
        driver.findElement(cardNumberInput).sendKeys(cardNumber);
    }

    public void submitCard() {
        driver.findElement(submitCardButton).click();
    }

    public String getCardError() {
        return driver.findElements(cardErrorMessage).isEmpty() ? "" : driver.findElement(cardErrorMessage).getText();
    }
}
