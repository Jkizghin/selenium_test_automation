package com.automation.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver createDriver() {

        if (driver == null) {

            // ✅ Use local EdgeDriver (no WebDriverManager)
            System.setProperty("webdriver.edge.driver", "C:\\Drivers\\msedgedriver.exe");

            EdgeOptions options = new EdgeOptions();

            // ✅ Create unique temp profile
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String baseTemp = System.getProperty("java.io.tmpdir");
            String profilePath = baseTemp + "\\selenium-profiles\\profile_" + timestamp;

            new File(profilePath).mkdirs();

            options.addArguments("--user-data-dir=" + profilePath);
            options.addArguments("--no-first-run");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--disable-extensions");
            options.addArguments("--start-maximized");

            driver = new EdgeDriver(options);
        }

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}