package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewPost {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public NewPost(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//*[@type='file'])[2]")
    private WebElement uploadForm;

    @FindBy(xpath = "//*[@placeholder='Enter you post caption here']")
    private WebElement caption;

    @FindBy(id = "create-post")
    private WebElement submitBtn;

    public void uploadPostPicture(String fileName){
        //WebElement loginLink = driver.findElement(loginLinkLoc);
        String directoryPath = Configuration.readFilesDirectory();
        uploadForm.sendKeys(directoryPath + fileName);
    }

    public void uploadNewestPicture(){
        String directory = Configuration.readFilesDirectory();
        String fileName = FileHelpers.getNewestFileNameFromDirectory(directory);
        uploadForm.sendKeys(directory + fileName);
    }

    public void fillCaption(String text){
        //WebElement loginLink = driver.findElement(loginLinkLoc);
        caption.sendKeys(text);
    }

    public void clickSubmit(){
        submitBtn.click();
    }


}
