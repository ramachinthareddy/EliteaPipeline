package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserProfilePage extends BasePage {

    private final By fullNameField = By.id("profile_full_name");
    private final By editButton = By.id("edit_profile");

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getFullName() {
        try {
            waitForVisible(fullNameField);
            return driver.findElement(fullNameField).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickEdit() {
        driver.findElement(editButton).click();
    }
}
