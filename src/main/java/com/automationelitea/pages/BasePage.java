package com.automationelitea.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    protected void waitForUrl(String expectedUrl) {
        wait.until(ExpectedConditions.urlContains(expectedUrl));
    }

    protected void waitForTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }
}
