package GeneralFiles;

import BaseFiles.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public WebDriver driver = null;

    public BasePage() {
        driver = DriverManager.getDriver();
    }

    public WebElement waitUntilClickable(By locator) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(5))
                    .ignoring(NoSuchElementException.class);

            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            throw new RuntimeException("Element clickable olmadı: " + locator.toString(), e);
        }
    }
    public WebElement waitUntilVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new RuntimeException("Element alınamadı: " + locator.toString(), e);
        }
    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void scrollToElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Scroll işlemi başarısız: " + locator.toString(), e);
        }
    }

    public void hoverOverElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            Thread.sleep(300);
        } catch (Exception e) {
            throw new RuntimeException("Hover işlemi başarısız: " + locator.toString(), e);
        }
    }

    public void clickElement(By locator) {
        try {
            waitUntilClickable(locator).click();
        } catch (Exception e) {
            throw new RuntimeException("Element tıklanamadı: " + locator.toString(), e);
        }
    }

    public void typeText(By locator, String text) {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Yazı yazılamadı: " + locator.toString(), e);
        }
    }

    public void typeTextAndPressEnter(By locator, String text) {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text + Keys.ENTER);
        } catch (Exception e) {
            throw new RuntimeException("Enter ile yazı gönderilemedi: " + locator.toString(), e);
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Element görünürlük kontrolü başarısız: " + locator.toString(), e);
        }
    }

}
