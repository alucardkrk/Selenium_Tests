package Tests;

import Helpers.BasicTestListener;
import Helpers.ExcelHelper;
import Pages.CreateAccountPage;
import Pages.HomePage;
import Utilities.RequiredFields;
import Pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;



@Listeners(BasicTestListener.class)
public class RegistrationTest extends BaseSeleniumTest {



    @Test
    public void invalidEmail(){
        driver.get("http://automationpractice.com/");
        HomePage homePage = new HomePage(driver);
        homePage.signInClick();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.createNewAccount("bezmalpy.com");
        String expectedAlertMessage = "Invalid email address.";
        Assert.assertEquals(signInPage.getAlertMessage(), expectedAlertMessage);


    }

    @Test(dataProvider = "getFormData")
    public void createAnAccount(String title, String firstName, String lastName, String email, String password, String dayOfBirth, String monthOfBirth,
                                String yearOfBirth, String isNewsletter, String isSpecialOffer, String addressFirstName, String addressLastName,
                                String company, String address, String city, String state, String postalCode, String country, String additionalInfo,
                                String homePhone, String mobilePhone, String addressAlias){
        driver.get("http://automationpractice.com/");
        new HomePage(driver).signInClick();
        String randomEmail = Math.random() + "@gmail.com";
        new SignInPage(driver).createNewAccount(randomEmail);
        CreateAccountPage createPage = new CreateAccountPage(driver);
        createPage
                .waitForPageToLoad()
                .setTitle(title)
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .setDateOfBirth(dayOfBirth,monthOfBirth,yearOfBirth)
                .enterPassword(password)
                .signUpForNewsletter(isNewsletter)
                .signUpForSpecialOffers(isSpecialOffer)
                .enterAddressFirstName(addressFirstName)
                .enterAddressLastName(addressLastName)
                .enterCompany(company)
                .enterAddressFirstLine(address)
                .enterCity(city)
                .selectState(state)
                .enterPostalCode(postalCode)
                .enterAdditionalInformations(additionalInfo)
                .enterHomePhone(homePhone)
                .enterMobilePhone(mobilePhone)
                .enterAddressAlias(addressAlias)
                .clickSubmitButton();
        String expectedMessage = "An account using this email address has already been registered.";
        String alertMessage = createPage.getAlertMessage();
        Assert.assertTrue(alertMessage.contains(expectedMessage));

    }
    @Test (dataProvider ="getRequiredFields")
    public void testRequiredFields(RequiredFields requiredField){
        driver.get("http://automationpractice.com/");
        String email = Math.random() + "@gmail.com";
        new HomePage(driver).signInClick();
        new SignInPage(driver).createNewAccount(email);
        CreateAccountPage createPage = new CreateAccountPage(driver);
        createPage
                .waitForPageToLoad()
                .setTitle("Mr")
                .enterFirstName("Jasio")
                .enterLastName("Zawodowiec")
                .enterEmail(email)
                .setDateOfBirth("10", "10", "1999")
                .enterPassword("jachuntor")
                .signUpForNewsletter("FALSE")
                .signUpForSpecialOffers("FALSE")
                .enterAddressFirstName("Jasio")
                .enterAddressLastName("Zawodowiec")
                .enterCompany("MABUCZI")
                .enterAddressFirstLine("Bochnia 10")
                .enterCity("Bochnia")
                .selectState("Alabama")
                .enterPostalCode("32300")
                .enterAdditionalInformations("NOPE")
                .enterHomePhone("505505505")
                .enterMobilePhone("508508508")
                .enterAddressAlias("adres alias");
        String requiredMessage = createPage.clearField(requiredField);
        createPage.clickSubmitButton();
        String alertMessage = createPage.getAlertMessage();
        Assert.assertTrue(alertMessage.contains(requiredMessage));

    }

    @DataProvider
    public Object [][] getRequiredFields(){
        RequiredFields [][] requiredFields = new RequiredFields[12][1];
        requiredFields[0][0] = RequiredFields.FIRST_NAME;
        requiredFields[1][0] = RequiredFields.LAST_NAME;
        requiredFields[2][0] = RequiredFields.EMAIL;
        requiredFields[3][0] = RequiredFields.PASSWORD;
        requiredFields[4][0] = RequiredFields.ADDRESS_FIRST_NAME;
        requiredFields[5][0] = RequiredFields.ADDRESS_LAST_NAME;
        requiredFields[6][0] = RequiredFields.ADDRESS;
        requiredFields[7][0] = RequiredFields.CITY;
        requiredFields[8][0] = RequiredFields.MOBILE_PHONE;
        requiredFields[9][0] = RequiredFields.ADDRESS_ALIAS;
        requiredFields[10][0] = RequiredFields.STATE;
        requiredFields[11][0] = RequiredFields.COUNTRY;


        return requiredFields;
    }

    @DataProvider
    public Object[][] getFormData(){
        Object[][] formData=null;

        try {
            formData = ExcelHelper.readExcelFile(new File("src//main//resources//RegisterForm.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return formData;
    }

}
