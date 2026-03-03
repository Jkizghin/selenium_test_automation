package com.automation.framework.locators;

import org.openqa.selenium.By;

public class ServiceNowNavigatorLocators {

public static final By ALL_BUTTON =
By.cssSelector("div[role='menuitem'][aria-label='All']");

//Navigator filter input
public static final By NAVIGATOR_FILTER=By.id("filter");

// Generic navigator item by visible text (Incident, Create New, etc.)
public static By NAV_ITEM_BY_TEXT(String text) {
return By.xpath("//div[@role='menuitem' and normalize-space()='" + text + "']");
}
public static By NAV_TOGGLE_BY_LABEL(String label) {
return By.cssSelector(
"div.snf-collapsible-list-header-button[aria-label='" + label + "']"
);
}

}