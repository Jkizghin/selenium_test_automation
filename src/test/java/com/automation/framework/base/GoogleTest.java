package com.automation.framework.base;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void openGoogle() {

        driver.get("https://www.google.com");

        String title = driver.getTitle();
        String url = driver.getCurrentUrl();

        System.out.println("Title: " + title);
        System.out.println("URL: " + url);

        Assert.assertTrue(url.contains("google"), "URL does not contain 'google'");
    }
}
