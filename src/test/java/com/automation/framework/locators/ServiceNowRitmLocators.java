package com.automation.framework.locators;

import org.openqa.selenium.By;

public class ServiceNowRitmLocators {

   public static final By STATE = By.id("sc_req_item.state");
   // public static final By CATALOG_TASKS_TAB = By.xpath("//span[text()='Catalog
   // Tasks']");
   public static final By CATALOG_TASKS_TAB = By.xpath("//*[contains(normalize-space(.), 'Catalog Tasks')]");
   // public static final By CATALOG_TASK
   public static final By CATALOG_TASK = By.xpath("//a[contains(text(),'SCTASK')]");

   public static final By CATALOG_TASK_STATE = By.id("sc_task.state");
   public static final By SAVE_BUTTON = By.cssSelector("#sysverb_update_and_stay");

   public static final By WORK_NOTES = By.id("activity-stream-textarea");
   public static final By POST_BUTTON = By.xpath("//button[normalize-space()='Post']");

   public static final By CLOSE_TASK_BUTTON = By.id("close_sc_task");

   public static final By RITM_STATE = By.id("sc_req_item.state");
   public static final By RITM_STAGE = By.id("sys_readonly.sc_req_item.stage");
}