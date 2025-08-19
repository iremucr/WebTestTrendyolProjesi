package BaseFiles;

import org.openqa.selenium.WebDriver;

public class Browsers {

    public static WebDriver prepareDriver(){
        WebDriver driver=null;
        try {
            BaseBrowser browser=Browser.prepareBrowser();

            if (TestBase.REMOTE){
                driver=browser.runRemote(TestBase.GRIDURL);
            }else{
                driver=browser.runLocal();
            }
              driver.manage().window().maximize();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return  driver;
    }
}
