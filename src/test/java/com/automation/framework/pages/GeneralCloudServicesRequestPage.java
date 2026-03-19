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
import com.automation.framework.locators.GeneralCloudServicesRequestLocators;

public class GeneralCloudServicesRequestPage extends UiActions {

    public GeneralCloudServicesRequestPage(WebDriver driver) {
        super(driver);
    }

    public void fillAndSubmitRequest(String requestedFor,
            String shortDescription,
            String additionalComments) {

        System.out.println("[SP] Filling General Cloud Services Request form");

        typeRequestedFor(requestedFor);

        type(GeneralCloudServicesRequestLocators.SHORT_DESCRIPTION, shortDescription);
        type(GeneralCloudServicesRequestLocators.ADDITIONAL_COMMENTS, additionalComments);

        wait.until(ExpectedConditions.elementToBeClickable(
                GeneralCloudServicesRequestLocators.SUBMIT_BUTTON));

        click(GeneralCloudServicesRequestLocators.SUBMIT_BUTTON);

        System.out.println("[SP] Submitted General Cloud Services Request");
    }


    private void typeRequestedFor(String name) {
        System.out.println("[SP] Filling Requested For: " + name);

        // 1. Open dropdown (real Select2 UI)
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        GeneralCloudServicesRequestLocators.REQUESTED_FOR_DROPDOWN));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", dropdown);

        dropdown.click();

        System.out.println("[SP] Requested For dropdown opened");

        
        // 2. Type using keyboard (Select2 expects keyboard events)
        Actions actions = new Actions(driver);

        actions
                .sendKeys(name)
                .pause(Duration.ofMillis(800)) // wait for results to load
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        System.out.println("[SP] Typed and selected: " + name);

        // 4. Optional: ensure dropdown closed (stability)
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(By.id("select2-drop-mask")));
    }

}