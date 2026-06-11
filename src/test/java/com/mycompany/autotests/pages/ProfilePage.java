package com.mycompany.autotests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing a user profile page for verifying stored names.
 */
public class ProfilePage {
    private final WebDriver driver;

    // TODO: Replace with correct locators
    private final By firstNameField = By.id("profileFirstName");
    private final By lastNameField = By.id("profileLastName");
    private final By fullNameDisplay = By.id("profileFullName");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstName() {
        try {
            return driver.findElement(firstNameField).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getLastName() {
        try {
            return driver.findElement(lastNameField).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getFullName() {
        try {
            return driver.findElement(fullNameDisplay).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
