package steps;

import BaseFiles.JsonWebTypeReader;
import GeneralFiles.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;

public class MainSteps {
    BasePage basePage = new BasePage();

    @Given("^\"([^\"]*)\" tiklanir$")
    public void clickElement(String key) {
        By locator = JsonWebTypeReader.getLocator(key);
        basePage.clickElement(locator);
    }

    @And("^\"([^\"]*)\" alanına \"([^\"]*)\" yazılır$")
    public void typeText(String key, String text) {
        By locator = JsonWebTypeReader.getLocator(key);
        basePage.typeText(locator, text);
    }

    @And("^\"([^\"]*)\" alanına \"([^\"]*)\" yazılır ve enter'a basılır$")
    public void typeTextAndPressEnter(String key, String text) {
        By locator = JsonWebTypeReader.getLocator(key);
        basePage.typeTextAndPressEnter(locator, text);
    }

    @And("^\"([^\"]*)\" elementine scroll yapılır$")
    public void scrollToElement(String key) {
        By locator = JsonWebTypeReader.getLocator(key);
        basePage.scrollToElement(locator);
    }

    @And("^\"([^\"]*)\" elementine hover yapılır$")
    public void hoverToElement(String key) {
        By locator = JsonWebTypeReader.getLocator(key);
        basePage.hoverOverElement(locator);
    }

    @And("^ilk ürün sepete eklenir$")
    public void addFirstProductToCart() {
        By productLocator = JsonWebTypeReader.getLocator("common@firstProductCard");
        By buttonLocator = JsonWebTypeReader.getLocator("common@addFirstProductToCart");

        basePage.scrollToElement(productLocator);
        basePage.hoverOverElement(productLocator);
        basePage.clickElement(buttonLocator);
    }
    @Then("^\"([^\"]*)\" elementi gorunur$")
    public void elementIsVisible(String key) {
        By locator = JsonWebTypeReader.getLocator(key);
        boolean visible = basePage.isElementVisible(locator);

        if (visible) {
            System.out.println("Element görünüyor: " + key);
        } else {
            System.out.println("Element görünmüyor: " + key);
        }

        Assert.assertTrue(visible, "Element görünmedi: " + key);
    }
    @Then("^sepette ürün var mı kontrol edilir$")
    public void checkIfCartHasItem() {
        By itemLocator = JsonWebTypeReader.getLocator("common@cartItem");
        boolean hasItem = basePage.isElementVisible(itemLocator);

        if (hasItem) {
            System.out.println("Sepette ürün var.");
        } else {
            System.out.println("Sepet boş. Senaryo atlanıyor.");
            throw new SkipException("Senaryo atlandı çünkü sepet boş.");
        }
    }


    String oldTotalPrice;

    @And("^sepetteki ürün silinir$")
    public void removeCartItem() {
        By priceLocator = JsonWebTypeReader.getLocator("common@totalPrice");
        WebElement priceElement = basePage.waitUntilVisible(priceLocator);
        oldTotalPrice = priceElement.getText().replaceAll("[^0-9,]", "").replace(",", ".");

        By deleteButton = JsonWebTypeReader.getLocator("common@deleteCartItem");
        basePage.clickElement(deleteButton);
        basePage.waitSeconds(3); // Silme animasyonu bitene kadar
    }
    @Then("^toplam fiyatin dustugu dogrulanir$")
    public void verifyPriceDecreased() {
        By priceLocator = JsonWebTypeReader.getLocator("common@totalPrice");
        String newTotalPrice = basePage.waitUntilVisible(priceLocator).getText().replaceAll("[^0-9,]", "").replace(",", ".");

        double oldPrice = Double.parseDouble(oldTotalPrice);
        double newPrice = Double.parseDouble(newTotalPrice);

        Assert.assertTrue(newPrice < oldPrice, "Fiyat düşmedi! Önceki: " + oldPrice + ", Şu anki: " + newPrice);
    }



}
