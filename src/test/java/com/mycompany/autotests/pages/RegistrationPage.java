package com.mycompany.autotests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for the registration page. Replace locators with real ones.
 * Single Responsibility: defines interactions for registration page only.
 */
public class RegistrationPage {
    private final WebDriver driver;

    // TODO: Replace these locators with the actual ones from your application
    private final By fullNameInput = By.id("fullName");
    private final By emailInput = By.id("email");
    private final By mobileInput = By.id("mobile");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.id("registerSubmit");
    private final By nameValidationMessage = By.id("fullNameError");
    private final By emailValidationMessage = By.id("emailError");
    private final By mobileValidationMessage = By.id("mobileError");
    private final By otpSentIndicator = By.id("otpSentIndicator");
    private final By alreadyRegisteredMessage = By.id("alreadyRegisteredMsg");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void enterFullName(String fullName) {
        driver.findElement(fullNameInput).clear();
        driver.findElement(fullNameInput).sendKeys(fullName);
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

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public String getNameValidationMessage() {
        return safeGetText(nameValidationMessage);
    }

    public String getEmailValidationMessage() {
        return safeGetText(emailValidationMessage);
    }

    public String getMobileValidationMessage() {
        return safeGetText(mobileValidationMessage);
    }

    public boolean isOtpSentIndicatorVisible() {
        return isElementVisible(otpSentIndicator);
    }

    public boolean isAlreadyRegisteredMessageVisible() {
        return isElementVisible(alreadyRegisteredMessage);
    }

    private boolean isElementVisible(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private String safeGetText(By by) {
        try {
            WebElement el = driver.findElement(by);
            return el.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
