package com.elitea.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import java.util.List;

// Page Object representing the registration page. Exposes actions used by step definitions.
public class RegistrationPage {
    private WebDriver driver;

    // Adjust locators to match AUT. These are sample locators and should be adapted.
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "mobile")
    private WebElement mobileInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(css = ".field-error")
    private List<WebElement> fieldErrors;

    @FindBy(css = ".otp-sent-message")
    private WebElement otpSentMessage;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void enterFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterMobile(String mobile) {
        mobileInput.clear();
        mobileInput.sendKeys(mobile);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void submit() {
        submitButton.click();
    }

    public boolean isOtpSentMessageDisplayed() {
        try {
            return otpSentMessage != null && otpSentMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstFieldError() {
        try {
            if (fieldErrors != null && !fieldErrors.isEmpty()) {
                return fieldErrors.get(0).getText();
            }
        } catch (Exception ignored) {}
        return "";
    }

    public boolean hasFieldErrorContaining(String text) {
        try {
            for (WebElement e : fieldErrors) {
                if (e.getText() != null && e.getText().contains(text)) {
                    return true;
                }
            }
        } catch (Exception ignored) {}
        return false;
    }

    // For security checks
    public String getPageSource() {
        return driver.getPageSource();
    }
}
