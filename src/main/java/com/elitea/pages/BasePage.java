package com.elitea.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void waitForVisible(org.openqa.selenium.By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForClickable(org.openqa.selenium.By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
