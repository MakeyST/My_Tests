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
import static io.qameta.allure.Allure.step;
@Owner("Makeenkov Igor")
public class SettingSystemLanguage {
    String Setting = "//a[@class=\"rc-tabs__link js-open-tab\"]";
    String SystemLanguage = "//button[@class=\"select-box__main\"]";
    String SelectboxLanguage = "//a[@class=\"select-box__item\"]";
    String LookLanguage = "//button[@class=\"select-box__main\"]";
    @Description("Смена языка")
    public void settingSystemLanguage (WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        driver.get(GetXProfileTest);
        SoftAssert t = new SoftAssert();
        //До
        byte[] settingSystemLanguageAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Настройка профиля: "Аккаунт", "Настройки", "Доступ", "История"
        step("Настройка профиля: Аккаунт, Настройки, Доступ, История", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(Setting)).get(0).click();

        //Выбор языка системы
        step("Выбор языка системы", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(SystemLanguage)).get(0).click();

        //Выбор в селектбоксе
        step("Выбор в селектбоксе", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(SelectboxLanguage)).get(0).click();

        //Проверка какой язык установлен
        step("Проверка какой язык установлен", Status.PASSED);
        waitUtils.waitForPageToLoad();
        String TextSystemENG =  driver.findElement(By.xpath(LookLanguage)).getText();
        String ExpectedSystemENG = "English";
        t.assertEquals(TextSystemENG, ExpectedSystemENG, "Проверка English системы ПРОВАЛЕНА![ENG]");
        t.assertNotNull(TextSystemENG);
        System.out.println("*Проверка English системы [ENG], ---> выполнено*");

        byte[] settingSystemLanguageENG = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);


        //Обратная проверка (возвращаем RUS)
        step("Обратная проверка (возвращаем RUS)", Status.PASSED);
        //Выбор языка системы
        step("Выбор языка системы", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(SystemLanguage)).get(0).click();

        //Выбор в селектбоксе
        step("Выбор в селектбоксе", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(SelectboxLanguage)).get(0).click();

        //Проверка какой язык установлен
        step("Проверка какой язык установлен", Status.PASSED);
        waitUtils.waitForPageToLoad();
        String TextSystemRUS =  driver.findElement(By.xpath(LookLanguage)).getText();
        String ExpectedSystemRUS = "Русский";
        t.assertEquals(TextSystemRUS, ExpectedSystemRUS, "Проверка Русский системы ПРОВАЛЕНА![RUS]");
        t.assertNotNull(TextSystemRUS);
        System.out.println("*Проверка Русский системы [RUS], ---> выполнено*");

        byte[] settingSystemLanguageRUS = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Аллюр Аттач
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: До смены языка", new ByteArrayInputStream(settingSystemLanguageAs));
        Allure.addAttachment("Скриншот: Англ язык актив", new ByteArrayInputStream(settingSystemLanguageENG));
        Allure.addAttachment("Скриншот: Рус язык актив", new ByteArrayInputStream(settingSystemLanguageRUS));

        t.assertAll();

    }
}
