package com.company;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class RegistrationTests extends BaseTests {

    /*
    @BeforeClass
    void beforeClass(){
        System.out.println("==== @BeforeClass2 =====");
    }

    @AfterClass
    void afterClass(){
        System.out.println("==== @AfterClass2 =====");
    }


     */


    /*
    @DataProvider (name = "data")
    public Object[][] dpMethod(){
        return new Object[][] {{"Ivan Draganov"}, {"Ivanov Stoyanov"}};
    }
    */

    @DataProvider (name = "Registration data")
    public Object[][] registrationData(){
        return new Object[][] {{"", "test@gmail.com", "03211984", "Test123", "Test123", "Test Info", "Username"},
                {"testuser", "", "06/23/1946", "Test123", "Test123", "Test Info", "email"},
                {"testuser", "test@gmail.com", "", "Test123", "Test123", "Test Info", "Birth date"},
                {"testuser", "test@gmail.com", "03211984", "", "Test123", "Test Info", "Password"},
                {"testuser", "test@gmail.com", "03211984", "Test123", "", "Test Info", "Confirm Password"},
                {"testuser", "test@gmail.com", "03211984", "Test123", "Test123", "", "Public info"}};
    }

    //@Test (dataProvider = "data")
    //void dataDrivenTest (String val) {
    //    System.out.println("Passed Parameter Is : " + val);
    //}
/*
    @Test (dataProvider = "data2")
    void dataDrivenTest2 (int a, int b, int c) {
    //    System.out.println("Passed Parameter Is : " + a + b + c);
    }

 */

    @Test
    void successfulRegistration(){
        long timestamp = Instant.now().getEpochSecond();
        String username = "hjusein" + timestamp;
        Header header = new Header(getDriver());
        LoginPage loginpage = header.clickLogin();
        RegistrationForm registrationForm = loginpage.clickRegister();
        registrationForm.fillUsername(username);
        registrationForm.fillEmail(timestamp + "@gmail.com");
        registrationForm.fillBirthDate("03211984");
        registrationForm.fillPass("Hju123_");
        registrationForm.fillConfirmPass("Hju123_");
        registrationForm.fillPublicInfo("alabala");
        registrationForm.clickSignIn();
        Assert.assertTrue(header.getProfileLink().isDisplayed());
        Assert.assertTrue(header.getNewPostLink().isDisplayed());
    }

    @Test
    void successfulRegistration2() throws InterruptedException {

        Header header = new Header(getDriver());
        LoginPage loginpage = header.clickLogin();
        RegistrationForm registrationForm = loginpage.clickRegister();
        registrationForm.fillUsername(username);
        registrationForm.fillPass(password);
    }

    @Test
    void unsuccessfulRegistrationUsernameTaken(){
        Header header = new Header(getDriver());
        LoginPage loginpage = header.clickLogin();
        RegistrationForm registrationForm = loginpage.clickRegister();
        registrationForm.fillUsername("hjusein1234");
        registrationForm.fillEmail("hjusein@gmail.com");
        registrationForm.fillBirthDate("03211984");
        registrationForm.fillPass("Hju123_");
        registrationForm.fillConfirmPass("Hju123_");
        registrationForm.fillPublicInfo("alabala");
        registrationForm.clickSignIn();
        Assert.assertTrue(registrationForm.getUsernameTakenMsg().isDisplayed());
    }

    @Test
    void unsuccessfulRegistrationUsernameTakenMsgDissapears(){
        Header header = new Header(getDriver());
        LoginPage loginpage = header.clickLogin();
        RegistrationForm registrationForm = loginpage.clickRegister();
        registrationForm.fillUsername("hjusein1234");
        registrationForm.fillEmail("hjusein@gmail.com");
        registrationForm.fillBirthDate("03211984");
        registrationForm.fillPass("Hju123_");
        registrationForm.fillConfirmPass("Hju123_");
        registrationForm.fillPublicInfo("alabala");
        registrationForm.clickSignIn();
        Assert.assertTrue(registrationForm.getUsernameTakenMsg().isDisplayed());
        Assert.assertTrue(registrationForm.usernameTakenMsgDissapearsAfterSeconds());
    }

    @Test(dataProvider = "Registration data")
    void registrationIsNotPossibleWhenSomeOfTheFieldsIsEmpty(String username, String email, String dateOfBirth, String pass, String confirmPass, String info, String elPlaceHolder) throws IOException {
        Header header = new Header(getDriver());
        LoginPage loginpage = header.clickLogin();
        RegistrationForm registrationForm = loginpage.clickRegister();
        registrationForm.fillUsername(username);
        registrationForm.fillEmail(email);
        registrationForm.fillBirthDate(dateOfBirth);
        registrationForm.fillPass(pass);
        registrationForm.fillConfirmPass(confirmPass);
        registrationForm.fillPublicInfo(info);
        registrationForm.clickSignIn();

        Assert.assertTrue(!registrationForm.getSignInBtn().isEnabled());
    }

    @Test(dataProvider = "Registration data")
    void fieldValidationMsgAppearsForAllFields(String username, String email, String dateOfBirth, String pass, String confirmPass, String info, String elPlaceHolder){
        Header header = new Header(getDriver());
        LoginPage loginpage = header.clickLogin();
        RegistrationForm registrationForm = loginpage.clickRegister();
        registrationForm.fillUsername(username);
        registrationForm.fillEmail(email);
        registrationForm.fillBirthDate(dateOfBirth);
        registrationForm.fillPass(pass);
        registrationForm.fillConfirmPass(confirmPass);
        registrationForm.fillPublicInfo(info);

        try {
            Assert.assertTrue(registrationForm.getFieldValidationMsg(elPlaceHolder).isDisplayed());
        } catch (Exception e){
            Assert.fail("Validation message for " + elPlaceHolder + " does not appear!");
        }
    }


    @Test
    void successfulLogin() {
        long timestamp = Instant.now().getEpochSecond();
        String username = "hjusein" + timestamp;
        Header header = new Header(getDriver());
        LoginPage loginpage = header.clickLogin();
        RegistrationForm registrationForm = loginpage.clickRegister();
        registrationForm.fillUsername(username);
        registrationForm.fillEmail(timestamp + "@gmail.com");
        registrationForm.fillBirthDate("03211984");
        registrationForm.fillPass("Hju123_");
        registrationForm.fillConfirmPass("Hju123_");
        registrationForm.fillPublicInfo("alabala");
        registrationForm.clickSignIn();
        NewPost newPostPage = header.clickNewPost();
        //newPostPage.uploadPostPicture("pic1.png");
        newPostPage.uploadNewestPicture();
        newPostPage.fillCaption("Test caption");
        newPostPage.clickSubmit();
    }

    @Test
    void compareFiles(){
        File file1 = new File("C:\\Users\\skillo\\Documents\\pics\\pic1.png");
        File file2 = new File("C:\\Users\\skillo\\Documents\\pics\\pic2.png");
        boolean test = FileHelpers.filesEqual(file1, file2);
        Assert.assertTrue(test);
    }


    /*
    @Test
    void successfulLogout(){
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();
        WebElement username = driver.findElement(By.id("defaultLoginFormUsername"));
        username.sendKeys(this.username);
        WebElement password = driver.findElement(By.id("defaultLoginFormPassword"));
        password.sendKeys(this.password);
        WebElement loginBtn = driver.findElement(By.id("sign-in-button"));
        loginBtn.click();
        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        WebElement postLink = driver.findElement(By.id("nav-link-new-post"));
        Assert.assertTrue(profileLink.isDisplayed());
        Assert.assertTrue(postLink.isDisplayed());
       // WebElement logout = driver.findElement(By.xpath());
        logout.click();
        username = driver.findElement(By.id("defaultLoginFormUsername"));
        Assert.assertTrue(username.isDisplayed());
    }
    */
}
