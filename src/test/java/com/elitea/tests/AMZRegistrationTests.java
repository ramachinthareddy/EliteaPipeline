package com.elitea.tests;

import com.elitea.pages.AccountPage;
import com.elitea.pages.OTPPage;
import com.elitea.pages.RegistrationPage;
import com.elitea.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * AMZRegistrationTests implements the scenarios described in the Gherkin feature.
 * Tests are implemented as TestNG tests that use POM classes above.
 * NOTE: Locators and URL values are placeholders and must be adapted to the AUT.
 */
public class AMZRegistrationTests {

    private WebDriver driver;
    private RegistrationPage registrationPage;
    private OTPPage otpPage;
    private AccountPage accountPage;

    private final String baseUrl = "https://example.com/register"; // TODO: replace with actual app URL

    @BeforeClass
    public void setUp() {
        // Ensure CHROME_DRIVER env or system property is set in CI
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        registrationPage = new RegistrationPage(driver);
        otpPage = new OTPPage(driver);
        accountPage = new AccountPage(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Background: Given the user is on the registration page
    private void givenUserIsOnRegistrationPage() {
        registrationPage.open(baseUrl);
    }

    // AMZ_REG_TC_001 - Successful registration with email + OTP
    @Test(description = "AMZ_REG_TC_001 - Successful registration with email and correct OTP")
    public void amzRegTc001_successfulRegistrationWithEmailAndOtp() {
        givenUserIsOnRegistrationPage();

        // Given I am a new user on the registration page
        // When I enter First Name "John", Last Name "Doe", Email ... and Password ... and submit
        registrationPage.enterFirstName("John");
        registrationPage.enterLastName("Doe");
        String email = "john.doe.test+001@example.com";
        registrationPage.enterEmail(email);
        registrationPage.enterPassword("SecureP@ss123");
        registrationPage.submitRegistration();

        // Then the system should send an OTP to the provided email and show an OTP verification screen
        Assert.assertTrue(registrationPage.isOtpSentBannerVisible(), "OTP sent banner should be visible");

        // Simulate OTP issuance by test framework (in real test fetch from mailbox)
        TestUtils.storeOtpFor(email, "000000"); // In real tests, remove store and fetch real OTP

        // Given I have received the OTP sent to ...
        String otp = TestUtils.fetchOtpFor(email);
        Assert.assertNotNull(otp, "OTP should be available in test inbox");

        // When I enter the correct OTP and submit
        otpPage.enterOtp(otp);
        otpPage.submitOtp();

        // Then the account page should be displayed with stored name/contact
        Assert.assertTrue(accountPage.isAt(), "Account page should be displayed after successful OTP verification");
        Assert.assertEquals(accountPage.getStoredName(), "John Doe", "Stored name should match the name provided during registration");
        Assert.assertTrue(accountPage.getStoredContact().contains("john.doe.test+001@example.com"), "Stored contact should contain the registered email");
    }
