package com.automation.framework.pages;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.framework.locators.ServiceNowCreateIncidentLocators;

import java.time.Duration;

public class ServiceNowIncidentPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Direct Create Incident URL
    // private static final String CREATE_INCIDENT_URL = "https://gditsharedtest.servicenowservices.com/now/nav/ui/classic/params/target/incident.do";
    private static final String CREATE_INCIDENT_URL = "https://gditsharedtest.servicenowservices.com/now/nav/ui/classic/params/target/u_incident_snts.do%3Fsys_id%3D-1%26sysparm_stack%3Du_incident_snts_list.do";

    public ServiceNowIncidentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // =====================================================
    // Navigate directly to Create New Incident
    // =====================================================
    public void goToCreateNewIncident() {

        driver.switchTo().defaultContent();

        driver.get(CREATE_INCIDENT_URL);

        waitForPageReady();

    }

    // =====================================================
    // Wait for Create Incident form
    // =====================================================
    public void waitForCreateNewIncidentForm() {

        System.out.println("DEBUG URL BEFORE WAIT: " + driver.getCurrentUrl());
        System.out.println("DEBUG TITLE BEFORE WAIT: " + driver.getTitle());

        wait.until(d -> {
            String url = d.getCurrentUrl();
            String title = d.getTitle();

            System.out.println("Checking URL: " + url);
            System.out.println("Checking TITLE: " + title);

            return title.contains("Incident");
        });

        System.out.println("=== Incident page loaded successfully ===");
    }

    // =====================================================
    // Public verification helper (optional)
    // =====================================================
    public boolean isIncidentPageLoaded() {
        try {
            waitForCreateNewIncidentForm();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // =====================================================
    // Helpers
    // =====================================================
    private void waitForPageReady() {
        wait.until(d -> {
            Object state = ((JavascriptExecutor) d)
                    .executeScript("return document.readyState");
            return "complete".equals(String.valueOf(state));
        });
    }

    public void populateIncident() {

        // Always reset frame first
        driver.switchTo().defaultContent();

        try {
            // Many ServiceNow instances use this iframe
            driver.switchTo().frame("gsft_main");
        } catch (Exception e) {
            // If no iframe, continue normally
        }

        WebElement affectedUser = driver.findElement(ServiceNowCreateIncidentLocators.AFFECTED_USER);
        affectedUser.clear();
        affectedUser.sendKeys("Tester Customer Test 1");

        driver.switchTo().defaultContent();
    }

}