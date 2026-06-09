package com.mycompany.automation.pages;

import org.openqa.selenium.By;

public class ProfilePage extends BasePage {
    private final By displayName = By.id("profileFullName");
    private final By addressBlock = By.id("profileAddress");

    public String getDisplayedFullName() {
        try {
            return driver.findElement(displayName).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean hasDeliveryAddress() {
        try {
            String txt = driver.findElement(addressBlock).getText();
            return txt != null && !txt.trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
