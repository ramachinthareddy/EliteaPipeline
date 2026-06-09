package com.mycompany.automation.api;

import org.testng.Assert;

public class ApiClient {
    private final String baseApi = System.getProperty("api.base", "https://api.example.com");

    public UserProfile fetchUserByEmail(String email) {
        // TODO: implement real HTTP call in your test environment (e.g., using RestAssured or HttpClient)
        // Placeholder stub - return null for now
        return null;
    }

    // Simple DTO
    public static class UserProfile {
        public String fullName;
        public String email;
        public String phone;
    }

    public void assertProfileContainsFullName(UserProfile profile, String expectedFullName) {
        Assert.assertNotNull(profile, "Profile was not returned by API");
        Assert.assertEquals(profile.fullName, expectedFullName, "Stored profile full name mismatch");
    }
}
