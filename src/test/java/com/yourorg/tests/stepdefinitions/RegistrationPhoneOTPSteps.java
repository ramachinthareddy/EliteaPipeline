package com.yourorg.tests.stepdefinitions;

import com.yourorg.tests.helpers.SMSService;
import com.yourorg.tests.pages.RegistrationPage;
import com.yourorg.tests.pages.OTPPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

/**
 * Step definitions for Phone number and SMS OTP scenarios.
 */
public class RegistrationPhoneOTPSteps {

    private final RegistrationPage registrationPage = new RegistrationPage();
    private final OTPPage otpPage = new OTPPage();

    @Given("the user is on the registration page and a test phone number is available")
    public void user_on_registration_with_test_phone() {
        registrationPage.open();
        Assert.assertTrue(registrationPage.isAt());
        Assert.assertTrue(SMSService.isTestNumberAvailable(), "Test SMS service must be available");
    }

    @When("the user enters {string} as Phone and submits")
    public void user_enters_phone_and_submits(String phone) {
        registrationPage.enterPhone(phone);
        registrationPage.submitRegistration();
    }

    @Then("the system sends an SMS OTP to that number and prompts the user to enter the OTP")
    public void system_sends_sms_otp_and_prompts() {
        Assert.assertTrue(otpPage.isAt(), "OTP entry page should be displayed");
        String otp = SMSService.waitForOtpForLastSentNumber();
        Assert.assertNotNull(otp, "OTP should be delivered to test phone number");
    }

    @When("the user enters a malformed phone such as {string} and attempts to submit")
    public void user_enters_malformed_phone_and_submits(String malformed) {
        registrationPage.enterPhone(malformed);
        registrationPage.submitRegistration();
    }

    @Then("the system validates phone format inline, prevents submission and does not send SMS")
    public void system_rejects_malformed_phone() {
        Assert.assertTrue(registrationPage.hasFieldValidationError("phone"), "Malformed phone should show validation error");
        Assert.assertFalse(otpPage.isAt(), "OTP should not be sent for malformed phone");
    }

    @When("the user enters an expired OTP and submits")
    public void user_enters_expired_otp_and_submits() {
        String otp = SMSService.getLatestOtpForLastSentNumber();
        Assert.assertNotNull(otp);
        // simulate expiration by manipulating test service or waiting beyond expiry in test environment
        SMSService.expireLatestOtpForLastSentNumber();
        otpPage.enterOtp(otp);
        otpPage.submitOtp();
    }

    @Then("the system rejects the expired OTP with appropriate messaging and allows resend")
    public void system_rejects_expired_otp_and_allows_resend() {
        Assert.assertTrue(otpPage.hasExpiredOtpMessage(), "Expired OTP should show expired message");
        Assert.assertFalse(otpPage.verificationSucceeded());
        Assert.assertTrue(otpPage.canResendOtp(), "User should be able to request a resend");
    }
}
