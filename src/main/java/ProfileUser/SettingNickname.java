package ProfileUser;

import Utils.LogUtils;
import Utils.WaitUtils;
import io.qameta.allure.*;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.GetXProfileTest;
import static io.qameta.allure.Allure.step;

@Owner("Makeenkov Igor")
public class SettingNickname {
    String Setting = "//a[@class=\"rc-tabs__link js-open-tab\"]";
    String Nickname = "//input[@class=\"field field-group__field\"]";
    String Change = "//button[@class=\"btn field-group__btn\"]";
    @Step("Смена никнейма")
    @Description("Смена никнейма")
    public void setNickname (WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        driver.get(GetXProfileTest);
        SoftAssert t = new SoftAssert();
        //До
        byte[] setNicknameAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        
        //Настройка профиля: "Аккаунт", "Настройки", "Доступ", "История"
        step("Настройка профиля: Аккаунт, Настройки, Доступ, История", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(Setting)).get(0).click();

        //Ввод ENG никнейма
        step("Ввод ENG никнейма", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(Nickname)).sendKeys("MakeyStar");

        //Клик "Сменить/Change"
        step("Клик Сменить/Change", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElement(By.xpath(Change)).click();
        waitUtils.waitForPageToLoad();
        driver.navigate().refresh();
        waitUtils.waitForPageToLoad();

        //Проверка поля Никнейм (ENG)
        step("Проверка поля Никнейм (ENG)", Status.PASSED);
        waitUtils.waitForPageToLoad();
        String TextNicknameENG =  driver.findElement(By.xpath(Nickname)).getAttribute("value");
        String ExpectedNicknameENG = "MakeyStar";
        t.assertEquals(TextNicknameENG, ExpectedNicknameENG, "Проверка поля Никнейм ПРОВАЛЕНА![ENG]");
        t.assertNotNull(TextNicknameENG);
        System.out.println("*Проверка Никнейма [ENG], ---> выполнено*");
        //После
        byte[] setNicknameAsTo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);


        //Ввод RUS никнейма
        step("Ввод RUS никнейма", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(Nickname)).sendKeys("Игорь");

        //Клик "Сменить/Change"
        step("Клик Сменить/Change", Status.PASSED);
        waitUtils.waitForPageToLoad();
        driver.findElement(By.xpath(Change)).click();
        waitUtils.waitForPageToLoad();
        driver.navigate().refresh();
        waitUtils.waitForPageToLoad();

        //Проверка поля Никнейм (RUS)
        step("Проверка поля Никнейм (RUS)", Status.PASSED);
        waitUtils.waitForPageToLoad();
        String TextNicknameRUS =  driver.findElement(By.xpath(Nickname)).getAttribute("value");
        String ExpectedNicknameRUS = "Игорь";
        t.assertEquals(TextNicknameRUS, ExpectedNicknameRUS, "Проверка поля Никнейм ПРОВАЛЕНА![RUS]");
        t.assertNotNull(TextNicknameRUS);
        System.out.println("*Проверка Никнейма [RUS], ---> выполнено*");

        //После
        byte[] setNicknameAsToTo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Аллюр отчеты
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: До смены никнейма", new ByteArrayInputStream(setNicknameAs));
        Allure.addAttachment("Скриншот: Смена на английский никнейм", new ByteArrayInputStream(setNicknameAsTo));
        Allure.addAttachment("Скриншот: Смена на русский никнейм", new ByteArrayInputStream(setNicknameAsToTo));
        t.assertAll();
    }
}
