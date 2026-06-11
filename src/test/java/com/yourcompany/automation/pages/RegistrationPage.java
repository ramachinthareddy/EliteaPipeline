package com.yourcompany.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    // NOTE: Adjust locators to your application
    private By fullNameInput = By.id("fullName");
    private By emailInput = By.id("email");
    private By mobileInput = By.id("mobile");
    private By passwordInput = By.id("password");
    private By submitButton = By.id("registerBtn");
    private By successMessage = By.cssSelector(".success-message");
    private By validationError = By.cssSelector(".validation-error");
    private By nameStoredField = By.id("displayedName"); // hypothetical read-back
    private By otpPrompt = By.id("otpPrompt"); // hypothetical

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void enterFullName(String name) {
        type(fullNameInput, name);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterMobile(String mobile) {
        type(mobileInput, mobile);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void submit() {
        click(submitButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public String getSuccessMessage() {
        return isSuccessMessageDisplayed() ? getText(successMessage) : "";
    }

    public String getValidationError() {
        return isDisplayed(validationError) ? getText(validationError) : "";
    }

    public boolean isOtpPromptDisplayed() {
        return isDisplayed(otpPrompt);
    }

    // Example: read back name stored on profile page or returned payload
    public String getStoredName() {
        try {
            return getText(nameStoredField);
        } catch (Exception e) {
            return "";
        }
    }
}
