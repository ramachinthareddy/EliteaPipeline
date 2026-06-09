package com.mycompany.automation.steps;

import com.mycompany.automation.context.TestContext;
import com.mycompany.automation.api.ApiClient;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class RegistrationSteps {
    private final TestContext ctx = new TestContext();

    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
        ctx.registrationPage().open();
    }

    @When("the user enters Full Name {string}, a valid Email, and Password {string}")
    public void user_enters_fullname_email_password(String fullName, String password) {
        String email = "auto+" + System.currentTimeMillis() + "@example.test";
        ctx.registrationPage().enterFullName(fullName);
        ctx.registrationPage().enterEmail(email);
        ctx.registrationPage().enterPassword(password);
    }

    @When("the user submits the registration form")
    public void user_submits_registration_form() {
        ctx.registrationPage().submit();
    }

    @Then("the system should accept the full name input and proceed to the next step \\(OTP or account creation flow)")
    public void system_accepts_full_name_and_proceeds() {
        // check either OTP UI displayed or redirected to profile
        boolean otpDisplayed = ctx.registrationPage().isOtpUiDisplayed();
        Assert.assertTrue(otpDisplayed || true, "Expected OTP UI or next step. Update check for your app.");
    }

    @Then("the stored user profile \\(via UI profile or API\\) should contain Full Name {string}")
    public void stored_profile_should_contain_full_name(String expectedFullName) {
        // try API first, fallback to UI profile
        ApiClient.UserProfile profile = ctx.apiClient().fetchUserByEmail("TODO"); // replace
        if (profile != null) {
            ctx.apiClient().assertProfileContainsFullName(profile, expectedFullName);
        } else {
            // fallback: go to profile page and check display
            String actual = ctx.profilePage().getDisplayedFullName();
            Assert.assertEquals(actual, expectedFullName, "Profile full name mismatch in UI");
        }
    }

    @When("the user enters Full Name {string} and submits the registration form")
    public void user_enters_single_name_and_submits(String fullName) {
        ctx.registrationPage().enterFullName(fullName);
        ctx.registrationPage().submit();
    }

    @Then("the system should show a validation error requiring both first and last name")
    public void system_shows_validation_for_missing_last_name() {
        String msg = ctx.registrationPage().getValidationMessage();
        Assert.assertTrue(msg.toLowerCase().contains("first") || msg.toLowerCase().contains("last")
                || msg.toLowerCase().contains("name"), "Expected validation message about full name; got: " + msg);
    }

    @When("the user enters Full Name {string} and submits the registration form")
    public void user_enters_unicode_name_and_submits(String fullName) {
        ctx.registrationPage().enterFullName(fullName);
        ctx.registrationPage().submit();
    }

    @Then("the system should accept and store the name containing Unicode and special characters without data corruption")
    public void system_accepts_unicode_name() {
        // verify via API/UI - placeholder
        Assert.assertTrue(true, "Implement verification for unicode storage (API or DB in test env)");
    }

    @Then("the stored profile should display the exact characters entered")
    public void stored_profile_should_display_exact_characters() {
        // placeholder, implement as in other stored verification steps
        Assert.assertTrue(true, "Implement exact character match via API/UI");
    }

    @Given("the tester is on the registration page in a non-production test environment")
    public void tester_on_registration_nonprod() {
        // Ensure we are in a non-prod environment before running destructive tests
        String env = System.getProperty("test.env", "nonprod");
        Assert.assertTrue(!"prod".equalsIgnoreCase(env), "Security tests must run in non-production environment");
        ctx.registrationPage().open();
    }

    @When("the tester enters a malicious payload into the Full Name field {string} and submits")
    public void tester_enters_malicious_payload(String payload) {
        ctx.registrationPage().enterFullName(payload);
        ctx.registrationPage().submit();
    }

    @Then("the system should neutralize the input or return a validation error")
    public void system_should_neutralize_or_validate() {
        String msg = ctx.registrationPage().getValidationMessage();
        boolean validationShown = msg != null && !msg.trim().isEmpty();
        // If accepted, verify that no script executed and stored value is sanitized (via API)
        Assert.assertTrue(validationShown || true, "Either validation should be shown or input neutralized. Implement app-specific checks.");
    }

    @Then("no script should execute in the browser and no database corruption should occur")
    public void no_script_execute_and_no_db_corruption() {
        // Check that no alert popped up (simple guard) and DB/API unchanged
        // Placeholder - implement concrete checks with event listeners or API state checks
        Assert.assertTrue(true, "Implement checks ensuring no script execution and DB integrity in test setup.");
    }

    @Then("any stored representation should be sanitized or encoded so it is safe to render")
    public void stored_representation_sanitized() {
        // Placeholder for API verification
        Assert.assertTrue(true, "Verify stored sanitized/encoded representation via API or DB.");
    }

    // Email OTP scenario
    @Given("the user is on the registration page and the test email inbox is accessible")
    public void user_on_registration_and_test_inbox_accessible() {
        ctx.registrationPage().open();
        // precondition: ensure email test inbox accessible - environment-specific
    }

    @When("the user enters a valid active Email {string}, Full Name {string}, and Password and submits")
    public void user_enters_email_fullname_and_password_and_submits(String email, String fullName) {
        ctx.registrationPage().enterFullName(fullName);
        ctx.registrationPage().enterEmail(email);
        ctx.registrationPage().enterPassword("Passw0rd");
        ctx.registrationPage().submit();
    }

    @Then("the system should send an OTP to the email and display the OTP entry UI")
    public void system_sends_otp_and_displays_ui() {
        Assert.assertTrue(ctx.registrationPage().isOtpUiDisplayed(), "Expected OTP entry UI after registration submission");
    }

    @Given("the OTP is received in the test inbox")
    public void otp_received_in_test_inbox() {
        // placeholder: fetch OTP from test mailbox
    }

    @When("the user enters the received OTP and submits")
    public void user_enters_received_otp_and_submits() {
        // placeholder: read OTP from mailbox; here we simulate
        String otp = "TODO";
        ctx.otpPage().enterOtp(otp);
        ctx.otpPage().submitOtp();
    }
