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
    public void resetSlots (WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        SoftAssert t = new SoftAssert();
        driver.get(GetX_Slots);
        waitUtils.waitForPageToLoad();

        //До
        byte[] set_Reset_button_Slots_Before = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Кликаем по полю поиска и вводим данные
        driver.findElements(By.xpath(Input_Search)).get(3).click();
        driver.findElements(By.xpath(Input_Search)).get(3).sendKeys(Keys.CONTROL,"a");
        driver.findElements(By.xpath(Input_Search)).get(3).sendKeys(Keys.DELETE);
        driver.findElements(By.xpath(Input_Search)).get(3).sendKeys(Cleos_Gold);

        //Нашли
        byte[] set_Reset_button_Slots_CleosSlot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Проверка введеных данных
        String TextSearch_by_Games_Slots =  driver.findElements(By.xpath(Input_Search)).get(3).getAttribute("value");
        String ExpectedSearch_by_Games_Slots = Cleos_Gold;
        t.assertEquals(TextSearch_by_Games_Slots, ExpectedSearch_by_Games_Slots, "Проверка поля поиска. Игра: Cleo's Gold, ПРОВАЛЕНА!");
        t.assertNotNull(TextSearch_by_Games_Slots);

        //Нажимаем кнопку "Сбросить"
        driver.findElement(By.xpath(Button_Reset)).click();
        waitUtils.waitForPageToLoad();

        //Проверка кнопки Сбросить
        String TextReset = driver.findElement(By.xpath(TextResetdiv)).getText();
        String ExpectedReset = Reset;
        t.assertEquals(TextReset, ExpectedReset, "Проверка кнопки Сбросить, ПРОВАЛЕНА!");
        t.assertNotNull(TextReset);

        //После
        byte[] set_Reset_button_Slots_After = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Проверки и отчеты
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: До всех действий", new ByteArrayInputStream(set_Reset_button_Slots_Before));
        Allure.addAttachment("Скриншот: Выполнили поиск", new ByteArrayInputStream(set_Reset_button_Slots_CleosSlot));
        Allure.addAttachment("Скриншот: После того как все выполнили", new ByteArrayInputStream(set_Reset_button_Slots_After));
        t.assertAll();




    }
}
