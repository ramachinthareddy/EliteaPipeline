package com.mycompany.autotests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for OTP verification page.
 */
public class OtpPage {
    private final WebDriver driver;

    // TODO: Replace locators with actual
    private final By otpInput = By.id("otpCode");
    private final By submitOtpButton = By.id("submitOtp");
    private final By otpErrorMessage = By.id("otpError");
    private final By resendOtpButton = By.id("resendOtp");
    private final By rateLimitMessage = By.id("resendRateLimitMsg");

    public OtpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterOtp(String code) {
        driver.findElement(otpInput).clear();
        driver.findElement(otpInput).sendKeys(code);
    }

    public void submitOtp() {
        driver.findElement(submitOtpButton).click();
    }

    public String getOtpErrorMessage() {
        try {
            return driver.findElement(otpErrorMessage).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickResendOtp() {
        driver.findElement(resendOtpButton).click();
    }

    public boolean isRateLimitMessageVisible() {
        try {
            return driver.findElement(rateLimitMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
