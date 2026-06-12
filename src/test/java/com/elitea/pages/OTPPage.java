package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * OTPPage: POM for OTP verification screen.
 */
public class OTPPage extends BasePage {

    private final WebDriverWait wait;

    private final By otpInput = By.id("otpInput");
    private final By verifyButton = By.id("verifyOtp");
    private final By resendButton = By.id("resendOtp");
    private final By invalidOtpMsg = By.id("otpInvalid");
    private final By expiredOtpMsg = By.id("otpExpired");
    private final By otpSentIndicator = By.id("otpSentIndicator");

    public OTPPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(otpInput));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterOtp(String otp) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(otpInput));
        el.clear();
        el.sendKeys(otp);
    }

    public void submitOtp() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(verifyButton));
        el.click();
    }

    public void resendOtp() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(resendButton));
        el.click();
    }

    public boolean isInvalidOtpMessageShown() {
        try {
            return driver.findElement(invalidOtpMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExpiredOtpMessageShown() {
        try {
            return driver.findElement(expiredOtpMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOtpSentIndicatorVisible() {
        try {
            return driver.findElement(otpSentIndicator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
