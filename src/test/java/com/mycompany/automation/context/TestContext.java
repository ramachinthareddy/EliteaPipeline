package com.mycompany.automation.context;

import com.mycompany.automation.api.ApiClient;
import com.mycompany.automation.pages.RegistrationPage;
import com.mycompany.automation.pages.OtpPage;
import com.mycompany.automation.pages.ProfilePage;

public class TestContext {
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final OtpPage otpPage = new OtpPage();
    private final ProfilePage profilePage = new ProfilePage();
    private final ApiClient apiClient = new ApiClient();

    public RegistrationPage registrationPage() { return registrationPage; }
    public OtpPage otpPage() { return otpPage; }
    public ProfilePage profilePage() { return profilePage; }
    public ApiClient apiClient() { return apiClient; }
}
