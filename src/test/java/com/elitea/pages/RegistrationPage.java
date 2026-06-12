package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * RegistrationPage: Page Object encapsulating registration page operations.
 * Use clear method names to support readability in tests (SOLID - single responsibility).
 */
public class RegistrationPage extends BasePage {

    private final WebDriverWait wait;

    // Locators - replace the By selectors with application-specific locators
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("email");
    private final By mobileInput = By.id("mobile");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.id("registerSubmit");
    private final By nameValidationMsg = By.id("nameError");
    private final By emailValidationMsg = By.id("emailError");
    private final By mobileValidationMsg = By.id("mobileError");
    private final By passwordValidationMsg = By.id("passwordError");
    private final By duplicateAccountMsg = By.id("duplicateAccount");
    private final By otpSentBanner = By.id("otpSentBanner");

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open(String url) {
        driver.get(url);
    }

    public void enterFirstName(String firstName) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        el.clear();
        el.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        el.clear();
        el.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        el.clear();
        el.sendKeys(email);
    }

    public void enterMobile(String mobile) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileInput));
        el.clear();
        el.sendKeys(mobile);
    }

    public void enterPassword(String password) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        el.clear();
        el.sendKeys(password);
    }

    public void submitRegistration() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        el.click();
    }

    public boolean isOtpSentBannerVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(otpSentBanner));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getNameValidationMessage() {
        try {
            return driver.findElement(nameValidationMsg).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String getEmailValidationMessage() {
        try {
            return driver.findElement(emailValidationMsg).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String getMobileValidationMessage() {
        try {
            return driver.findElement(mobileValidationMsg).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String getPasswordValidationMessage() {
        try {
            return driver.findElement(passwordValidationMsg).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String getDuplicateAccountMessage() {
        try {
            return driver.findElement(duplicateAccountMsg).getText();
        } catch (Exception e) {
            return null;
        }
    }
}
