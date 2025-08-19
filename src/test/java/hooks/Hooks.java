package hooks;

import BaseFiles.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        TestBase testBase=new TestBase();
        testBase.beforeMethod();
    }

    @After
    public void tearDown() {
        TestBase testBase=new TestBase();
        testBase.afterMethod();
    }
}
