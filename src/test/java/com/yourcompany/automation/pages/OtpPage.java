package com.yourcompany.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OtpPage extends BasePage {

    private By otpInput = By.id("otp");
    private By submitOtpButton = By.id("submitOtp");
    private By otpError = By.cssSelector(".otp-error");
    private By resendBtn = By.id("resendOtp");
    private By resendConfirmation = By.cssSelector(".resend-confirm");

    public OtpPage(WebDriver driver) {
        super(driver);
    }

    public void enterOtp(String otp) {
        type(otpInput, otp);
    }

    public void submitOtp() {
        click(submitOtpButton);
    }

    public String getOtpError() {
        return isDisplayed(otpError) ? getText(otpError) : "";
    }

    public void clickResend() {
        click(resendBtn);
    }

    public boolean isResendConfirmationDisplayed() {
        return isDisplayed(resendConfirmation);
    }
}
