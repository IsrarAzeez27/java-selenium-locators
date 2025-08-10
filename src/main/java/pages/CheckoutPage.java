package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CheckoutPage {
    WebDriver driver;

    By firstName = By.tagName("input");
    By lastName = By.id("last-name");
    By postalCode = By.name("postalCode");
    By continueBtn = By.xpath("//input[@value='Continue']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillDetails(String fName, String lName, String pCode) {
        driver.findElements(firstName).get(0).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(postalCode).sendKeys(pCode);
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }
}
