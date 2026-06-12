package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * AccountPage: represents welcome/my-account page after successful registration.
 */
public class AccountPage extends BasePage {

    private final WebDriverWait wait;
    private final By accountWelcomeText = By.id("accountWelcome");
    private final By storedName = By.id("accountName");
    private final By storedContact = By.id("accountContact");

    public AccountPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isAt() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(accountWelcomeText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getStoredName() {
        try {
            return driver.findElement(storedName).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String getStoredContact() {
        try {
            return driver.findElement(storedContact).getText();
        } catch (Exception e) {
            return null;
        }
    }
}
