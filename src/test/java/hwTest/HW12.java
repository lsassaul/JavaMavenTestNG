package hwTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class HW12 {

    private static final String CHROMEDRIVER = "webdriver.chrome.driver";
    private static final String DRIVERPATH = "D:\\Учеба\\Автоматизация\\Курсы\\JavaMavenTestNG_04\\chromedriver_win32\\" +
            "chromedriver.exe";
    private static final String BASEURL = "http://www.99-bottles-of-beer.net/";

    /**
     * TC_12_01 Подтвердите, что в меню BROWSE LANGUAGES, подменю  J, пользователь может найти описание страницы,
     * на которой перечеслены все программные языки, начинающиеся с буквы J,  отсортированные по названию
     *
     * Шаги:
     * Открыть базовую страницу
     * Нажать на пункт меню BROWSE LANGUAGES
     * Нажать на подменю J
     * Подтвердить, что пользователь видит текст “All languages starting with the letter J are shown, sorted by Language.”
     */

    @Test
    public void testPageDescriptionForJ () {
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        driver.findElement(By.xpath("//ul[@id = 'menu']//a[@href='/abc.html']")).click();
        driver.findElement(By.xpath("//ul[@id = 'submenu']//a[@href='j.html']")).click();

        String actualResult = driver.findElement(By.xpath("//div[@id = 'main']/p[1]")).getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**
     * TC_12_02 Подтвердите, что в меню BROWSE LANGUAGES, подменю  M, последний программный язык в таблице -  MySQL
     *
     * Шаги:
     * Открыть базовую страницу
     * Нажать на пункт меню BROWSE LANGUAGES
     * Нажать на подменю M
     * Подтвердить, что последний язык программирования на странице - MySQL
     */

    @Test
    public void testLastProgramLanguageStartingWithM () {
        String expectedResult = "MySQL";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        driver.findElement(By.xpath("//ul[@id = 'menu']//a[@href='/abc.html']")).click();
        driver.findElement(By.xpath("//ul[@id = 'submenu']//a[@href='m.html']")).click();

        String actualResult = driver.findElement(
                By.xpath("//tr[last()]//a[@href='language-mysql-1252.html']")
        ).getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**
     * TC_12_03 Подтвердите, что в меню BROWSE LANGUAGES существует таблица с заголовками
     * Language, Author, Date, Comments, Rate
     *
     * Steps:
     * 1.Go to http://www.99-bottles-of-beer.net/
     * 2.Click 'BROWSER LANGUAGE' menu item
     * 3.Confirm that there is a table with the following column names: Language, Author, Date, Comments, Rate
     */

    @Test
    public void testTableColumnsNameBrowseLanguagesPage () {
        List <String> expectedResult = new ArrayList();
        expectedResult.add("Language");
        expectedResult.add("Author");
        expectedResult.add("Date");
        expectedResult.add("Comments");
        expectedResult.add("Rate");

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        driver.findElement(By.xpath("//ul[@id = 'menu']//a[@href='/abc.html']")).click();

        List <WebElement> columnNames = driver.findElements(By.xpath("//table[@id = 'category']//tr[1]/th"));

        List <String> actualResult = new ArrayList();

        for (WebElement columnName:
             columnNames) {
            actualResult.add(columnName.getText());
        }

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**
     * TC_12_04 Подтвердите, что создатель решения на языке Mathematica - Brenton Bostick,
     * дата обновления решения на этом языке - 03/16/06, и что это решение имеет 1 комментарий
     *
     * Steps:
     * 1.Go to http://www.99-bottles-of-beer.net/
     * 2.Click 'BROWSER LANGUAGE' menu item
     * 3.Click 'M' submenu item
     * 4.Click 'Mathematica' language in the table.
     * 5.Confirm that the language author is Brenton Bostick, decision was updated at 03/16/06 and decision has 1 comment.
     *
     */

    @Test
    public void testMathematicaLanguageAttributes() {
        String expectedResult1 = "Brenton Bostick";
        String expectedResult2 = "03/16/06";
        String expectedResult3 = "1";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        driver.findElement(By.xpath("//ul[@id = 'menu']//a[@href='/abc.html']")).click();
        driver.findElement(By.xpath("//ul[@id = 'submenu']//a[@href='m.html']")).click();
        driver.findElement(By.xpath("//a[@href='language-mathematica-1090.html']")).click();

        String actualResult1 = driver.findElement(By.xpath("//strong[text()='Author:']/..//../td[last()]")).getText();
        String actualResult2 = driver.findElement(By.xpath("//strong[text()='Date:']/..//../td[last()]")).getText();
        String actualResult3 = driver.findElement(By.xpath("//strong[text()='Comments:']/..//../td[last()]")).getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();
    }

    /**
     * TC_12_05 Подтвердите, что на сайте существует 10 языков, названия которых начинаются с цифр.
     * Steps:
     * 1.Go to http://www.99-bottles-of-beer.net/
     * 2.Click 'BROWSER LANGUAGE' menu item
     * 3.Click '0-9' submenu item
     * 4.Confirm that there are ten languages in the table displayed.
     */

    @Test
    public void testQuantityOfNumericLanguageNames() {
        int expectedResult = 10;

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        driver.findElement(By.xpath("//ul[@id = 'menu']//a[@href='/abc.html']")).click();
        driver.findElement(By.xpath("//ul[@id = 'submenu']//a[@href='0.html']")).click();

        List <WebElement> languageQuantity = driver.findElements(By.xpath("//table[@id='category']//tr"));

        int actualResult = languageQuantity.size() - 1;

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**
     * TC_12_06 Подтвердите, что если на странице Sign Guestbook http://www.99-bottles-of-beer.net/signv2.html
     * вы заполните все поля формы, но введете случайно сгенерированное трехзначное число в поле  Security Code: ,
     * то вы получите сообщение об ошибке  Error: Error: Invalid security code.
     *
     * Steps:
     * 1. Go to http://www.99-bottles-of-beer.net/signv2.html
     * 2. Fill in all fields in the form. Set random 3-digits number in [Security Code:] field.
     * 3. Click [Submit] button.
     * 4. Check that [Error: Error: Invalid security code.] is displayed.
     */

    @Test
    public void testErrorMessageSignGuestbookPage() {
        String expectedResult = "Error: Error: Invalid security code.";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.99-bottles-of-beer.net/signv2.html");

        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Liubov");
        driver.findElement(By.xpath("//input[@name='location']")).sendKeys("Kyiv");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("magessa@ukr.net");
        driver.findElement(
                By.xpath("//input[@name='captcha']"))
                .sendKeys(String.valueOf(100 + (int)(Math.random() * 900)));
        driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys("Good site.");

        driver.findElement(By.xpath("//input[@name='submit']")).click();

        String actualResult = driver.findElement(By.xpath("//div[@id='main']/p")).getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**
     * TC_12_07 Выберите любой язык программирования (из меню BROWSE LANGUAGES) и любую версию решения
     * (из раздела Alternative Versions, если такой раздел существует  для выбранного языка)
     * Подтвердите, что пользователь может сделать закладку на это решение на сайте Reddit
     * (нажав на иконку сайта Reddit, пользователь перейдет на Логин страницу сайта Reddit)
     *
     * Steps:
     *
     */

    @Test
    public void testRedditBookmark() throws InterruptedException {
        String expectedResult = "reddit.com: Log in";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        driver.findElement(By.xpath("//ul[@id = 'menu']//a[@href='/abc.html']")).click();

        driver.findElement(By.xpath("//a[text()='C#']")).click();

        if (driver.findElement(By.xpath("//div[@id='alternatives']//table[@id='category']")).isDisplayed()) {
            driver.findElement(By.xpath("//a[text()='C# Gratuitously Functional']")).click();
        }


        driver.findElement(By.xpath("//a[@title='reddit']")).click();
        Thread.sleep(1000);

        String actualResult = driver.getTitle();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**
     *
     */









}
