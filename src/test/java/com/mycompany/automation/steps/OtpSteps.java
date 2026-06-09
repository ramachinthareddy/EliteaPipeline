package com.mycompany.automation.steps;

import com.mycompany.automation.context.TestContext;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class OtpSteps {
    private final TestContext ctx = new TestContext();

    @Then("the user should be redirected to their profile page")
    public void user_redirected_to_profile() {
        // placeholder: verify we are on profile page
        Assert.assertTrue(ctx.profilePage().getDisplayedFullName() != null, "Expected profile page to be visible");
    }
}
