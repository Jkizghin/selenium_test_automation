package com.automation.framework.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.framework.base.UiActions;
import com.automation.framework.locators.ServiceNowRitmLocators;

public class ServiceNowRitmPage extends UiActions {

    public ServiceNowRitmPage(WebDriver driver) {
        super(driver);
    }
    // ---------------- Public API ----------------

    public void clickSaveButton() {
        click(ServiceNowRitmLocators.SAVE_BUTTON);
        System.out.println("[SN] Save button clicked.");
    }

    public void updateState(String state) {

        // Set State
        selectByVisibleText(ServiceNowRitmLocators.STATE, state);
        smallWait(500);

        System.out.println("[SN] RITM updated: State");
    }

    public void clickSaveButtonAfterUpdate() {
        click(ServiceNowRitmLocators.SAVE_BUTTON);
        System.out.println("[SN] Save button clicked after update.");
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
    public void waitForRitmPageToLoad() {

        System.out.println("[SN] Locating gsft_main iframe inside shadow DOM");

        WebElement frame = wait.until(d -> getGsftMainFrame());

        // IMPORTANT: reset context before switching
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frame);

        System.out.println("[SN] Switched into gsft_main iframe");

        System.out.println("[SN] Waiting for RITM page to load");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                ServiceNowRitmLocators.STATE));

        wait.until(ExpectedConditions.elementToBeClickable(
                ServiceNowRitmLocators.SAVE_BUTTON));

        System.out.println("[SN] RITM page detected");
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

    public void openCatalogTasksTab() {
        WebElement tab = wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(ServiceNowRitmLocators.CATALOG_TASKS_TAB));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", tab);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ;
        }

        wait.until(ExpectedConditions.elementToBeClickable(tab)).click();

        System.out.println("[SN] Opened Catalog Tasks tab");
    }

    public void openCatalogTask() {
        WebElement task = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        ServiceNowRitmLocators.CATALOG_TASK));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", task);

        wait.until(ExpectedConditions.elementToBeClickable(task)).click();

        System.out.println("[SN] Opened Catalog Task");
    }

    public void updateCatalogTaskState(String stateValue) {
        WebElement stateDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        ServiceNowRitmLocators.CATALOG_TASK_STATE));

        Select select = new Select(stateDropdown);
        select.selectByVisibleText(stateValue);

        System.out.println("[SN] Catalog Task state set to: " + stateValue);
    }

    public void clickCatalogTaskSaveButton() {
        WebElement saveBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        ServiceNowRitmLocators.SAVE_BUTTON));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", saveBtn);

        saveBtn.click();

        System.out.println("[SN] Save (Update & Stay) button clicked");
    }

    public void enterWorkNotes(String notes) {
        WebElement workNotes = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        ServiceNowRitmLocators.WORK_NOTES));

        workNotes.clear();
        workNotes.sendKeys(notes);

        System.out.println("[SN] Work notes entered");
    }

    public void clickPostButton() {
        WebElement postBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        ServiceNowRitmLocators.POST_BUTTON));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", postBtn);

        postBtn.click();

        System.out.println("[SN] Post button clicked");
    }

    public void clickCloseTaskButton() {

        WebElement closeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        ServiceNowRitmLocators.CLOSE_TASK_BUTTON));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", closeBtn);

        closeBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // wait for action to complete, adjust as needed
        System.out.println("[SN] Close Task button clicked");
    }

    public void refreshPage() {
        driver.navigate().refresh();
        waitForRitmPageToLoad();
        System.out.println("[SN] RITM page refreshed");
    }

    public String getRitmState() {
        WebElement stateDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        ServiceNowRitmLocators.RITM_STATE));

        Select select = new Select(stateDropdown);
        String selectedText = select.getFirstSelectedOption().getText().trim();

        System.out.println("[SN] RITM state text: " + selectedText);
        return selectedText;
    }

    public String getRitmStage() {
        WebElement stageDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        ServiceNowRitmLocators.RITM_STAGE));

        Select select = new Select(stageDropdown);
        String selectedText = select.getFirstSelectedOption().getText().trim();

        System.out.println("[SN] RITM stage text: " + selectedText);
        return selectedText;
    }

}