package hwTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WebTests {

    private static final String CHROMEDRIVER = "webdriver.chrome.driver";
    private static final String DRIVERPATH = "D:\\Учеба\\Автоматизация\\Курсы\\JavaMavenTestNG_04\\chromedriver_win32\\" +
            "chromedriver.exe";
    private static final String BASEURL = "http://www.99-bottles-of-beer.net/";

    /**
     * TC_11_01 Подтвердите, что на странице по базовой ссылке в правом верхнем углу пользователь видит заголовок
     * 99 Bottles of Beer
     * Шаги:
     * 1. Открыть вебсайт на базовой странице
     * 2. Считать заголовок в правом верхнем углу
     * 3. Подтвердить, что текст заголовка соответствует ожидаемому
     * 4. Закрыть браузер
     */


    @Test
    public void testBasePageHeader () {
        String expectedResult = "99 Bottles of Beer";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        WebElement header = driver.findElement(
                By.xpath("//div[@id = 'header']/h1")
        );
        String actualResult = header.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    /**
     * TC_11_02 Подтвердите, что на странице по базовой ссылке последний пункт меню называется Submit new Language
     * Шаги:
     * 1. Открыть вебсайт на базовой странице
     * 2. Считать название последнего пункта меню
     * 3. Подтвердить, что название последнего пункта меню соответствует ожидаемому
     * 4. Закрыть браузер
     */

    @Test
    public void testLastMenuItem () {
        String expectedResult1 = "SUBMIT NEW LANGUAGE"; //UI Test - user sees this value
        String expectedResult2 = "Submit new Language"; //Functional Test - this value is in the particular tag

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        WebElement lastMenuItem = driver.findElement(
                By.xpath("//ul[@id = 'menu']/li[last()]/a")
        );

        String actualResult = lastMenuItem.getText();

        Assert.assertEquals(actualResult, expectedResult1); //UI Test
        Assert.assertEquals(actualResult, expectedResult2.toUpperCase()); //Functional Test

        driver.quit();
    }

    //TC_11_03 Подтвердите, что на странице по базовой ссылке последний пункт меню имеет подзаголовок Submit new Language
    //Шаги:
    //1. Открыть вебсайт на базовой странице
    //2. Нажать на пункт меню Submit new Language
    //3. Считать название подзаголовка последнего пункта меню
    //4. Подтвердить, что название подзаголовка последнего пункта меню соответствует ожидаемому
    //5. Закрыть браузер

    @Test
    public void testSubmenuItemNameSubmitNewLanguage () {
        String expectedResult = "Submit New Language"; //Should be 'Submit New Language'

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        WebElement lastMenuItem = driver.findElement(
                By.xpath("//ul[@id = 'menu']//a[@href = '/submitnewlanguage.html']")
        );

        lastMenuItem.click();

        WebElement submenuName = driver.findElement(
                By.xpath("//ul[@id = 'submenu']//a[@href = './submitnewlanguage.html']")
        );

        String actualResult = submenuName.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_04 Подтвердите, что на странице по ссылке http://www.99-bottles-of-beer.net/abc.html , первый пункт
    // подменю называется 0-9
    //
    //Шаги:
    //1. Открыть вебсайт на странице
    //2. Считать название первого подзаголовка
    //3. Подтвердить, что название подменю соответствует ожидаемому
    //4. Закрыть браузер

    @Test
    public void testFirstSubmenuName () {
        String url = "http://www.99-bottles-of-beer.net/abc.html";
        String expectedResult = "0-9";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement firstSubmenuName = driver.findElement(
                By.xpath("//ul[@id = 'submenu']/li[1]/a")
        );

        String actualResult = firstSubmenuName.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_06 Подтвердите, что имена создателей сайта:
    //Oliver Schade
    //Gregor Scheithauer
    //Stefan Scheler
    //Шаги:
    //1. Открыть вебсайт на базовой странице
    //2. Нажать на пункт подменю Team
    //3. Считать имена команды разработчиков
    //4. Подтвердить, что имена команды разработчиков соответствуют ожидаемому
    //5. Закрыть браузер

    @Test
    public void testAuthorsName () {
        String [] expectedResult = {"Oliver Schade", "Gregor Scheithauer", "Stefan Scheler"};

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASEURL);

        driver.findElement(
                By.xpath("//ul[@id = 'submenu']//a[@href='team.html']")
        ).click();

        List <WebElement> teamNames = driver.findElements(
                By.xpath("//div[@id = 'main']/h3")
        );

        String[] actualResult = new String[teamNames.size()];

        int i = 0;
        for (WebElement teamName : teamNames) {
            actualResult[i] = teamName.getText();
            i++;
        }

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_07 Придумайте и автоматизируйте свой собственный тест кейс на сайте http://www.99-bottles-of-beer.net/
    //Шаги:
    //1. Открыть вебсайт на базовой странице
    //2. Cчитать названия меню footer
    //4. Подтвердить, что названия меню соответствуют ожидаемому
    //Start, Browse Languages, Search Languages, Top Lists, Guestbook, Submit new Language
    //5. Закрыть браузер

    @Test
    public void testFooterMenuName () {
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "D:\\Учеба\\Автоматизация\\Курсы\\JavaMavenTestNG_04\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String [] expectedResult = {"Start", "Browse Languages", "Search Languages"
                , "Top Lists", "Guestbook", "Submit new Language"};

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        List <WebElement> footerMenuNames = driver.findElements(
                By.xpath("//div[@id = 'footer']//a")
        );

        String[] actualResult = new String[footerMenuNames.size()];

        int i = 0;
        for (WebElement footerMenuName : footerMenuNames) {
            actualResult[i] = footerMenuName.getText();
            i++;
        }

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_11 Подтвердите, что если на странице по ссылке http://www.99-bottles-of-beer.net/submitnewlanguage.html ,  пользователь нажмет кнопку Submit Language,  не заполнив информацию в обязательных полях, будет показана ошибка
    //Error: Precondition failed - Incomplete Input.
    //Шаги:
    //1. Открыть вебсайт на странице
    //2. Нажать на кнопку Submit Language
    //3. Подтвердить, что на странице показана ошибка
    //4. Подтвердить, что текст ошибки соответствует ожидаемому
    //5. Закрыть браузер

    @Test
    public void testSubmissionWithEmptyFieldsSubmitLanguageMenu () {
        String url = "http://www.99-bottles-of-beer.net/submitnewlanguage.html";
        String expectedResult = "Error: Precondition failed - Incomplete Input.";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        driver.findElement(
                By.xpath("//input[@name = 'submitlanguage']")
        ).click();

        WebElement errorMessage = driver.findElement(
                By.xpath("//div[@id = 'main']/p")
        );

        String actualResult = errorMessage.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_12 Precondition: Если на странице по ссылке http://www.99-bottles-of-beer.net/submitnewlanguage.html ,
    //пользователь нажмет кнопку Submit Language,  не заполнив информацию в обязательных полях, будет показана ошибка
    //с текстом
    //Error: Precondition failed - Incomplete Input.
    //Подтвертите, что в тексте ошибки слова Error, Precondition, Incomplete и Input написаны с большой буквы, а слово
    //failed  написано  с маленькой буквы.
    //Так же подтвердите, что в тексте ошибки содержатся знаки :, -  и .
    //Шаги:
    //1. Открыть вебсайт на странице
    //2. Нажать на кнопку Submit Language
    //3. Считать текст ошибки
    //4. Подтвердить requirenments
    //5. Закрыть браузер

    @Test
    public void testErrorMessageSpellingSubmitLanguageMenu () {
        String url = "http://www.99-bottles-of-beer.net/submitnewlanguage.html";
        String[] expectedResult1 = {":", "-", "."};
        String[] expectedResult = {"Error", "Precondition", "failed", "Incomplete", "Input"};

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        driver.findElement(
                By.xpath("//input[@name = 'submitlanguage']")
        ).click();

        WebElement errorMessage = driver.findElement(
                By.xpath("//div[@id = 'main']/p")
        );

        String errorMessageText = errorMessage.getText();

        String[] actualResult1 = new String[expectedResult1.length];
        int j = 0;
        for (int i = 0; i < errorMessageText.length(); i++) {
                if (String.valueOf(errorMessageText.charAt(i)).equals(expectedResult1[0])
                        ||  String.valueOf(errorMessageText.charAt(i)).equals(expectedResult1[1])
                        ||  String.valueOf(errorMessageText.charAt(i)).equals(expectedResult1[2])) {
                    actualResult1[j] = String.valueOf(errorMessageText.charAt(i));
                    j++;
                }
        }

        String[] actualResult = errorMessageText
                .replace(expectedResult1[0], "")
                .replace(expectedResult1[1],"")
                .replace(expectedResult1[2],"")
                .replaceAll("( )+", " ")
                .split(" ");

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult1, expectedResult1);

        driver.quit();
    }

    //TC_11_13 Подтвердите, что на странице по ссылке http://www.99-bottles-of-beer.net/submitnewlanguage.html
    // в первом пункте списка пользователь видит текст
    //IMPORTANT: Take your time! The more carefully you fill out this form (especially the language
    // name and description), the easier it will be for us and the faster your language will show up
    // on this page. We don't have the time to mess around with fixing your descriptions etc. Thanks
    // for your understanding.
    //Шаги:
    //1. Открыть вебсайт на странице
    //2. Считать текст
    //3. Подтвердить, что текст соответствует ожидаемому
    //4. Закрыть браузер

    @Test
    public void testImportantTextSubmitLanguageMenu () {
        String url = "http://www.99-bottles-of-beer.net/submitnewlanguage.html";
        String expectedResult = "IMPORTANT: Take your time! The more carefully you fill out this form (especially the " +
                "language name and description), the easier it will be for us and the faster your language will " +
                "show up on this page. We don't have the time to mess around with fixing your descriptions etc. " +
                "Thanks for your understanding.";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement importantTextPart = driver.findElement(
                By.xpath("//div[@id = 'main']//li[1]")
        );

        String actualResult = importantTextPart.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_14 Подтвердите, что нажав на пункт меню Browse Languages, пользователь увидит таблицу со следующими
    // названиями для первого и второго столбцов:
    //Language
    //Author
    //Шаги:
    //1. Открыть вебсайт на базовой странице
    //2. Нажать на пункт меню Browse Languages
    //3. Считать названия первого и второго столбцов таблицы
    //3. Подтвердить, что названия соответствует ожидаемым
    //4. Закрыть браузер

    @Test
    public void testFirstTwoTableColumnNameBrowseLanguageMenu () {
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult1 = "Language";
        String expectedResult2 = "Author";

        System.setProperty(CHROMEDRIVER, DRIVERPATH);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        driver.findElement(
                By.xpath("//div[@id = 'navigation']//a[@href = '/abc.html']")
        ).click();

        WebElement firstColumName = driver.findElement(
                By.xpath("//table[@Id = 'category']//tr/th[1]")
        );
        WebElement secondColumName = driver.findElement(
                By.xpath("//table[@Id = 'category']//tr/th[2]")
        );

        String actualResult1 = firstColumName.getText();
        String actualResult2 = secondColumName.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    //TC_11_15 Подтвердите, что на странице по базовой ссылке  пользователь НЕ увидит новые комментарии,
    //если нажмет на пункты меню Top List → New Comments
    //Шаги:
    //Придумать самостоятельно.

    @Test
    public void testNewCommentEmpty () {
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "D:\\Учеба\\Автоматизация\\Курсы\\JavaMavenTestNG_04\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";
        String expectedResult = "";

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        driver.findElement(
                By.xpath("//div[@id = 'navigation']//a[@href = '/toplist.html']")
        )
                .click();

        driver.findElement(
                By.xpath("//ul[@id = 'submenu']//a[@href = './newcomments.html']")
        )
                .click();

        WebElement emptyParagraph = driver.findElement(
                By.xpath("//div[@id = 'main']/p")
        );

        String actualResult = emptyParagraph.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_21 Подтвердите, что на странице по ссылке http://www.99-bottles-of-beer.net/submitnewlanguage.html
    //пользователь видит предупреждение IMPORTANT:, написанное белыми буквами bold шрифтом на красном фоне,
    //и что все буквы - capital
    //Шаги:
    //1. Открыть вебсайт на странице
    //2. Считать слово IMPORTANT: из списка
    //3. Подтвердить requirements
    //4. Закрыть браузер

    @Test
    public void testImportantStyle () {
        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "D:\\Учеба\\Автоматизация\\Курсы\\JavaMavenTestNG_04\\chromedriver_win32\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/submitnewlanguage.html";
        String expectedResult1 = "rgba(255, 255, 255, 1)"; //color : white
        String expectedResult2 = "rgba(255, 0, 0, 1)"; // background-color : red
        String expectedResult3 = "700"; // font-weight : bold
        String expectedResult4 = "IMPORTANT:";

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

        WebElement importantColor = driver.findElement(
                By.xpath("//div[@id = 'main']//li/span")
        );

        WebElement importantFont = driver.findElement(
                By.xpath("//div[@id = 'main']//li/span/b")
        );

        String actualResult1 = importantColor.getCssValue("color");
        String actualResult2 = importantColor.getCssValue("background-color");
        String actualResult3 = importantFont.getCssValue("font-weight");
        String actualResult4 = importantFont.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
        Assert.assertEquals(actualResult4, expectedResult4);

        driver.quit();
    }







}
