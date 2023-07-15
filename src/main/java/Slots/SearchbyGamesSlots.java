package Slots;

import Utils.LogUtils;
import Utils.WaitUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static Slots.ConfigFileSlots.LocatorsSlots.*;


public class SearchbyGamesSlots {
    public void searchbyGamesSlots(WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        SoftAssert t = new SoftAssert();
        driver.get(GetX_Slots);
        waitUtils.waitForPageToLoad();

        // Сделаем скриншот до выполнения действий для Cleo's Gold
        byte[] set_Search_by_Games_Slots_Before = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Кликаем по полю поиска и вводим данные для Cleo's Gold
        WebElement searchInput = driver.findElements(By.xpath(Input_Search)).get(3);
        searchInput.click();
        searchInput.sendKeys(Keys.CONTROL, "a");
        searchInput.sendKeys(Keys.DELETE);
        searchInput.sendKeys(Cleos_Gold);

        // Сделаем скриншот после ввода данных для Cleo's Gold
        byte[] set_Search_by_Games_Slots_After = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Проверка введенных данных для Cleo's Gold
        String textSearchByGamesSlots = searchInput.getAttribute("value");
        String expectedSearchByGamesSlots = Cleos_Gold;
        t.assertEquals(textSearchByGamesSlots, expectedSearchByGamesSlots, "Проверка поля поиска. Игра: Cleo's Gold, ПРОВАЛЕНА!");
        t.assertNotNull(textSearchByGamesSlots);

        // Сделаем скриншот до выполнения действий для Crazy Monkey
        byte[] set_Search_by_Games_Slots_Before01 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Кликаем по полю поиска и вводим данные для Crazy Monkey
        searchInput.click();
        searchInput.sendKeys(Keys.CONTROL, "a");
        searchInput.sendKeys(Keys.DELETE);
        searchInput.sendKeys(Crazy_Monkey);

        // Сделаем скриншот после ввода данных для Crazy Monkey
        byte[] set_Search_by_Games_Slots_After01 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Проверка введенных данных для Crazy Monkey
        String textSearchByGamesSlots01 = searchInput.getAttribute("value");
        String expectedSearchByGamesSlots01 = Crazy_Monkey;
        t.assertEquals(textSearchByGamesSlots01, expectedSearchByGamesSlots01, "Проверка поля поиска. Игра: Crazy Monkey, ПРОВАЛЕНА!");
        t.assertNotNull(textSearchByGamesSlots01);

        // Проверки и отчеты
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: До начала поиска Cleo's Gold", new ByteArrayInputStream(set_Search_by_Games_Slots_Before));
        Allure.addAttachment("Скриншот: Поиск Cleo's Gold", new ByteArrayInputStream(set_Search_by_Games_Slots_After));
        Allure.addAttachment("Скриншот: До начала поиска Crazy Monkey", new ByteArrayInputStream(set_Search_by_Games_Slots_Before01));
        Allure.addAttachment("Скриншот: Поиск Crazy Monkey", new ByteArrayInputStream(set_Search_by_Games_Slots_After01));
        t.assertAll();
    }
}
