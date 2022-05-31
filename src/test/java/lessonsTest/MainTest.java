package lessonsTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Test
    public void eightComponents() {
        System.setProperty("webdriver.chrome.driver", "D:\\Учеба\\Автоматизация\\Курсы\\JavaMavenTestNG_04\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://google.com");

        String title = driver.getTitle();
        Assert.assertEquals("Google", title);

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"));

        searchBox.sendKeys("Selenium");
        searchButton.click();

        searchBox = driver.findElement(By.name("q"));
        String value = searchBox.getAttribute("value");
        Assert.assertEquals("Selenium", value);

        driver.quit();
    }
}
