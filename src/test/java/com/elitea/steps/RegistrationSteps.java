package com.elitea.steps;

import com.elitea.pages.DashboardPage;
import com.elitea.pages.OTPPage;
import com.elitea.pages.RegistrationPage;
import com.elitea.pages.UserProfilePage;
import com.elitea.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Cucumber step definitions for registration-related BDD feature files.
 * Uses Page Objects and a DriverFactory. TestNG assertions are used.
 */
public class RegistrationSteps {

    private WebDriver driver;
    private RegistrationPage registrationPage;
    private OTPPage otpPage;
    private DashboardPage dashboardPage;
    private UserProfilePage profilePage;

    // Base URL can be overridden via system property for CI
    private final String baseUrl = System.getProperty("base.url", "https://localhost:8080");

    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        registrationPage = new RegistrationPage(driver);
        otpPage = new OTPPage(driver);
        dashboardPage = new DashboardPage(driver);
        profilePage = new UserProfilePage(driver);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Given("the registration page is reachable and I am not logged in")
    public void registrationPageReachableNotLoggedIn() {
        registrationPage.goTo(baseUrl);
        Assert.assertTrue(registrationPage.isPageDisplayed(), "Registration page should be visible");
        // Additional checks for not logged in could be cookies/session cleanup
    }

    @Given("the registration page is reachable")
    public void registrationPageReachable: An array of objects, each with file_path and code. - https://github.com/ramachinthareddy/EliteaPipeline: The GitHub repository name or URL. - I've received the target branch: **EliteayTest**

However, your message appears to be incomplete. You mentioned "Save the input in..." but didn't specify where or what format you'd like me to save it in.

Could you please complete your request? For example:
- Save it in a variable?
- Save it in a specific file format (JSON, YAML, text)?
- Save it as part of a git command?
- Extract repository information from this branch?

Please provide the complete instruction so I can assist you properly.: The branch to push to. - code pushed successfully.: The commit message.  Your task: For each file in files: 1. Create or update the file at file_path in the specified repository and branch. 2. Commit the changes with commit_message.  Return STRICT JSON only: {   "push_results": [     {       "file_path": "",       "commit_url": "",       "status": "File pushed"     }   ] } Do not return explanations. Do not return markdown. Only JSON.
