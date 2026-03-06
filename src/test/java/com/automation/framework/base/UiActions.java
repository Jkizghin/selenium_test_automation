package com.automation.framework.base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UiActions {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public UiActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void selectByText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    protected void sendKey(By locator, Keys key) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(key);
    }

    protected void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void selectByVisibleText(By by, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
        Select dropdown = new Select(el);
        dropdown.selectByVisibleText(text);
    }

    protected void typeAndSelect(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ENTER);
    }

    // ---------------- Small utilities ----------------

    private WebElement findOrNull(SearchContext ctx, By by) {
        try {
            List<WebElement> els = ctx.findElements(by);
            if (els == null || els.isEmpty())
                return null;
            return els.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    private List<WebElement> safeFindElements(SearchContext ctx, By by) {
        try {
            return ctx.findElements(by);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    protected void blurActiveElement() {
        try {
            ((JavascriptExecutor) driver).executeScript("if(document.activeElement){document.activeElement.blur();}");
        } catch (Exception ignored) {
        }
    }

    protected String safeTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            return "<no-title>";
        }
    }

    private String safeAttr(WebElement el, String attr) {
        try {
            return el.getAttribute(attr);
        } catch (Exception e) {
            return "";
        }
    }

    protected void smallWait(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
