package com.company;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BeforeIntegrationTests {
    @BeforeTest
    void beforeTestMethod(){
        System.out.println("==== @BeforeTest 3=====");
    }

    @AfterTest
    void aferTestMethod(){
        System.out.println("==== @AfterTest 3=====");
    }
}
