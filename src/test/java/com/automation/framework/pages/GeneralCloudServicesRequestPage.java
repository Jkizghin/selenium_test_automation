package com.automation.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import com.automation.framework.base.UiActions;
// import com.automation.framework.locators.GeneralCloudServicesRequestLocators;
import com.automation.framework.locators.GeneralCloudServicesRequestLocators;
import com.automation.framework.locators.GeneralCloudServicesSubmittedRequestLocators;

public class GeneralCloudServicesRequestPage extends UiActions {

        public GeneralCloudServicesRequestPage(WebDriver driver) {
                super(driver);
        }

        public void openCloudServiceRequestDetails() {
                click(GeneralCloudServicesSubmittedRequestLocators.CLOUD_SERVICE_REQUEST_LINK);
        }

        public void validateGeneralCloudServiceRequestWidgets() {
                assertElementDisplayed(GeneralCloudServicesRequestLocators.REQUESTED_BY, "Requested By");
                assertElementDisplayed(GeneralCloudServicesRequestLocators.REQUESTED_FOR, "Requested For");
                assertElementDisplayed(GeneralCloudServicesRequestLocators.SHORT_DESCRIPTION, "Short Description");
                assertElementDisplayed(GeneralCloudServicesRequestLocators.ADDITIONAL_COMMENTS, "Additional Comments");
        }

}