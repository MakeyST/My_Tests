package ProfileUser;

import Utils.LogUtils;
import Utils.WaitUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.model.Status;
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

import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.GetXProfileTest;
import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.promo;
import static io.qameta.allure.Allure.step;
@Owner("Makeenkov Igor")
public class SettingPromoCode {
    String InputPromoCode = "//input[@class=\"field field-group__field\"]";
    String ApplyPromoCode = "//button[@class=\"btn field-group__btn\"]";
    @Description("Промокоды в профиле юзера")
    public void settingPromoCode(WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        SoftAssert t = new SoftAssert();
        driver.get(GetXProfileTest);
        waitUtils.waitForPageToLoad();

        //Клик по полю ввода промокода + ввод промокода
        step("Клик по полю ввода промокода + ввод промокода", Status.PASSED);
        driver.findElement(By.xpath(InputPromoCode)).sendKeys(promo);
        waitUtils.waitForPageToLoad();

        //Проверка введенного промокода
        step("Проверка введенного промокода", Status.PASSED);
        String TextPromoCode =  driver.findElement(By.xpath(InputPromoCode)).getAttribute("value");
        String ExpectedPromoCode = promo;
        t.assertEquals(TextPromoCode, ExpectedPromoCode, "Проверка поля Промокод, ПРОВАЛЕНА!");
        t.assertNotNull(TextPromoCode);
        System.out.println("*Проверка поля Промокод, ---> выполнено*");

        //Применить
        step("Применить", Status.PASSED);
        driver.findElement(By.xpath(ApplyPromoCode)).click();
        waitUtils.waitForPageToLoad();
        byte[] settingPromoCode = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Аллюр Аттач
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: Промокод введен", new ByteArrayInputStream(settingPromoCode));
        t.assertAll();
    }
}
