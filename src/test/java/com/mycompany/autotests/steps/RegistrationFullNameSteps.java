package com.mycompany.autotests.steps;

import com.mycompany.autotests.pages.ProfilePage;
import com.mycompany.autotests.pages.RegistrationPage;
import com.mycompany.autotests.utils.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Step definitions for "User Registration - Full Name" scenarios.
 */
public class RegistrationFullNameSteps {
    private final WebDriver driver = DriverManager.getDriver();
    private final RegistrationPage registrationPage = new RegistrationPage(driver);
    private final ProfilePage profilePage = new ProfilePage(driver);

    // URL should be set from config; replace with actual registration page URL
    private final String registrationUrl = System.getProperty("app.url", "https://example.com/register");

    @Given("a new customer is on the registration page")
    public void a_new_customer_is_on_the_registration_page() {
        registrationPage.open(registrationUrl);
    }

    @When("they enter {string} in the Full Name field, a valid email, and a valid password and submit the form")
    public void they_enter_fullname_valid_email_and_password_and_submit(String fullName) {
        registrationPage.enterFullName(fullName);
        // use unique email per test (timestamp) or inject from test data
        String email = "test+" + System.currentTimeMillis() + "@example.com";
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("ValidPassword123!");
        registrationPage.clickSubmit();
    }

    @Then("the system should accept the full name and store First Name = {string} and Last Name = {string} in the account profile")
    public void system_should_accept_and_store_names(String expectedFirst, String expectedLast) {
        // Navigate to profile or assume redirected; adapt as needed
        // TODO: Wait for navigation or use explicit waits
        String actualFirst = profilePage.getFirstName();
        String actualLast = profilePage.getLastName();

        Assert.assertEquals(actualFirst, expectedFirst, "First name stored incorrectly");
        Assert.assertEquals(actualLast, expectedLast, "Last name stored incorrectly");
    }

    @Then("account creation should proceed (or OTP should be sent if contact verification is required)")
    public void account_creation_or_otp() {
        // If OTP step uses UI indicator on registration page:
        boolean otpShown = registrationPage.isOtpSentIndicatorVisible();
        // If account was created directly, profile should be visible
        boolean profileShown = !profilePage.getFullName().isEmpty();

        Assert.assertTrue(otpShown || profileShown, "Neither OTP was sent nor profile was created; registration did not proceed");
    }

    @When("they enter {string} in the Full Name field and submit the form")
    public void they_enter_single_token_name_and_submit(String fullName) {
        registrationPage.enterFullName(fullName);
        // optionally fill other required fields minimally
        registrationPage.enterEmail("test+" + System.currentTimeMillis() + "@example.com");
        registrationPage.enterPassword("ValidPassword123!");
        registrationPage.clickSubmit();
    }

    @Then("the system should display a validation message requiring both first and last name (for example, {string})")
    public void system_should_display_validation_message_for_full_name(String expectedMessage) {
        String actual = registrationPage.getNameValidationMessage();
        Assert.assertTrue(actual.contains(expectedMessage) || actual.equals(expectedMessage),
                "Expected validation about first and last name. Actual: " + actual);
    }

    @Then("the system should prevent proceeding with registration")
    public void system_should_prevent_proceeding() {
        boolean otp = registrationPage.isOtpSentIndicatorVisible();
        Assert.assertFalse(otp, "OTP should not have been sent when validation failed.");
        // Further check: not redirected to profile
        Assert.assertTrue(registrationPage.getNameValidationMessage().length() > 0, "Expected validation message displayed.");
    }

    @Given("a new customer with a legal single-word name is on the registration page")
    public void new_customer_with_legal_single_word_name_on_registration_page() {
        registrationPage.open(registrationUrl);
    }

    @Then("the system should behave according to the configured business rule:")
    public void system_should_behave_per_business_rule(io.cucumber.datatable.DataTable dataTable) {
        // DataTable provided only in Gherkin text; we will handle both possibilities via config flag
        String allowSingleName = System.getProperty("business.allowSingleName", "false");
        if ("true".equalsIgnoreCase(allowSingleName)) {
            // expect account creation with Last Name empty
            // navigate to profile and verify
            String first = profilePage.getFirstName();
            String last = profilePage.getLastName();
            Assert.assertTrue(!first.isEmpty(), "First name should be stored");
            Assert.assertTrue(last.isEmpty(), "Last name should be empty per business rule when single word allowed");
        } else {
            // expect validation message
            String nm = registrationPage.getNameValidationMessage();
            Assert.assertTrue(nm.length() > 0, "Expected validation message requiring both first and last name");
        }
    }

    @When("they
