package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    WebDriver driver;

    // Locators
    By username = By.id("user-name");
    By password = By.name("password");
    By loginBtn = By.className("btn_action");
    By loginLogo = By.cssSelector(".login_logo");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginLogoDisplayed() {
        return driver.findElement(loginLogo).isDisplayed();
    }

    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }
}
