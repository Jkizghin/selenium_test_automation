package com.automation.framework.locators;

import org.openqa.selenium.By;

public class GeneralCloudServicesRequestLocators {

   
 // Requested By
public static final By REQUESTED_BY =
By.xpath("//*[contains(text(),'Requested By')]");
// Requested For
public static final By REQUESTED_FOR =
By.xpath("//b[text()='Requested For']/ancestor::label/following-sibling::div[1]");

// Short Description
public static final By SHORT_DESCRIPTION =
By.xpath("//b[text()='Short Description']/ancestor::label/following-sibling::div[1]");

// Additional Comments
public static final By ADDITIONAL_COMMENTS =
By.xpath("//b[text()='Additional Comments']/ancestor::label/following-sibling::div[1]");
}
