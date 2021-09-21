package com.company;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class LoginTests extends BaseTests {

    @DataProvider (name = "Registration data")
    public Object[][] registrationData(){
        return new Object[][] {{"", "niamatakavtext"},
                {"niamatakavtext", ""},
                {"", ""}};
    }

    @Test(dataProvider = "Registration data")
    void unsueccessfulLogin(String username, String password) throws IOException {
        Header header = new Header(getDriver());
        LoginPage loginpage = header.clickLogin();
        loginpage.fillUsername(username);
        loginpage.fillPassword(password);
        //loginpage.clickBtn();
        Assert.assertFalse(loginpage.isLoginBtnClickable());
    }

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
        //NewPost newPostPage = header.clickNewPost();
        //newPostPage.uploadPostPicture("pic1.png");
        //newPostPage.uploadNewestPicture();
        //newPostPage.fillCaption("Test caption");
        //newPostPage.clickSubmit();
    }
}
