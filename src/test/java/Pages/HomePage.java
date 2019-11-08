package Pages;

import Helpers.SeleniumHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    private WebDriver driver;
    private SeleniumHelper helper;


    public HomePage(WebDriver driver) {
        this.driver=driver;
        this.helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver,this);
    }


    @FindBy (xpath = "//a[@class='login']")
    private WebElement signInButton;

    @FindBy (xpath = "//input[@id=\"search_query_top\"]")
    private WebElement searchField;

    public void searchFor(String item){
        searchField.clear();
        searchField.sendKeys(item);
        searchField.sendKeys(Keys.ENTER);
    }

    public void signInClick(){
    signInButton.click();
    }
}
