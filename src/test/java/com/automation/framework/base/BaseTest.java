package com.automation.framework.base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.openqa.selenium.WebDriver;

public class BaseTest {

protected WebDriver driver;

@BeforeMethod
public void setUp() {
driver = DriverFactory.createDriver();
}

@AfterMethod
public void tearDown() {
DriverFactory.quitDriver();
}
}
