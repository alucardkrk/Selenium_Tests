package Pages;

import Helpers.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
   private WebDriver driver;
   private SeleniumHelper helper;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//input[@id=\"email_create\"]")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@id=\"SubmitCreate\"]")
    private WebElement createAccountButton;
    @FindBy (xpath = "//*[@id=\"create_account_error\"]")
    private WebElement alertDialogBox;

    public void createNewAccount(String email){
        helper.waitForEelement(emailInput);
        emailInput.sendKeys(email);
        createAccountButton.click();
    }
    public String getAlertMessage(){
        String message;
        helper.waitForEelement(alertDialogBox);
        message = alertDialogBox.getText();

        return  message;
    }









}




