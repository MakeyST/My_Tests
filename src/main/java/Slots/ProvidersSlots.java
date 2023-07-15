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

public class ProvidersSlots {
    public void providerSlots(WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        SoftAssert t = new SoftAssert();
        driver.get(GetX_Slots);
        waitUtils.waitForPageToLoad();

        // Сделаем скриншот до выполнения действий
        byte[] set_Providers_Before = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Выбираем провайдера через поиск
        driver.findElements(By.xpath(Input_Search)).get(2).click();
        driver.findElements(By.xpath(Input_Search)).get(2).sendKeys(Keys.CONTROL, "a");
        driver.findElements(By.xpath(Input_Search)).get(2).sendKeys(Keys.DELETE);
        driver.findElements(By.xpath(Input_Search)).get(2).sendKeys(Provider_Dlv);

        // Сделаем скриншот после выполнения поиска провайдера
        byte[] set_Providers_Dlv = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Проверка введенных данных
        String TextProviders_Dlv = driver.findElements(By.xpath(Input_Search)).get(2).getAttribute("value");
        String ExpectedProviders_Dlv = Provider_Dlv;
        t.assertEquals(TextProviders_Dlv, ExpectedProviders_Dlv, "Проверка поля поиска провайдера Dlv, ПРОВАЛЕНА!");
        t.assertNotNull(TextProviders_Dlv);

        // Выбираем DLV
        driver.findElements(By.xpath(Dlv_filter)).get(35).click();
        waitUtils.waitForPageToLoad();

        // Сделаем скриншот после выполнения всех действий
        byte[] set_Providers_After = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Проверки и отчеты
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: До всех действий", new ByteArrayInputStream(set_Providers_Before));
        Allure.addAttachment("Скриншот: Выполнили поиск", new ByteArrayInputStream(set_Providers_Dlv));
        Allure.addAttachment("Скриншот: После выполнения всех действий", new ByteArrayInputStream(set_Providers_After));
        t.assertAll();
    }
}
