package com.yourorg.tests.stepdefinitions;

import com.yourorg.tests.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

/**
 * Miscellaneous registration related step definitions.
 */
public class RegistrationMiscSteps {

    private final RegistrationPage registrationPage = new RegistrationPage();

    @Given("the user is on the registration page with referral code {string}")
    public void user_on_registration_with_referral(String code) {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterReferralCode(code);
    }

    @When("the user selects different country locales and continues")
    public void user_selects_locales_and_continues() {
        registrationPage.selectCountryLocale("US");
        registrationPage.selectCountryLocale("FR");
        registrationPage.submitRegistration();
    }

    @Then("the system stores locale and referral metadata with the account")
    public void system_stores_locale_and_referral() {
        Assert.assertTrue(registrationPage.registrationSucceeded());
        Assert.assertNotNull(registrationPage.getStoredLocale());
        Assert.assertNotNull(registrationPage.getStoredReferral());
    }
}
