
package com.automation.framework.hooks;

import com.automation.framework.base.DriverFactory;
import io.cucumber.java.After;

public class Hooks {

@After
public void tearDown() {
DriverFactory.quitDriver();
}
}