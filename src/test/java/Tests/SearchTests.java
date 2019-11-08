package Tests;

import Listeners.BasicTestListener;
import Helpers.ExcelHelper;
import Pages.HomePage;
import Pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import Listeners.RetryAnalyzer;

@Listeners(BasicTestListener.class)
public class SearchTests extends BaseSeleniumTest {


    @Test (dataProvider = "getItemsToFind", retryAnalyzer = RetryAnalyzer.class)
    public void cartTotalPriceTest(String item){
        driver.get("http://automationpractice.com/");
        HomePage homePage = new HomePage(driver);
        homePage.searchFor(item);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.waitForSearchResults();
        double expectedTotalPrice = searchPage.getOrderTotalPrice();
        searchPage.addAllItemsToCart();
        double cartTotalPrice = searchPage.getCartTotalPrice();
        Assert.assertEquals(cartTotalPrice,expectedTotalPrice);
    }



    @DataProvider
    public Object[][] getItemsToFind(){
        Object[][] itemsToFind = null;
        try {
            itemsToFind= ExcelHelper.readExcelFile(new File("src//main//resources//itemsToFind.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemsToFind;
    }
}
