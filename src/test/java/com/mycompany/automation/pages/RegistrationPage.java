package com.mycompany.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {
    // Update locators to match your application
    private final By fullNameInput = By.id("fullName");
    private final By emailInput = By.id("email");
    private final By mobileInput = By.id("mobile");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.id("registerBtn");
    private final By validationMessage = By.cssSelector(".validation-message");
    private final By otpEntryUi = By.id("otpEntry");

    public void open() {
        driver.get(baseUrl() + "/register");
    }

    public void enterFullName(String name) {
        driver.findElement(fullNameInput).clear();
        driver.findElement(fullNameInput).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterMobile(String mobile) {
        driver.findElement(mobileInput).clear();
        driver.findElement(mobileInput).sendKeys(mobile);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void submit() {
        driver.findElement(submitButton).click();
    }

    public String getValidationMessage() {
        WebElement el = driver.findElement(validationMessage);
        return el.getText();
    }

    public boolean isOtpUiDisplayed() {
        try {
            return driver.findElement(otpEntryUi).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
