package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OTPPage extends BasePage {

    private final By otpInput = By.id("otp_input");
    private final By otpSubmit = By.id("otp_submit");
    private final By resendOtp = By.id("resend_otp");
    private final By otpNotification = By.id("otp_notification");

    public OTPPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        try {
            waitForVisible(otpInput);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterOtp(String otp) {
        driver.findElement(otpInput).clear();
        driver.findElement(otpInput).sendKeys(otp);
    }

    public void submitOtp() {
        driver.findElement(otpSubmit).click();
    }

    public void clickResend() {
        driver.findElement(resendOtp).click();
    }

    public String getOtpNotification() {
        try {
            waitForVisible(otpNotification);
            return driver.findElement(otpNotification).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
