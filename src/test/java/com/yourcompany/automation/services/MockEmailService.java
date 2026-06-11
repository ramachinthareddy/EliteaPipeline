package com.yourcompany.automation.services;

import java.util.HashMap;
import java.util.Map;

public class MockEmailService {
    // simple in-memory store mapping email -> last OTP
    private static final Map<String, String> otpStore = new HashMap<>();

    public static void sendOtp(String email, String otp) {
        otpStore.put(email.toLowerCase(), otp);
    }

    public static String fetchLatestOtp(String email) {
        return otpStore.get(email.toLowerCase());
    }

    public static void clear() {
        otpStore.clear();
    }
}
