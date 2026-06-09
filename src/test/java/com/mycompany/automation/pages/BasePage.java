package com.mycompany.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.mycompany.automation.driver.DriverFactory;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // common helper methods can go here (navigate, getTitle, etc.)
    protected String baseUrl() {
        return System.getProperty("base.url", "https://example.com");
    }
}
