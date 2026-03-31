package com.automation.framework.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.framework.base.UiActions;
import com.automation.framework.locators.ServiceNowNavigatorLocators;

public class ServiceNowHomePage extends UiActions {

    public ServiceNowHomePage(WebDriver driver) {
        super(driver);
    }
    // ---------------- Public API ----------------

    // HELPER METHOD

    public void searchGlobalBar(String searchString) {
        driver.switchTo().defaultContent();
        System.out.println("[DEBUG] Switched to default content");
        // STEP 1: host1 (macroponent)
        WebElement host1 = driver.findElement(By.cssSelector("*[app-id='a84adaf4c700201072b211d4d8c260b7']"));
        System.out.println("[DEBUG] Found host1 (macroponent with app-id)");

        // STEP 2: shadowRoot1
        SearchContext shadowRoot1 = host1.getShadowRoot();
        System.out.println("[DEBUG] Entered shadowRoot1");

        // STEP 3: host2
        WebElement host2 = shadowRoot1.findElement(By.cssSelector("sn-canvas-appshell-root"));
        System.out.println("[DEBUG] Found sn-canvas-appshell-root");

        // STEP 4: host3
        WebElement host3 = host2.findElement(By.cssSelector("sn-canvas-appshell-layout"));
        System.out.println("[DEBUG] Found sn-canvas-appshell-layout");

        // STEP 5: host4
        WebElement host4 = host3.findElement(By.cssSelector("sn-polaris-layout"));
        System.out.println("[DEBUG] Found sn-polaris-layout");

        // STEP 6: shadowRoot4
        SearchContext shadowRoot4 = host4.getShadowRoot();
        System.out.println("[DEBUG] Entered shadowRoot4");

        // STEP 7: host5
        WebElement host5 = shadowRoot4.findElement(By.cssSelector("sn-polaris-header"));
        System.out.println("[DEBUG] Found sn-polaris-header");

        // STEP 8: shadowRoot5
        SearchContext shadowRoot5 = host5.getShadowRoot();
        System.out.println("[DEBUG] Entered shadowRoot5");

        // STEP 9: host6
        WebElement host6 = shadowRoot5.findElement(By.cssSelector("sn-search-input-wrapper"));
        System.out.println("[DEBUG] Found sn-search-input-wrapper");

        // STEP 10: shadowRoot6
        SearchContext shadowRoot6 = host6.getShadowRoot();
        System.out.println("[DEBUG] Entered shadowRoot6");

        // STEP 11: host7
        WebElement host7 = shadowRoot6.findElement(By.cssSelector("sn-component-workspace-global-search-typeahead"));
        System.out.println("[DEBUG] Found sn-component-workspace-global-search-typeahead");

        // STEP 12: shadowRoot7
        SearchContext shadowRoot7 = host7.getShadowRoot();
        System.out.println("[DEBUG] Entered shadowRoot7");

        // print all elements in shadowRoot7
        List<WebElement> allInShadow = shadowRoot7.findElements(By.cssSelector("*"));
        System.out.println("=== Elements in shadow root of host: " + summarize(host7) + " ===");
        for (WebElement el : allInShadow) {
            System.out.println(summarize(el));
        }

        WebElement searchInput = shadowRoot7.findElement(ServiceNowNavigatorLocators.SEARCH_BAR);
        System.out.println("[DEBUG] Found search input inside shadowRoot7");

        // type searchString into the search input
        searchInput.clear();
        searchInput.sendKeys(searchString);
        // add 5 second wait to see the dropdown options
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchInput.sendKeys(Keys.ARROW_DOWN);
        searchInput.sendKeys(Keys.ENTER);
        // add 5 second wait to see the result page load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[DEBUG] Typed search string and selected first option in dropdown");

    }

}