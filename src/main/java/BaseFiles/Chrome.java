package BaseFiles;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Chrome implements BaseBrowser {

    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.password_manager_leak_detection", false); // Turn off change your password
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false); //Turn off Save Address popup
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        options.setAcceptInsecureCerts(true);

        if (Boolean.valueOf(TestBase.HEADLESS) == true) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1550,900");
        }

        return options;
    }




    @Override
    public WebDriver runLocal() {
        try {

            WebDriverManager.chromedriver().setup();
            ChromeDriver chromeDriver=new ChromeDriver(getOptions());
            return chromeDriver;

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public WebDriver runRemote(String url) {
        return null;
    }
}
