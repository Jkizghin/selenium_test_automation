package com.automation.framework.locators;

import org.openqa.selenium.By;

public class ServiceNowImpersonateUrlLocators {
   public static final By IFRAME = By.id("gsft_main"); // If login is inside frame

   public static final By IMPERSONATE_USER = By.id("sys_display.QUERY:active=true^locked_out=false^web_service_access_only=false^ORweb_service_access_onlyISEMPTY^roles!=admin^identity_type!=ai_agent^ORidentity_type=NULL");
   public static final By IMPERSPONATE_OK_BUTTON = By.id("ok_button");
}