package com.company.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {
    private final WebDriver driver;
    private final WebDriverWait wait;
    //By loginLinkLoc = By.id("nav-link-login");
    //By profileLinkLoc = By.id("nav-link-profile");
    //By newPostLoc = By.id("nav-link-new-post");

    /*
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;

     */

    @FindAll({@FindBy(id = "nav-link-login"), @FindBy(linkText = "Login")})
    private WebElement loginLink;

    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;

    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;


    public Header(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickLogin(){
        //WebElement loginLink = driver.findElement(loginLinkLoc);
        loginLink.click();
        LoginPage loginpage = new LoginPage(driver);
        return loginpage;
    }

    public WebElement getProfileLink() {
        wait.until(ExpectedConditions.visibilityOf(profileLink));

        return profileLink;
    }

    public WebElement getNewPostLink() {
        wait.until(ExpectedConditions.visibilityOf(newPostLink));
        return newPostLink;
    }

    public NewPost clickNewPost() {
        wait.until(ExpectedConditions.visibilityOf(newPostLink));
        newPostLink.click();
        return new NewPost(driver);
    }


}
