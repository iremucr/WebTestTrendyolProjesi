package BaseFiles;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class TestBase {
    public static boolean HEADLESS=false;
    public static String BROWSER="chrome";
    public static boolean REMOTE=false;
    public static String GRIDURL="";
    public static String URL="https://www.trendyol.com/";

    public static WebDriver driver=null;

    public void beforeMethod(){
        try {

            DriverManager.setDriver(Browsers.prepareDriver());
            driver=DriverManager.getDriver();
            driver.get(TestBase.URL);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void afterMethod(){
        //DriverManager.getDriver().quit();
    }



}
