package com.yourcompany.automation.services;

import java.util.HashMap;
import java.util.Map;

public class MockSmsService {
    private static final Map<String, String> otpStore = new HashMap<>();

    public static void sendOtp(String mobile, String otp) {
        otpStore.put(normalize(mobile), otp);
    }

    public static String fetchLatestOtp(String mobile) {
        return otpStore.get(normalize(mobile));
    }

    public static void clear() {
        otpStore.clear();
    }

    private static String normalize(String mobile) {
        return mobile.replaceAll("[^0-9+]", "");
    }
}
