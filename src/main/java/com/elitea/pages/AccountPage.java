package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {
    private final By profileName = By.cssSelector(".profile-fullname");
    private final By noAddressBanner = By.cssSelector(".no-address");
    private final By noPaymentBanner = By.cssSelector(".no-payment");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String getProfileFullName() {
        return driver.findElements(profileName).isEmpty() ? "" : driver.findElement(profileName).getText();
    }

    public boolean hasDefaultAddress() {
        return driver.findElements(noAddressBanner).isEmpty();
    }

    public boolean hasDefaultPayment() {
        return driver.findElements(noPaymentBanner).isEmpty();
    }
}
