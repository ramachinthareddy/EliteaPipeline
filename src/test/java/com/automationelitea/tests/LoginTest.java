package com.automationelitea.tests;

import com.automationelitea.config.ConfigManager;
import com.automationelitea.drivers.DriverFactory;
import com.automationelitea.pages.HomePage;
import com.automationelitea.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigManager.getBaseUrl());
        loginPage = new LoginPage(driver);
    }

    @Test
    public void verifyLoginPageLoads() {
        Assert.assertTrue(driver.getTitle().length() > 0, "Login page title should not be empty");
    }

    @Test(dependsOnMethods = "verifyLoginPageLoads")
    public void shouldLoginWithValidCredentials() {
        HomePage homePage = loginPage
                .enterUsername("testuser")
                .enterPassword("password")
                .submitLogin();

        Assert.assertTrue(homePage.getHeadingText().length() > 0, "Home page heading should be visible after login");
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
