package com.company;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;

public class BaseTests {
    private final ThreadLocal <Driver> driver = new ThreadLocal<Driver>();

    protected Driver getDriver(){
        return driver.get();
    }

    protected String username;
    protected String password;
    protected String scrDirectory;

    @BeforeTest
    void beforeTest() throws IOException {
        Configuration.loadConfigurations();
    }

    @BeforeClass
    void beforeClass() throws IOException {
        username = Configuration.readUsername();
        password = Configuration.readPass();
        scrDirectory = Configuration.readScreenshotDirectory();
        FileHelpers.cleanUpDirectory();
    }

    @BeforeMethod
    void setup() {
        driver.set(new Driver(DriverType.CHROME));
        getDriver().loadBaseUrl();
    }

    @AfterMethod
    void cleanUp(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()){
            FileHelpers.takeScreenshot(getDriver().getWebDriver(), result);
        }
        getDriver().killDriverSession();
    }
}
