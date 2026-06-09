package com.yourorg.tests.stepdefinitions;

import com.yourorg.tests.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

/**
 * Step definitions for Date of Birth (DOB) validation scenarios.
 */
public class RegistrationDobSteps {

    private final RegistrationPage registrationPage = new RegistrationPage();

    @Given("the user is on the registration page and DOB field is visible")
    public void user_on_registration_with_dob() {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt());
        Assert.assertTrue(registrationPage.isDobFieldVisible(), "DOB field should be visible on registration form");
    }

    @When("the user enters {string} as Date of Birth and submits")
    public void user_enters_dob_and_submits(String dob) {
        registrationPage.enterDob(dob);
        registrationPage.submitRegistration();
    }

    @Then("the system validates DOB format and age rules, rejecting if underage or malformed")
    public void system_validates_dob_and_age_rules() {
        if (registrationPage.hasFieldValidationError("dob")) {
            Assert.assertFalse(registrationPage.registrationSucceeded(), "Invalid DOB should prevent registration");
        } else {
            // if accepted, ensure age meets minimum requirement
            int age = registrationPage.getRegisteredUserAge();
            Assert.assertTrue(age >= registrationPage.getMinimumAllowedAge(), "Registered user must meet minimum age");
        }
    }
}
