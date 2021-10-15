package com.company.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestBr {
    WebDriver driver = null;

    @BeforeMethod
    @Parameters("browser")
    public void setUp (String browser){
        System.out.println("@before");
        String url = "http://training.skillo-bg.com:4300/posts/all";

        if (browser.equalsIgnoreCase("chrome")){
            System.out.println("---CHROME HERE---");
            System.setProperty("webdriver.chrome.driver", "C:\\bin\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("firefox")){
            System.out.println("---FIREFOX HERE---");
            System.setProperty("webdriver.gecko.driver", "C:\\bin\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        if (browser.equalsIgnoreCase("msedgedriver")){
            System.out.println("---EDGE---");
            System.setProperty("webdriver.edge.driver", "C:\\bin\\msedgedriver.exe");
            driver = new FirefoxDriver();
        }
        if (browser.equalsIgnoreCase("opera")){
            System.out.println("--OPERA HERE---");
            System.setProperty("webdriver.opera.driver", "C:\\bin\\operadriver.exe");
            driver = new OperaDriver();
        }
        if (browser.equalsIgnoreCase("headless")){
            System.out.println("--HEADLESS CHROME---");
            System.setProperty("webdriver.chrome.driver", "C:\\bin\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Test
    public void  test1(){
        System.out.println("Test1");
    }
    @Test
    public void  test2(){
        System.out.println("Test2");
    }
    @Test
    public void  test3(){
        System.out.println("Test2");
    }

    @AfterMethod
    public void clanUp(){
        System.out.println("@after");
        //driver.close();
        driver.quit();
    }
}
