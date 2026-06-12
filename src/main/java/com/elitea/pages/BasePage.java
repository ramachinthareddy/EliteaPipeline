package com.elitea.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void jsClick(Object element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
