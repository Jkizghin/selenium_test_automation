package com.automation.framework.locators;

import org.openqa.selenium.By;

public class GeneralCloudServicesSubmittedRequestLocators {

    public static final By SHORT_DESCRIPTION = By.id("sp_formfield_short_description");
    public static final By ADDITIONAL_COMMENTS = By.id("sp_formfield_additional_comments");
    public static final By REQUESTED_FOR_DROPDOWN = By.cssSelector("#s2id_sp_formfield_requested_for a.select2-choice");
    
    public static final By SUBMIT_BUTTON = By.id("submit-btn");
    
    public static final By CREATED_RITM = By.xpath("//span[contains(text(),'RITM')]");

}
