package com.elitea.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

/**
 * Simple DriverFactory using ThreadLocal to support parallel runs.
 * Update ChromeDriver setup to use WebDriverManager or your CI-managed binary.
 */
public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // Simple Chrome setup; replace or extend for other browsers
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            WebDriver wd = new ChromeDriver(options);
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wd.manage().window().maximize();
            driver.set(wd);
        }
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver wd = driver.get();
        if (wd != null) {
            wd.quit();
            driver.remove();
        }
    }
}
