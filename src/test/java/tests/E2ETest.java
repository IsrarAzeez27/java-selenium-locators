package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.*;

import java.util.List;
import java.time.Duration;

public class E2ETest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        // Fresh session every run
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void testE2ECheckoutFlow() throws InterruptedException {
        // Step 1: Login
        Assert.assertTrue(loginPage.isLoginLogoDisplayed(), "Login logo not displayed!");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        // Step 2: Verify products page loaded
        Assert.assertTrue(productsPage.isOnProductsPage(), "Products page not displayed!");

        // Step 3: Remove existing cart items if any
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[contains(text(),'Remove')]"));
        for (WebElement removeBtn : removeButtons) {
            removeBtn.click();
        }

        // Step 4: Count total products
        List<WebElement> allProducts = driver.findElements(By.className("inventory_item"));
        System.out.println("Total Products Available: " + allProducts.size());
        Assert.assertTrue(allProducts.size() > 0, "No products found!");

        // Step 5: Add multiple products
        productsPage.addBackpackToCart();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        // Step 6: Verify cart badge count
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount, "3", "Cart count mismatch!");

        // Step 7: Open cart and verify product names
        productsPage.openCart();
        List<WebElement> cartItems = driver.findElements(By.className("inventory_item_name"));
        for (WebElement item : cartItems) {
            System.out.println("In Cart: " + item.getText());
        }
        Assert.assertEquals(cartItems.size(), 3, "Cart item count mismatch!");

        // Step 8: Proceed to checkout
        cartPage.clickCheckout();

        // Step 9: Fill checkout form
        checkoutPage.fillDetails("John", "Doe", "12345");
        checkoutPage.clickContinue();

        // Step 10: Verify Checkout Overview page
        Assert.assertTrue(driver.findElement(By.className("summary_total_label")).isDisplayed(), "Checkout total not displayed!");

        // Step 11: Finish purchase
        driver.findElement(By.id("finish")).click();
        Assert.assertTrue(driver.findElement(By.className("complete-header")).isDisplayed(), "Order not completed!");

        // Step 12: Logout to reset session
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();
    }
    
    

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
