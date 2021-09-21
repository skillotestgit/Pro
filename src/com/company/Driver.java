package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.List;
import java.util.Set;

public class Driver implements WebDriver {

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public Driver(DriverType browserType){

        if(browserType == DriverType.CHROME){
            driver.set(new ChromeDriver());
        } else if(browserType == DriverType.IE){
            driver.set(new InternetExplorerDriver());
        } else if(browserType == DriverType.FIREFOX){
            driver.set(new FirefoxDriver());
        } else if(browserType == DriverType.OPERA){
            driver.set(new OperaDriver());
        }
        driver.get().manage().window().maximize();
    }

    public void loadBaseUrl(){
        String url = Configuration.readBaseUrl();
        get(url);
    }

    public void killDriverSession(){
        close();
        quit();
        driver = null;
    }

    public WebDriver getWebDriver(){

        return driver.get();
    }

    @Override
    public void get(String url) {
        getWebDriver().get(url);
    }

    @Override
    public String getCurrentUrl() {
        return getWebDriver().getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return getWebDriver().getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getWebDriver().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getWebDriver().findElement(by);
    }

    @Override
    public String getPageSource() {
        return getWebDriver().getPageSource();
    }

    @Override
    public void close() {
        getWebDriver().close();
    }

    @Override
    public void quit() {
        getWebDriver().quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return getWebDriver().getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return getWebDriver().getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return getWebDriver().switchTo();
    }

    @Override
    public Navigation navigate() {
        return getWebDriver().navigate();
    }

    @Override
    public Options manage() {
        return getWebDriver().manage();
    }
}
