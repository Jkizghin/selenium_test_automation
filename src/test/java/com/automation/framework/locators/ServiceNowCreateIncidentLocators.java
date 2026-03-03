package com.automation.framework.locators;

import org.openqa.selenium.By;

public class ServiceNowCreateIncidentLocators {
   public static final By IFRAME = By.id("gsft_main"); // If login is inside frame

   public static final By AFFECTED_USER = By.id("sys_display.u_incident_snts.caller_id");
   public static final By CATEGORY = By.id("u_incident_snts.category");
   public static final By SUBCATEGORY = By.id("u_incident_snts.subcategory");
   public static final By SHORT_DESCRIPTION = By.id("u_incident_snts.short_description");
   public static final By SAVE_BUTTON = By.name("sysverb_insert_and_stay");

}