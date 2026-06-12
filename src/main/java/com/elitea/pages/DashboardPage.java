package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private final By profileMenu = By.id("profile_menu");
    private final By profileLink = By.id("profile_link");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        try {
            waitForVisible(profileMenu);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void goToProfile() {
        driver.findElement(profileMenu).click();
        driver.findElement(profileLink).click();
    }
}
