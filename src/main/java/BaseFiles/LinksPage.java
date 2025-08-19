package BaseFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LinksPage {
    WebDriver driver;

    public LinksPage(WebDriver driver) {
        this.driver = driver;
    }

    By createdLink = By.id("created");
    By response = By.id("linkResponse");

    public void clickCreatedLink() {
        driver.findElement(createdLink).click();
    }

    public String getResponseMessage() {
        return driver.findElement(response).getText();
    }
}
