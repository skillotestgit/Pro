package com.company.tests;

import com.company.configurations.Configuration;
import com.company.configurations.Driver;
import com.company.configurations.DriverType;
import com.company.helpers.FileHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

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
        //driver.set(new Driver(DriverType.CHROME));
        driver.set(new Driver(DriverType.OPERA));
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
