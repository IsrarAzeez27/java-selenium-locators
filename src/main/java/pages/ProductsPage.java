package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    WebDriver driver;

    By productTitle = By.xpath("//span[text()='Products']");
    By addToCart = By.id("add-to-cart-sauce-labs-backpack");
    By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnProductsPage() {
        return driver.findElement(productTitle).isDisplayed();
    }

    public void addBackpackToCart() {
        driver.findElement(addToCart).click();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }
}
