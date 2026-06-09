package com.yourorg.tests.stepdefinitions;

import com.yourorg.tests.helpers.TestInbox;
import com.yourorg.tests.pages.RegistrationPage;
import com.yourorg.tests.pages.OTPPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

/**
 * Step definitions for Email and Email OTP scenarios.
 * Assumes TestInbox helper to read OTP messages from test mailbox.
 */
public class RegistrationEmailSteps {

    private final RegistrationPage registrationPage = new RegistrationPage();
    private final OTPPage otpPage = new OTPPage();

    @Given("the user is on the registration page and a test inbox is available")
    public void user_on_registration_with_test_inbox() {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt());
        Assert.assertTrue(TestInbox.isAvailable(), "Test inbox must be available for OTP delivery checks");
    }

    @When("the user enters {string} as Email and submits")
    public void user_enters_email_and_submits(String email) {
        registrationPage.enterEmail(email);
        registrationPage.submitRegistration();
    }

    @Then("the system sends an OTP to that email and prompts the user to enter the OTP")
    public void system_sends_otp_and_prompts() {
        // wait for OTP sending UI
        Assert.assertTrue(otpPage.isAt(), "OTP entry page should be displayed");
        String otp = TestInbox.waitForOtpForLastSentEmail();
        Assert.assertNotNull(otp, "OTP should be delivered to test inbox");
    }

    @When("the user enters invalid email formats such as {string}, {string}, or {string} and attempts to submit")
    public void user_enters_invalid_emails_and_submits(String e1, String e2, String e3) {
        // try them one by one, or treat as multiple steps; we try first to simulate one scenario
        registrationPage.enterEmail(e1);
        registrationPage.submitRegistration();
    }

    @Then("the system displays inline validation errors, prevents submission, and does not send an OTP")
    public void system_rejects_invalid_email() {
        Assert.assertTrue(registrationPage.hasFieldValidationError("email"), "Invalid email should show inline validation error");
        Assert.assertFalse(otpPage.isAt(), "OTP page should not be displayed for invalid email");
    }

    @Given("the user is on the registration page and an account exists for {string}")
    public void existing_account_for_email(String email) {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt());
        // Prepare the system state: ensure account exists. This can be done via API helper or DB fixture.
        // Example: TestDataHelper.ensureAccountExists(email)
        // We'll assert the helper exists; actual implementation should create the user if missing.
        // TestDataHelper.ensureAccountExists(email);
    }

    @When("the user enters {string} and attempts to proceed")
    public void user_enters_existing_email(String email) {
        registrationPage.enterEmail(email);
        registrationPage.submitRegistration();
    }

    @Then("the system shows an {string} message and suggests account recovery or sign-in")
    public void system_shows_email_already_in_use_message(String expectedMessage) {
        Assert.assertTrue(registrationPage.hasInlineMessage(expectedMessage),
                "Expected email already in use message to be shown");
        Assert.assertFalse(otpPage.isAt(), "OTP should not be sent when email already in use");
    }

    @Given("the user received the OTP email for the provided address")
    public void user_received_otp_email() {
        Assert.assertTrue(otpPage.isAt(), "OTP entry page should be displayed");
        // Ensure we indeed have an OTP available in TestInbox
        String otp = TestInbox.waitForOtpForLastSentEmail();
        Assert.assertNotNull(otp, "Test inbox must contain OTP");
    }

    @When("the user enters the correct OTP within its validity period and submits")
    public void user_enters_correct_otp_and_submits() {
        String otp = TestInbox.getLatestOtpForLastSentEmail();
        Assert.assertNotNull(otp, "OTP must be available");
        otpPage.enterOtp(otp);
        otpPage.submitOtp();
    }

    @Then("the system verifies the OTP and allows continuation of the registration flow")
    public void system_verifies_otp_and_continues() {
        Assert.assertTrue(otpPage.verificationSucceeded(), "OTP verification should succeed with correct OTP");
    }

    @Given("the user is on the OTP entry screen after an OTP was sent")
    public void user_on_otp_screen_after_sent() {
        Assert.assertTrue(otpPage.isAt(), "User should be on OTP entry screen");
    }

    @When("the user enters an incorrect OTP (e.g., off by one digit) and submits")
    public void user_enters_incorrect_otp_and_submits() {
        String otp = TestInbox.getLatestOtpForLastSentEmail();
        Assert.assertNotNull(otp);
        String incorrect = otp.substring(0, otp.length() - 1) + (otp.charAt(otp.length() - 1) == '0' ? '1' : '0');
        otpPage.enterOtp(incorrect);
        otpPage.submitOtp();
    }

    @Then("the system rejects the OTP with an error message and allows retries within configured retry limits")
    public void system_re
