package com.automation.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver createDriver() {
        if (driver != null)
            return driver;

        ChromeOptions options = new ChromeOptions();

        // ✅ IMPORTANT: use a fresh temp profile so it won’t open your company/startup
        // page
        try {
            Path tempProfile = Files.createTempDirectory("selenium-chrome-profile-");
            options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("Could not create temp Chrome profile", e);
        }

        // ✅ prevent “Who’s using Chrome” / first-run screens + reduce enterprise
        // behaviors
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");

        // ✅ disable extensions (company extensions often redirect to company portals)
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");

        // ✅ optional: start clean
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        // timeouts (helps avoid “hang” feeling)
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}