package Pages;

import Utilities.RequiredFields;
import Helpers.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class CreateAccountPage {
    private WebDriver driver;
    private SeleniumHelper helper;


    public CreateAccountPage(WebDriver driver) {
        this.driver=driver;
        this.helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//form[@id=\"account-creation_form\"]")
    private WebElement accCreationForm;

    @FindBy(xpath = "//input[@id=\"id_gender1\"]")
    private WebElement mrCheckBox;

    @FindBy(xpath = "//input[@id=\"id_gender2\"]")
    private WebElement mrsCheckBox;

    @FindBy(xpath = "//input[@id=\"customer_firstname\"]")
    private WebElement firstNameInput;

    @FindBy (xpath = "//input[@id=\"customer_lastname\"]")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id=\"email\"]")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id=\"passwd\"]")
    private WebElement passwordInput;

    @FindBy (xpath = "//select[@id=\"days\"]")
    private WebElement dayOfBirth;

    @FindBy(xpath = "//select[@id=\"months\"]")
    private WebElement monthOfBirth;

    @FindBy(xpath = "//select[@id=\"years\"]")
    private WebElement yearOfBirth;

    @FindBy (xpath = "//input[@id=\"newsletter\"]")
    private WebElement newsletterCheckbox;

    @FindBy(xpath = "//input[@id=\"optin\"]")
    private WebElement specialOfferCheckbox;

    @FindBy(xpath = "//input[@id=\"firstname\"]")
    private WebElement addressFirstName;

    @FindBy(xpath = "//input[@id=\"lastname\"]")
    private WebElement addressLastName;

    @FindBy(xpath = "//input[@id=\"company\"]")
    private WebElement company;

    @FindBy(xpath = "//input[@id=\"address1\"]")
    private WebElement addressFirstLine;

    @FindBy(xpath = "//input[@id=\"address2\"]")
    private WebElement addressSecondLine;

    @FindBy(xpath = "//input[@id=\"city\"]")
    private WebElement city;

    @FindBy(xpath = "//select[@id=\"id_state\"]")
    private WebElement state;

    @FindBy(xpath = "//input[@id=\"postcode\"]")
    private WebElement postalCode;

    @FindBy (xpath = "//select[@id=\"id_country\"]")
    private WebElement country;

    @FindBy(xpath = "//*[@id='other']")
    private WebElement additionalInfo;

    @FindBy(xpath = "//input[@id=\"phone\"]")
    private WebElement homePhone;

    @FindBy(xpath = "//input[@id=\"phone_mobile\"]")
    private WebElement mobilePhone;

    @FindBy (xpath = "//input[@id=\"alias\"]")
    private  WebElement addressAlias;

    @FindBy (xpath = "//button[@id=\"submitAccount\"]")
    private WebElement submitAccountButton;

    @FindBy (xpath = "//div[@class=\"alert alert-danger\"]")
    private WebElement invalidFieldAlert;







    public CreateAccountPage waitForPageToLoad(){
        helper.waitForEelement(accCreationForm);
        return this;
    }
    public String getAlertMessage(){

        return invalidFieldAlert.getText();
    }
    public CreateAccountPage setTitle(String title){
        if (title.equals("Mr")){
            mrCheckBox.click();}
        else if (title.equals("Mrs")){
            mrsCheckBox.click();
        }

        return this;
    }
    public CreateAccountPage enterFirstName(String firstName){
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public CreateAccountPage enterLastName(String lastName){
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public CreateAccountPage enterEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);

        return this;
    }
    public CreateAccountPage setDateOfBirth(String day, String month, String year){
        Select daySelect = new Select(dayOfBirth);
        Select monthSelect = new Select(monthOfBirth);
        Select yearSelect = new Select(yearOfBirth);
        daySelect.selectByValue(day);
        monthSelect.selectByValue(month);
        yearSelect.selectByValue(year);

        return this;
    }
    public CreateAccountPage enterPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }
    public CreateAccountPage signUpForNewsletter(String isNewsletter){
        if (Boolean.parseBoolean(isNewsletter)) {
            if (!newsletterCheckbox.isSelected()){
            newsletterCheckbox.click(); }

        }else
            if (newsletterCheckbox.isSelected()){
                newsletterCheckbox.click(); }

        return this;
    }
    public CreateAccountPage signUpForSpecialOffers(String isSpecialOffer){
        if (Boolean.parseBoolean(isSpecialOffer)) {
            if (!specialOfferCheckbox.isSelected()){
                specialOfferCheckbox.click(); }

        }else
        if (specialOfferCheckbox.isSelected()){
            specialOfferCheckbox.click(); }

        return this;
    }

    public CreateAccountPage enterAddressFirstName(String addrFirstName){
        addressFirstName.clear();
        addressFirstName.sendKeys(addrFirstName);
        return this;
    }

    public CreateAccountPage enterAddressLastName(String addrLastName){
        addressLastName.clear();
        addressLastName.sendKeys(addrLastName);
        return this;
    }
    public CreateAccountPage enterCompany(String companyName){
        company.clear();
        company.sendKeys(companyName);
        return this;
    }
    public CreateAccountPage enterAddressFirstLine(String address){
        addressFirstLine.clear();
        addressFirstLine.sendKeys(address);
        return this;
    }
    public CreateAccountPage enterCity(String cityName){
        city.clear();
        city.sendKeys(cityName);
        return this;
    }
    public CreateAccountPage selectState (String stateName){
        Select select = new Select(state);
        select.selectByVisibleText(stateName);
        return this;
    }
    public CreateAccountPage enterPostalCode(String zipcode){
        postalCode.clear();
        postalCode.sendKeys(zipcode);
        return this;
    }
    public CreateAccountPage enterAdditionalInformations(String additionalInformations){
        additionalInfo.clear();
        additionalInfo.sendKeys(additionalInformations);
        return this;
    }

    public CreateAccountPage enterHomePhone(String phone){
        homePhone.clear();
        homePhone.sendKeys(phone);
        return this;
    }
    public CreateAccountPage enterMobilePhone (String phone){
        mobilePhone.clear();
        mobilePhone.sendKeys(phone);
        return this;
    }
    public CreateAccountPage enterAddressAlias (String alias){
        addressAlias.clear();
        addressAlias.sendKeys(alias);
        return this;
    }
    public void clickSubmitButton(){
        submitAccountButton.click();
    }

    public String clearField(RequiredFields requiredField){
        String message = "";

        switch (requiredField) {
            case FIRST_NAME:
                firstNameInput.clear();
                message = "firstname is required.";
                break;
            case LAST_NAME:
                lastNameInput.clear();
                message = "lastname is required.";
                break;
            case EMAIL:
                emailInput.clear();
                message = "email is required.";
                break;
            case PASSWORD:
                passwordInput.clear();
                message = "passwd is required.";
                break;
            case ADDRESS_FIRST_NAME:
                addressFirstName.clear();
                message = "firstname is required.";
                break;
            case ADDRESS_LAST_NAME:
                addressLastName.clear();
                message = "lastname is required.";
                break;
            case ADDRESS:
                addressFirstLine.clear();
                message = "address1 is required.";
                break;
            case CITY:
                city.clear();
                message = "city is required.";
                break;
            case COUNTRY:
                Select selectCountry = new Select(country);
                selectCountry.selectByVisibleText("-");
                message = "Country is invalid";
                break;
            case STATE:
                Select select = new Select(state);
                select.selectByVisibleText("-");
                message="This country requires you to choose a State";
                break;
            case MOBILE_PHONE:
                homePhone.clear();
                mobilePhone.clear();
                message="You must register at least one phone number.";
                break;
            case ADDRESS_ALIAS:
                addressAlias.clear();
                message = "alias is required.";
                break;
        }
        return message;
    }




}
