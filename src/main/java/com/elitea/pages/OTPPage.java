package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OTPPage extends BasePage {
    private final By otpInput = By.id("otp");
    private final By verifyButton = By.id("verify-otp");
    private final By resendButton = By.id("resend-otp");
    private final By otpSentConfirmation = By.cssSelector(".otp-sent-info");
    private final By throttleMessage = By.cssSelector(".otp-throttle");

    public OTPPage(WebDriver driver) {
        super(driver);
    }

    public void enterOtp(String otp) {
        driver.findElement(otpInput).clear();
        driver.findElement(otpInput).sendKeys(otp);
    }

    public void verifyOtp() {
        driver.findElement(verifyButton).click();
    }

    public void resendOtp() {
        driver.findElement(resendButton).click();
    }

    public boolean isOtpSentConfirmed() {
        return !driver.findElements(otpSentConfirmation).isEmpty();
    }

    public String getThrottleMessage() {
        return driver.findElements(throttleMessage).isEmpty() ? "" : driver.findElement(throttleMessage).getText();
    }
}
