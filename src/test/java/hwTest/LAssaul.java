package hwTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LAssaul {

    @Test
    public void firstTestSelenium () {
        System.setProperty("webdriver.chrome.driver", "D:\\Учеба\\Автоматизация\\Курсы\\JavaMavenTestNG_04\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        String title = driver.getTitle();
        Assert.assertEquals (title, "Swag Labs");

        WebElement userName = driver.findElement(By.cssSelector("#user-name"));
        WebElement password = driver.findElement(By.cssSelector("#password"));
        WebElement logButton = driver.findElement(By.cssSelector("#login-button"));

        userName.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        logButton.click();

        String menuTitle = driver.findElement(By.xpath("//span[@class = 'title']")).getText();
        Assert.assertEquals(menuTitle, "PRODUCTS");

        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();

        driver.findElement(By.cssSelector("#remove-sauce-labs-backpack")).isDisplayed();
        String itemQuantity = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();

        Assert.assertEquals(itemQuantity, "1");

        driver.quit();
    }
}
