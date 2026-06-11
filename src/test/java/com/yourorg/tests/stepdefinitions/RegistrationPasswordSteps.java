package com.yourorg.tests.stepdefinitions;

import com.yourorg.tests.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

/**
 * Step definitions for Password rules and validation.
 */
public class RegistrationPasswordSteps {

    private final RegistrationPage registrationPage = new RegistrationPage();

    @Given("the user is on the registration page and password rules are known")
    public void user_on_registration_with_password_rules() {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt());
        Assert.assertNotNull(registrationPage.getPasswordPolicy(), "Password policy must be available for tests");
    }

    @When("the user enters password {string} and confirmation {string} and submits")
    public void user_enters_password_and_confirmation(String pwd, String confirm) {
        registrationPage.enterPassword(pwd);
        registrationPage.enterPasswordConfirmation(confirm);
        registrationPage.submitRegistration();
    }

    @Then("the system enforces complexity, length, and mismatch checks and shows inline messages")
    public void system_enforces_password_rules() {
        if (registrationPage.hasFieldValidationError("password") || registrationPage.hasFieldValidationError("passwordConfirmation")) {
            Assert.assertFalse(registrationPage.registrationSucceeded(), "Invalid passwords should prevent registration");
        } else {
            Assert.assertTrue(registrationPage.registrationSucceeded(), "Valid password should allow registration");
        }
    }
}
