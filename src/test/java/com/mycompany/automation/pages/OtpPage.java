package com.mycompany.automation.pages;

import org.openqa.selenium.By;

public class OtpPage extends BasePage {
    private final By otpInput = By.id("otp");
    private final By verifyButton = By.id("verifyOtp");

    public void enterOtp(String otp) {
        driver.findElement(otpInput).clear();
        driver.findElement(otpInput).sendKeys(otp);
    }

    public void submitOtp() {
        driver.findElement(verifyButton).click();
    }
}
