package Tests;


import Utilities.DriverFactory;
import Utilities.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseSeleniumTest {
    protected WebDriver driver;

    @BeforeMethod
    protected void setup() {
        driver = DriverFactory.getDriver(DriverType.CHROME);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }
    @AfterMethod
    protected void end(){
       driver.quit();
       DriverFactory.resetDriver();
    }
}

