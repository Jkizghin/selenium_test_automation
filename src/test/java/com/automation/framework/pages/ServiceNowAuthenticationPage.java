package com.automation.framework.pages;

import com.automation.framework.locators.ServiceNowAuthenticationLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ServiceNowAuthenticationPage {

private WebDriver driver;

public ServiceNowAuthenticationPage(WebDriver driver) {
this.driver = driver;
}

public void login(String username, String password) {

// Always reset frame first
driver.switchTo().defaultContent();

try {
// Many ServiceNow instances use this iframe
driver.switchTo().frame("gsft_main");
} catch (Exception e) {
// If no iframe, continue normally
}

WebElement userField =
driver.findElement(ServiceNowAuthenticationLocators.USERNAME);
userField.clear();
userField.sendKeys(username);

WebElement passField =
driver.findElement(ServiceNowAuthenticationLocators.PASSWORD);
passField.clear();
passField.sendKeys(password);

driver.findElement(
ServiceNowAuthenticationLocators.LOGIN_BUTTON).click();

driver.switchTo().defaultContent();
}
}
