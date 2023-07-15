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

public class ResetSlots {
    public void resetSlots(WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        SoftAssert t = new SoftAssert();
        driver.get(GetX_Slots);
        waitUtils.waitForPageToLoad();

        // Сделаем скриншот до выполнения действий
        byte[] set_Reset_button_Slots_Before = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Кликаем по полю поиска и вводим данные
        WebElement searchInput = driver.findElements(By.xpath(Input_Search)).get(3);
        searchInput.click();
        searchInput.sendKeys(Keys.CONTROL, "a");
        searchInput.sendKeys(Keys.DELETE);
        searchInput.sendKeys(Cleos_Gold);

        // Сделаем скриншот после ввода данных
        byte[] set_Reset_button_Slots_CleosSlot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Проверка введенных данных
        String textSearchByGamesSlots = searchInput.getAttribute("value");
        String expectedSearchByGamesSlots = Cleos_Gold;
        t.assertEquals(textSearchByGamesSlots, expectedSearchByGamesSlots, "Проверка поля поиска. Игра: Cleo's Gold, ПРОВАЛЕНА!");
        t.assertNotNull(textSearchByGamesSlots);

        // Нажимаем кнопку "Сбросить"
        driver.findElement(By.xpath(Button_Reset)).click();
        waitUtils.waitForPageToLoad();

        // Проверка кнопки "Сбросить"
        String textReset = driver.findElement(By.xpath(TextResetdiv)).getText();
        String expectedReset = Reset;
        t.assertEquals(textReset, expectedReset, "Проверка кнопки Сбросить, ПРОВАЛЕНА!");
        t.assertNotNull(textReset);

        // Сделаем скриншот после выполнения всех действий
        byte[] set_Reset_button_Slots_After = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Проверки и отчеты
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: До всех действий", new ByteArrayInputStream(set_Reset_button_Slots_Before));
        Allure.addAttachment("Скриншот: Выполнили поиск", new ByteArrayInputStream(set_Reset_button_Slots_CleosSlot));
        Allure.addAttachment("Скриншот: После того как все выполнили", new ByteArrayInputStream(set_Reset_button_Slots_After));
        t.assertAll();
    }
}
