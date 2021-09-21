package com.company;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BeforeUITests {
    @BeforeTest
    void beforeTestMethod(){
        System.out.println("==== @BeforeTest =====");
    }

    @AfterTest
    void aferTestMethod(){
        System.out.println("==== @AfterTest =====");
    }
}
