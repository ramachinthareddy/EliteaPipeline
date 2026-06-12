package com.elitea.utils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * TestUtils provides helper methods such as OTP retrieval simulation.
 * In real test harness, connect to test email/SMS gateway. Here we provide
 * pluggable methods and simple in-memory mock for examples.
 */
public class TestUtils {

    // Simple in-memory store for OTPs that tests or test doubles can use.
    private static final Map<String, String> otpStore = new HashMap<>();
    private static final Map<String, Instant> otpIssuedAt = new HashMap<>();

    public static void storeOtpFor(String contact, String otp) {
        otpStore.put(contact, otp);
        otpIssuedAt.put(contact, Instant.now());
    }

    public static String fetchOtpFor(String contact) {
        // Replace this with real mailbox/SMS gateway integration when available.
        return otpStore.get(contact);
    }

    public static void clearOtpFor(String contact) {
        otpStore.remove(contact);
        otpIssuedAt.remove(contact);
    }

    public static boolean isOtpExpired(String contact, int ttlSeconds) {
        Instant issued = otpIssuedAt.get(contact);
        if (issued == null) return true;
        return Instant.now().isAfter(issued.plusSeconds(ttlSeconds));
    }
}
