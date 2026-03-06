package com.automation.framework.locators;

import org.openqa.selenium.By;

public class ServiceNowCreateIncidentLocators {
   public static final By IFRAME = By.id("gsft_main"); // If login is inside frame

   public static final By AFFECTED_USER = By.id("sys_display.u_incident_snts.caller_id");
   public static final By CATEGORY = By.id("u_incident_snts.category");
   public static final By SUBCATEGORY = By.id("u_incident_snts.subcategory");
   public static final By SHORT_DESCRIPTION = By.id("u_incident_snts.short_description");
   public static final By DESCRIPTION = By.id("u_incident_snts.description");
   public static final By SAVE_BUTTON = By.id("sysverb_insert_and_stay");
   public static final By SAVE_BUTTON2 =By.cssSelector("#sysverb_insert_and_stay, #sysverb_update_and_stay");
   
   // In Progress flow
   public static final By STATE = By.id("u_incident_snts.state");

   public static final By ASSIGNMENT_GROUP = By.id("sys_display.u_incident_snts.assignment_group");

   public static final By ASSIGNED_TO = By.id("sys_display.u_incident_snts.assigned_to");

   // Future (when you move to Resolve flow)
   public static final By RESOLUTION_INFORMATION_TAB =By.xpath("//span[text()='Resolution Information']");
   public static final By RESOLUTION_CODE = By.id("u_incident_snts.close_code");
   public static final By RESOLUTION_NOTES = By.id("u_incident_snts.close_notes");
}