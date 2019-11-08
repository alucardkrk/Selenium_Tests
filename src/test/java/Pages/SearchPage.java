package Pages;

import Helpers.SeleniumHelper;
import Utilities.SearchedProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private WebDriver driver;
    private SeleniumHelper helper;


    public SearchPage (WebDriver driver) {
        this.driver=driver;
        this.helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver,this);
    }



    @FindBy(xpath = "//ul[@class='product_list grid row']")
    private WebElement searchedProductsTable;

    @FindBy(xpath = "//ul[@class='product_list grid row']/li")
    private List<WebElement> productsList;

    @FindBy (xpath = "//span[@title=\"Continue shopping\"]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@title=\"View my shopping cart\"]")
    private WebElement shoppingCart;

    @FindBy (xpath = "//span[@class=\"price cart_block_total ajax_block_cart_total\"]")
    private WebElement cartTotalPrice;


    public SearchPage waitForSearchResults(){
        helper.waitForEelement(searchedProductsTable);
        return this;
    }
    public List<SearchedProduct> getProductsList(){
        ArrayList<SearchedProduct> productsList = new ArrayList<>();
        for (WebElement element: this.productsList) {
            String name = element.findElement(By.xpath(".//h5[@itemprop='name']")).getText();
            double price = Double.valueOf(element.findElement(By.xpath(".//div[@class='right-block']//span[@itemprop='price']")).getText().substring(1));
            productsList.add(new SearchedProduct(name, price));
        }
        return productsList;
    }
    public SearchPage addAllItemsToCart(){

        for (int i = 0; i < productsList.size() ; i++) {
            addItemToCart(i);
        }
        return this;
    }
    public double getOrderTotalPrice(){
        List<SearchedProduct> searchedProducts = getProductsList();
        double expectedTotalPrice= 2.0; //Shipping cost = 2.0
        for (SearchedProduct product : searchedProducts) {
            expectedTotalPrice = expectedTotalPrice + product.getPrice();
        }
        return expectedTotalPrice;
    }
    public double getCartTotalPrice(){
        double price = 0;
        Actions builder = new Actions(driver);
        builder.moveToElement(shoppingCart).perform();
        helper.waitForEelement(cartTotalPrice);
        price = Double.valueOf(cartTotalPrice.getText().substring(1));
        return price;
    }
    public SearchPage addItemToCart(int index){
        WebElement searchedItem = productsList.get(index);
        WebElement imageCointainer = searchedItem.findElement(By.xpath(".//div[@class=\"product-image-container\"]"));
        Actions builder = new Actions(driver);
        builder.moveToElement(imageCointainer).perform();
        helper.waitForEelement(searchedItem.findElement(By.xpath(".//a[@title='Add to cart']")));
        searchedItem.findElement(By.xpath(".//a[@title='Add to cart']")).click();
        helper.waitForEelement(continueShoppingButton);
        continueShoppingButton.click();
        return this;
    }
}
