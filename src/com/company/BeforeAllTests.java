package com.company;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BeforeAllTests {
    @BeforeSuite
    void beforeSuiteMethod(){

        System.out.println("==== @BeforeSuite =====");
    }

    @AfterSuite
    void afterSuiteMethod(){
        System.out.println("==== @AfterSuite =====");
    }
}
