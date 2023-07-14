package ProfileUser.СontrolProfile;

import Utils.LogUtils;
import Utils.WaitUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.GetXProfileHistory;

@Owner("Makeenkov Igor")

public class ControlPromoCode {
    String Bonuses = "//button[@class=\"button-group__link\"]";
    @Description("Проверка, что промокод успешно активирован")
    public void controlPromoCode (WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));

        driver.get(GetXProfileHistory);
        waitUtils.waitForPageToLoad();

        driver.findElements(By.xpath(Bonuses)).get(2).click();
        waitUtils.waitForPageToLoad();

        waitUtils.waitForPageToLoad();

        //После
        byte[] controlPromocodeScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: Успешно примененного промокода", new ByteArrayInputStream(controlPromocodeScreen));

    }
}
