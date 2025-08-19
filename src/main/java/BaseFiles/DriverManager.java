package BaseFiles;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverThread=new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return driverThread.get();
    }

    public static synchronized void setDriver(WebDriver  driver) {
        DriverManager.driverThread.set(driver);
    }
}
