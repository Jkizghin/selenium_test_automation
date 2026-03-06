package com.automation.framework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.automation.framework.locators.ServiceNowCreateIncidentLocators.*;
import com.automation.framework.base.UiActions;
import com.automation.framework.locators.ServiceNowCreateIncidentLocators;

public class ServiceNowIncidentPage extends UiActions {

    private static final String CREATE_INCIDENT_URL = "https://gditsharedtest.servicenowservices.com/now/nav/ui/classic/params/target/u_incident_snts.do?sys_id=-1";

    public ServiceNowIncidentPage(WebDriver driver) {
        super(driver);
    }
    // ---------------- Public API ----------------

    /** Navigate to Create New Incident page and wait until it is loaded. */
    public void goToCreateNewIncident() {

        driver.get(CREATE_INCIDENT_URL);
        waitForPageReady();

        System.out.println("[SN] Locating gsft_main iframe inside shadow DOM");

        WebElement frame = wait.until(d -> getGsftMainFrame());

        // IMPORTANT: reset context before switching
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frame);

        System.out.println("[SN] Switched into gsft_main iframe");

        // quick sanity check (should be 1 when inside correct iframe)
        System.out.println("[SN] short desc count=" +
                driver.findElements(ServiceNowCreateIncidentLocators.SHORT_DESCRIPTION).size());

        waitForIncidentFormToLoad();

        System.out.println("[SN] Create New Incident page loaded. Title=" + safeTitle());
        System.out.println("[SN] URL=" + driver.getCurrentUrl());
    }

    // Types a value into field located by the given locator
    private void typeInto(By by, String value) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
        try {
            el.click();
        } catch (Exception ignored) {
        }
        try {
            el.clear();
        } catch (Exception ignored) {
        }

        el.sendKeys(value);
    }

    public void populateIncident(String affectedUser, String category, String subcategory, String shortDesc,
            String description) {
        // Ensure we are on the right page
        waitForIncidentFormToLoad();

        if (affectedUser != null && !affectedUser.isBlank()) {
            typeInto(ServiceNowCreateIncidentLocators.AFFECTED_USER, affectedUser);

            // Often ServiceNow requires ENTER / selection from typeahead.
            // This JS triggers blur/change; you can also send Keys.ENTER if you prefer.
            blurActiveElement();
            smallWait(300);
        }
        // Category dropdown
        selectByVisibleText(ServiceNowCreateIncidentLocators.CATEGORY, category);
        smallWait(500);

        // Subcategory dropdown
        selectByVisibleText(ServiceNowCreateIncidentLocators.SUBCATEGORY, subcategory);
        smallWait(500);

        // Short Description
        if (shortDesc != null && !shortDesc.isBlank()) {
            typeInto(ServiceNowCreateIncidentLocators.SHORT_DESCRIPTION, shortDesc);
        }

        // Description
        if (description != null && !description.isBlank()) {
            typeInto(ServiceNowCreateIncidentLocators.DESCRIPTION, description);
        }
        System.out.println("[SN] Incident fields populated successfully.");
    }

    public void clickSaveButton() {
        click(ServiceNowCreateIncidentLocators.SAVE_BUTTON);
        System.out.println("[SN] Save button clicked.");
    }

    public void updateIncidentAssignment(String state, String assignmentGroup, String assignedTo) {

        // Set State
        selectByVisibleText(ServiceNowCreateIncidentLocators.STATE, state);
        smallWait(500);

        // Set Assignment Group
        type(ServiceNowCreateIncidentLocators.ASSIGNMENT_GROUP, assignmentGroup);
        smallWait(500);

        // Set Assigned To
        type(ServiceNowCreateIncidentLocators.ASSIGNED_TO, assignedTo);
        smallWait(500);

        System.out.println("[SN] Incident updated: State, Assignment Group, and Assigned To set.");
    }

    public void clickSaveButtonAfterUpdate() {
        click(ServiceNowCreateIncidentLocators.SAVE_BUTTON2);
        System.out.println("[SN] Save button clicked after update.");
    }

    public void resolveIncident(String resolvedState, String resolutionCode, String resolutionNotes) {

        // Set State to Resolved
        selectByVisibleText(ServiceNowCreateIncidentLocators.STATE, resolvedState);
        smallWait(1000);

        // Click Resolution Information tab
        click(ServiceNowCreateIncidentLocators.RESOLUTION_INFORMATION_TAB);
        smallWait(1000);

        // Scroll to Resolution Code
        scrollToElement(ServiceNowCreateIncidentLocators.RESOLUTION_CODE);
        smallWait(500);

        // Set Resolution Code
        selectByVisibleText(ServiceNowCreateIncidentLocators.RESOLUTION_CODE, resolutionCode);
        smallWait(500);

        // Set Resolution Notes
        type(ServiceNowCreateIncidentLocators.RESOLUTION_NOTES, resolutionNotes);
        smallWait(500);

        System.out.println("[SN] Resolution Information entered.");
    }
    // ---------------- Core helpers ----------------

    private void waitForPageReady() {
        try {
            wait.until(d -> "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
        } catch (Exception ignored) {
            // don’t fail on this; ServiceNow can keep async loading even after
            // readyState=complete
        }
    }

    /**
     * Wait until we can see a form container OR a known field, in any context.
     * This is the “main gate” before trying to interact with fields.
     */
    public void waitForIncidentFormToLoad() {

        System.out.println("[SN] Waiting for incident form to load");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                ServiceNowCreateIncidentLocators.SHORT_DESCRIPTION));

        wait.until(ExpectedConditions.elementToBeClickable(
                ServiceNowCreateIncidentLocators.SHORT_DESCRIPTION));

        System.out.println("[SN] Incident form detected");
    }

    // HELPER METHOD

    private WebElement getGsftMainFrame() {
        // this method is called repeatedly by wait.until(...)
        driver.switchTo().defaultContent();
        System.out.println("[DEBUG] Switched to default content");

        try {
            // STEP 1: host1 (macroponent)
            WebElement host1 = driver.findElement(
                    By.cssSelector("*[app-id='a84adaf4c700201072b211d4d8c260b7']"));
            System.out.println("[DEBUG] Found host1 (macroponent with app-id)");

            // STEP 2: shadowRoot1
            SearchContext shadowRoot1 = host1.getShadowRoot();
            System.out.println("[DEBUG] Entered shadowRoot1");

            // STEP 3: host2
            WebElement host2 = shadowRoot1.findElement(By.cssSelector("sn-canvas-appshell-root"));
            System.out.println("[DEBUG] Found sn-canvas-appshell-root");

            // STEP 4: shadowRoot2
            SearchContext shadowRoot2 = host2.getShadowRoot();
            System.out.println("[DEBUG] Entered shadowRoot2");

            // STEP 5: Find sn-canvas-appshell-layout via slot
            WebElement host3 = wait.until(d -> {
                try {
                    WebElement slot = shadowRoot2.findElement(By.cssSelector("slot"));

                    @SuppressWarnings("unchecked")
                    List<WebElement> assigned = (List<WebElement>) ((JavascriptExecutor) driver)
                            .executeScript("return arguments[0].assignedElements({flatten:true});", slot);

                    for (WebElement el : assigned) {
                        if ("sn-canvas-appshell-layout".equalsIgnoreCase(el.getTagName())) {
                            return el;
                        }
                    }
                    return null;

                } catch (Exception e) {
                    return null;
                }
            });

            System.out.println("[DEBUG] Found sn-canvas-appshell-layout (via slot)");

            // STEP 6: shadowRoot3
            SearchContext shadowRoot3 = host3.getShadowRoot();
            System.out.println("[DEBUG] Entered shadowRoot3");

            // STEP 7: host4 = sn-polaris-layout (VIA SLOT) ✅
            WebElement host4 = wait.until(d -> {
                try {
                    // find a slot inside shadowRoot3
                    WebElement slot = shadowRoot3.findElement(By.cssSelector("slot"));

                    // get assigned elements from slot and pick the one that matches
                    // sn-polaris-layout
                    Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                            "const slot = arguments[0];" +
                                    "const sel = arguments[1];" +
                                    "const els = slot.assignedElements({flatten:true});" +
                                    "return els.find(e => e.matches(sel)) || null;",
                            slot, "sn-polaris-layout");

                    return (WebElement) result; // null -> wait retries
                } catch (Exception e) {
                    return null; // wait retries
                }
            });
            System.out.println("[DEBUG] Found sn-polaris-layout (via slot)");

            // STEP 8: shadowRoot4
            SearchContext shadowRoot4 = host4.getShadowRoot();
            System.out.println("[DEBUG] Entered shadowRoot4");

            // STEP 9: Find iframe#gsft_main (WAIT) ✅ (slotted / light DOM, NOT inside
            // shadowRoot4)
            WebElement iframe = wait.until(d -> {
                try {
                    List<WebElement> frames = host4.findElements(By.cssSelector(
                            "iframe#gsft_main, iframe[name='gsft_main'], iframe[title='Main Content']"));
                    return frames.isEmpty() ? null : frames.get(0);
                } catch (Exception e) {
                    return null; // keep retrying
                }
            });
            System.out.println("[DEBUG] Found iframe gsft_main");
            return iframe;

        } catch (Exception e) {
            System.out.println("[DEBUG] getGsftMainFrame() failed at: "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
            // OK only if caller uses wait.until(...)
            return null;
        }
    }

}