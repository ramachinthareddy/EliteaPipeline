package com.yourorg.tests.stepdefinitions;

import com.yourorg.tests.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

/**
 * Step definitions for CAPTCHA and bot prevention validation.
 */
public class RegistrationCaptchaSteps {

    private final RegistrationPage registrationPage = new RegistrationPage();

    @Given("the user is on the registration page and CAPTCHA is visible")
    public void user_on_registration_with_captcha() {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt());
        Assert.assertTrue(registrationPage.isCaptchaVisible(), "CAPTCHA should be visible for registration");
    }

    @When("the user fails the CAPTCHA or submits automated requests beyond limits")
    public void user_fails_captcha_or_exceeds_limits() {
        registrationPage.simulateCaptchaFailure();
        registrationPage.submitRegistration();
    }

    @Then("the system prevents registration, shows an anti-bot message, and optionally throttles further attempts")
    public void system_prevents_registration_for_bot_behavior() {
        Assert.assertTrue(registrationPage.hasInlineMessage("CAPTCHA verification failed") || registrationPage.isThrottled(),
                "Expected anti-bot behavior to be enforced");
        Assert.assertFalse(registrationPage.registrationSucceeded());
    }
}
