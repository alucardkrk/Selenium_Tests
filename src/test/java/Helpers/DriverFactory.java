package Helpers;

import Utilities.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(DriverType driverType) {
        if (driver==null){
            getSpecificDriver(driverType);
        }


        return  driver;
    }

    public static void resetDriver(){
        driver = null;

    }



    private static void getSpecificDriver(DriverType driverType) {
        switch (driverType){
            case IE:
                //TODO w razie potrzeby uzupelnic IE
                break;

            case CHROME:
                getChromeDriver();
                break;

            case FIREFOX:
                //TODO w razie potrzeby uzupelnic firefoxa
                break;
            default:
                getChromeDriver();

        }
    }
    private static void getChromeDriver(){
        File chromeDriverExe = new File("src\\main\\resources\\drivers\\chromedriver.exe");
        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(chromeDriverExe)
                .usingAnyFreePort()
                .build();
        driver = new ChromeDriver(chromeDriverService);

    }

}
