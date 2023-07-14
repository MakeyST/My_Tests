package ProfileUser;

import Utils.LogUtils;
import Utils.WaitUtils;
import io.qameta.allure.Allure;
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

import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.*;
import static io.qameta.allure.Allure.step;
public class SettingPassword {
    String InputOldPassword = "//input[@class=\"field field-group__field field_icon\"]";

    public void setPassword (WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        //Перейти на сайт
        step("Перейти на сайт", Status.PASSED);
        driver.get(GetXProfileTest);
        waitUtils.waitForPageToLoad();

        //Проверки
        SoftAssert t = new SoftAssert();

        //Старый пароль
        step("Старый пароль", Status.PASSED);
        driver.findElements(By.xpath(InputOldPassword)).get(0).click();
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(InputOldPassword)).get(0).sendKeys(OldPassword);
        waitUtils.waitForPageToLoad();

        //Проверка старого пароля
        step("Проверка старого пароля", Status.PASSED);
        String TextOldPassword =  driver.findElements(By.xpath(InputOldPassword)).get(0).getAttribute("value");
        String ExpectedOldPassword = "23Makey23";
        t.assertEquals(TextOldPassword, ExpectedOldPassword, "Проверка поля Старый пароль ПРОВАЛЕНА!");
        t.assertNotNull(TextOldPassword);
        System.out.println("*Проверка поля Старый пароль, ---> выполнено*");

        //Новый пароль
        step("Новый пароль", Status.PASSED);
        driver.findElements(By.xpath(InputOldPassword)).get(1).click();
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(InputOldPassword)).get(1).sendKeys(NewPassword);
        waitUtils.waitForPageToLoad();

        //Проверка нового пароля
        step("Проверка нового пароля", Status.PASSED);
        String TextNewPassword =  driver.findElements(By.xpath(InputOldPassword)).get(1).getAttribute("value");
        String ExpectedNewPassword = "23makey23";
        t.assertEquals(TextNewPassword, ExpectedNewPassword, "Проверка поля Новый пароль ПРОВАЛЕНА!");
        t.assertNotNull(TextNewPassword);
        System.out.println("*Проверка поля Новый пароль, ---> выполнено*");

        //Повторить новый пароль
        step("Повторить новый пароль", Status.PASSED);
        driver.findElements(By.xpath(InputOldPassword)).get(2).click();
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(InputOldPassword)).get(2).sendKeys(ConfirmPassword);
        waitUtils.waitForPageToLoad();

        //Проверка повтора нового пароля
        step("Проверка повтора нового пароля", Status.PASSED);
        String TextConfirmPassword =  driver.findElements(By.xpath(InputOldPassword)).get(2).getAttribute("value");
        String ExpectedConfirmPassword = "23makey23";
        t.assertEquals(TextConfirmPassword, ExpectedConfirmPassword, "Проверка поля Подтверждения нового пароля ПРОВАЛЕНА!");
        t.assertNotNull(TextConfirmPassword);
        System.out.println("*Проверка поля Подтверждения нового пароля, ---> выполнено*");
        waitUtils.waitForPageToLoad();
        byte[] setPassword = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Аллюр Аттач
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: Пароль успешно изменен", new ByteArrayInputStream(setPassword));


        //Выход
        t.assertAll();

    }
}
