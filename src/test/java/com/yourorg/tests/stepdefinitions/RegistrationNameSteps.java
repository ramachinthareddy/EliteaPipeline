package com.yourorg.tests.stepdefinitions;

import com.yourorg.tests.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

/**
 * Step definitions for Full Name validation and handling scenarios.
 * Assumes RegistrationPage exposes clear, focused methods.
 */
public class RegistrationNameSteps {

    private final RegistrationPage registrationPage = new RegistrationPage(); // adapt DI/constructor if needed

    @Given("the user is on the registration page and not logged in")
    public void user_on_registration_page_not_logged_in() {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt(), "Registration page should be open");
        Assert.assertFalse(registrationPage.isLoggedIn(), "User should not be logged in");
    }

    @Given("the user is on the registration page")
    public void user_on_registration_page() {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt(), "Registration page should be open");
    }

    @Given("the user is on the registration page and the system has a defined max name length")
    public void user_on_registration_page_with_max_length_defined() {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt());
        // optional: fetch config to ensure max length available
        Integer max = registrationPage.getMaxNameLength();
        Assert.assertTrue(max != null && max > 0, "Max name length should be defined for the system under test");
    }

    @When("the user enters {string} as First Name and {string} as Last Name and submits the form")
    public void enters_first_and_last_and_submits(String first, String last) {
        registrationPage.enterFirstName(first);
        registrationPage.enterLastName(last);
        registrationPage.submitRegistration();
    }

    @When("the user enters {string} in First Name, leaves Last Name blank and attempts to submit")
    public void enters_first_leaves_last_blank_and_submits(String first) {
        registrationPage.enterFirstName(first);
        registrationPage.enterLastName(""); // clear
        registrationPage.submitRegistration();
    }

    @When("the user leaves First Name blank, enters {string} as Last Name and submits the form")
    public void leaves_first_enters_last_and_submits(String last) {
        registrationPage.enterFirstName("");
        registrationPage.enterLastName(last);
        registrationPage.submitRegistration();
    }

    @When("the user enters extremely long strings (e.g., {int} characters) in First and Last Name and submits")
    public void enters_extremely_long_names_and_submits(Integer length) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) s.append('A');
        registrationPage.enterFirstName(s.toString());
        registrationPage.enterLastName(s.toString());
        registrationPage.submitRegistration();
    }

    @When("the user enters {string} as First Name and {string} as Last Name and submits")
    public void enters_unicode_names_and_submits(String first, String last) {
        registrationPage.enterFirstName(first);
        registrationPage.enterLastName(last);
        registrationPage.submitRegistration();
    }

    @When("the user enters {string} as First Name and {string} as Last Name and submits")
    public void enters_numeric_names_and_submits(String first, String last) {
        // This signature may duplicate the unicode method; cucumber will bind same method for same pattern.
        registrationPage.enterFirstName(first);
        registrationPage.enterLastName(last);
        registrationPage.submitRegistration();
    }

    @Then("the system accepts the input and stores the full name for account creation")
    public void system_accepts_and_stores_full_name() {
        Assert.assertTrue(registrationPage.registrationSucceeded(), "Registration should succeed with valid full name");
        // verify stored representation if account detail page available
        String storedFullName = registrationPage.getStoredFullName();
        Assert.assertNotNull(storedFullName);
        Assert.assertFalse(storedFullName.trim().isEmpty(), "Stored full name should not be empty");
    }

    @Then("the system shows a validation error requiring the last name and prevents account creation")
    public void system_rejects_missing_last() {
        Assert.assertTrue(registrationPage.hasFieldValidationError("lastName"),
                "Expected validation error for missing last name");
        Assert.assertFalse(registrationPage.registrationSucceeded(), "Registration should be prevented");
    }

    @Then("the system shows a validation error requiring the first name and prevents account creation")
    public void system_rejects_missing_first() {
        Assert.assertTrue(registrationPage.hasFieldValidationError("firstName"),
                "Expected validation error for missing first name");
        Assert.assertFalse(registrationPage.registrationSucceeded(), "Registration should be prevented");
    }

    @Then("the system either truncates to the allowed limit or shows a clear validation error; no crash occurs and the stored name representation conforms to requirements")
    public void system_handles_very_long_names() {
        Assert.assertFalse(registrationPage.crashed(), "System should not crash on very long name input");
        Integer max = registrationPage.getMaxNameLength();
        if (registrationPage.hasFieldValidationError("firstName") || registrationPage.hasFieldValidationError("lastName")) {
            Assert.assertTrue(true, "Input was rejected by validation as expected");
        } else {
            String storedFirst = registrationPage.getStoredFirstName();
            String storedLast = registrationPage.getStoredLastName();
            Assert.assertTrue(storedFirst.length() <= max, "Stored first name should not exceed max length");
            Assert.assertTrue(storedLast.length() <= max, "Stored last name should not exceed max length");
        }
    }

    @Then("the system accepts and correctly stores the name including accents, apostrophes, hyphens and non-Latin characters")
    public void system_accepts_unicode_and_special_chars() {
        Assert.assertTrue(registrationPage.registrationSucceeded(), "Registration should succeed with unicode/special characters");
        String storedFullName = registrationPage.getStoredFullName();
        Assert.assertTrue(storedFullName.contains("José") || storedFullName.contains("O'Connor") || storedFullName.contains("李"),
                "Stored name should retain accents and non-latin characters");
    }

    @Then("the system either rejects the input with a validation message or flags it per the business rule; no malformed storage occurs")
    public void system_handles_numeric_in_names() {
        boolean rejected = registrationPage.hasFieldValidationError("firstName") || registrationPage.hasFieldValidationError("lastName");
        Assert.assertTrue(rejected || registrationPage.registrationSucceeded(), "Either rejected or accepted with flagging");
        if (registrationPage.registrationSucceeded()) {
            // ensure stored form is validated/cleaned
            String stored = registrationPage.getStoredFullName();
            Assert.assertNotNull(stored);
            Assert.assertFalse(stored.contains("<script>"), "Stored name should not contain malformed/unsafe content");
        }
    }
}
