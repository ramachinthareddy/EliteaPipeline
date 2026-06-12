package com.elitea.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OTPPage {
    private WebDriver driver;

    @FindBy(name = "otp")
    private WebElement otpInput;

    @FindBy(css = "button.verify-otp")
    private WebElement verifyButton;

    @FindBy(css = ".otp-error")
    private WebElement otpError;

    @FindBy(css = "button.resend-otp")
    private WebElement resendButton;

    @FindBy(css = ".resend-message")
    private WebElement resendMessage;

    public OTPPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterOtp(String code) {
        otpInput.clear();
        otpInput.sendKeys(code);
    }

    public void submitOtp() {
        verifyButton.click();
    }

    public String getOtpErrorText() {
        try { return otpError.getText(); } catch (Exception e) { return ""; }
    }

    public void clickResend() {
        resendButton.click();
    }

    public String getResendMessage() {
        try { return resendMessage.getText(); } catch (Exception e) { return ""; }
    }
}
