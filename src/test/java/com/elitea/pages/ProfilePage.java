package com.elitea.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    private WebDriver driver;

    @FindBy(css = ".profile-fullname")
    private WebElement fullNameLabel;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDisplayedFullName() {
        try { return fullNameLabel.getText(); } catch (Exception e) { return ""; }
    }
}
