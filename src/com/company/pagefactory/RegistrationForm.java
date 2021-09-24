package com.company.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationForm {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//div[contains(text(), 'Username taken')]")
    private WebElement usernameTakenMsg;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@placeholder='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Birth date']")
    private WebElement birthDateField;

    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passField;

    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPassField;

    @FindBy(id = "sign-in-button")
    private WebElement signInBtn;

    @FindBy(name = "pulic-info")
    private WebElement publicInfoField;

    public RegistrationForm(WebDriver driver){

        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public WebElement getUsernameTakenMsg(){
        wait.until(ExpectedConditions.visibilityOf(usernameTakenMsg));
        return usernameTakenMsg;
    }

    public boolean usernameTakenMsgDissapearsAfterSeconds(){
        boolean usernameTakenMsgDissapears = wait.until(ExpectedConditions.invisibilityOf(usernameTakenMsg));
        return usernameTakenMsgDissapears;
    }

    public void fillUsername(String textToFill){
        usernameField.sendKeys(textToFill);
    }

    public void fillEmail(String textToFill){
        emailField.sendKeys(textToFill);
    }

    public void fillBirthDate(String textToFill){
        birthDateField.sendKeys(textToFill);
    }

    public void fillPass(String textToFill){
        passField.sendKeys(textToFill);
    }

    public void fillConfirmPass(String textToFill){
        confirmPassField.sendKeys(textToFill);
    }

    public void fillPublicInfo(String textToFill){
        publicInfoField.sendKeys(textToFill);
    }

    public HomePage clickSignIn(){
        signInBtn.click();
        HomePage hp = new HomePage(driver);
        return hp;
    }

    public WebElement getSignInBtn(){

        return signInBtn;
    }

    public WebElement getFieldValidationMsg(String field) throws NoSuchElementException {
        String valMsgXPath1 = "//div//input[@placeholder = '";
        String valMsgXPath2 = "']//following-sibling::span[contains(text(), \"This field is required!\")]";
        String fullXPath = valMsgXPath1 + field + valMsgXPath2;
        return driver.findElement(By.xpath(fullXPath));
    }

}
