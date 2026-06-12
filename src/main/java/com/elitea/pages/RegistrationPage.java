package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object representing the registration page.
 * Single responsibility: expose actions and queries related to registration.
 */
public class RegistrationPage extends BasePage {

    private final By fullNameInput = By.id("full_name");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By emailInput = By.id("email");
    private final By mobileInput = By.id("mobile");
    private final By passwordInput = By.id("password");
    private final By confirmPasswordInput = By.id("confirm_password");
    private final By submitButton = By.id("register_submit");
    private final By notificationBanner = By.id("notification");
    private final By fullNameError = By.id("error_full_name");
    private final By emailError = By.id("error_email");
    private final By mobileError = By.id("error_mobile");
    private final By passwordError = By.id("error_password");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void goTo(String baseUrl) {
        driver.get(baseUrl + "/register");
    }

    public boolean isPageDisplayed() {
        try {
            waitForVisible(submitButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterFullName(String fullName) {
        WebElement el = driver.findElement(fullNameInput);
        el.clear();
        el.sendKeys(fullName);
    }

    public void enterFirstAndLastName(String first, String last) {
        // Support pages that split first/last
        if (isElementPresent(firstNameInput) && isElementPresent(lastNameInput)) {
            WebElement f = driver.findElement(firstNameInput);
            WebElement l = driver.findElement(lastNameInput);
            f.clear();
            f.sendKeys(first);
            l.clear();
            l.sendKeys(last);
        } else {
            enterFullName(first + " " + last);
        }
    }

    public void enterEmail(String email) {
        WebElement el = driver.findElement(emailInput);
        el.clear();
        el.sendKeys(email);
    }

    public void enterMobile(String mobile) {
        WebElement el = driver.findElement(mobileInput);
        el.clear();
        el.sendKeys(mobile);
    }

    public void enterPassword(String password) {
        WebElement el = driver.findElement(passwordInput);
        el.clear();
        el.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        WebElement el = driver.findElement(confirmPasswordInput);
        el.clear();
        el.sendKeys(password);
    }

    public void submit() {
        WebElement btn = driver.findElement(submitButton);
        btn.click();
    }

    public String getNotificationText() {
        try {
            waitForVisible(notificationBanner);
            return driver.findElement(notificationBanner).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getFullNameError() {
        return getTextIfPresent(fullNameError);
    }

    public String getEmailError() {
        return getTextIfPresent(emailError);
    }

    public String getMobileError() {
        return getTextIfPresent(mobileError);
    }

    public String getPasswordError() {
        return getTextIfPresent(passwordError);
    }

    private String getTextIfPresent(By locator) {
        try {
            waitForVisible(locator);
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            return "";
        }
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Utility to mimic user submitting with Enter key on a field
    public void submitUsingKeyboard() {
        try {
            WebElement el = driver.findElement(submitButton);
            el.sendKeys(Keys.ENTER);
        } catch (Exception ignored) {
        }
    }
}
