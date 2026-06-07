package com.automationelitea.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                PROPERTIES.load(input);
            } else {
                throw new IllegalStateException("config.properties not found on classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getBaseUrl() {
        return get("app.url");
    }

    public static String getBrowser() {
        return get("browser");
    }
}
