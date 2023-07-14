package Slots;

import Utils.LogUtils;
import Utils.WaitUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static Slots.ConfigFileSlots.LocatorsSlots.GetX_Slots;
import static Slots.ConfigFileSlots.LocatorsSlots.Recent_button;

public class RecentSlots {
    public void recentSlots (WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        SoftAssert t = new SoftAssert();
        driver.get(GetX_Slots);
        waitUtils.waitForPageToLoad();

        //До
        byte[] set_Resent_Before = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Недавние игры
        driver.findElements(By.xpath(Recent_button)).get(1).click();
        waitUtils.waitForPageToLoad();

        //После
        byte[] set_Resent_After = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Проверки и отчеты
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: Было", new ByteArrayInputStream(set_Resent_Before));
        Allure.addAttachment("Скриншот: Стало", new ByteArrayInputStream(set_Resent_After));
        t.assertAll();

    }
}
