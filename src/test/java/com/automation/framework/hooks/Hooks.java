
package com.automation.framework.hooks;

import com.automation.framework.base.DriverFactory;
import com.automation.framework.pages.ServiceNowAuthenticationPage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.automation.framework.utilities.ConfigReader;

public class Hooks {

    @Before("@login")
    public void setUp() {

        // 1. Launch browser + navigate to URL
        DriverFactory.createDriver();
        DriverFactory.getDriver().get(
                ConfigReader.getProperty("auth.url"));

        // 2. Initialize authentication page
        ServiceNowAuthenticationPage authenticationPage = new ServiceNowAuthenticationPage(DriverFactory.getDriver());

        // 3. Perform login
        authenticationPage.login(
                ConfigReader.getProperty("auth.username"),
                ConfigReader.getProperty("auth.password"));
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            if (scenario.isFailed()) {
                scenario.log("Scenario failed, taking screenshot...");
                TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DriverFactory.quitDriver();
    }

}