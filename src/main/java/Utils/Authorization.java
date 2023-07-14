package Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.junit.jupiter.api.extension.TestWatcher;
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

import static Utils.LocatorsAuthorization.LoginPro100igo228;
import static Utils.LocatorsAuthorization.PasswordALLUSERS;
import static io.qameta.allure.Allure.step;

@Owner("Makeenkov Igor")
public class Authorization implements TestWatcher {
    @Step("Авторизация")
    @Description("Авторизация. Доступно несколько учетных записей, их нужно менять по необходимости")
    public void authorization(WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        System.out.println("-----Авторизация запущена-----");
        SoftAssert t = new SoftAssert();

        try {
            //Клик "Войти"
            step("Открыть окно авторизации", Status.PASSED);
            waitUtils.waitForPageToLoad();
            driver.findElement(By.id("btn-login")).click();
            waitUtils.waitForPageToLoad();
            byte[] loginPopup = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            //Ввод логина
            step("Ввод логина", Status.PASSED);
            waitUtils.waitForPageToLoad();
            driver.findElement(By.id("login-field-email")).sendKeys(LoginPro100igo228);
            waitUtils.waitForPageToLoad();
            byte[] screenshotLogin = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            //Проверка логина
            step("Проверка логина", Status.PASSED);
            String TextLogin = driver.findElement(By.id("login-field-email")).getAttribute("value");
            String ExpectedLogin = LoginPro100igo228;
            t.assertEquals(TextLogin, ExpectedLogin,"Проверка поля E-mail ПРОВАЛЕНА!");
            t.assertNotNull(TextLogin);
            System.out.println("*Проверка E-mail, ---> выполнено*");

            //Ввод пароль
            step("Ввод пароль", Status.PASSED);
            waitUtils.waitForPageToLoad();
            driver.findElement(By.id("login-field-password")).sendKeys(PasswordALLUSERS);
            waitUtils.waitForPageToLoad();
            byte[] screenshotPassword = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            //Проверка пароля
            step("Проверка пароля", Status.PASSED);
            String TextPassword = driver.findElement(By.id("login-field-password")).getAttribute("value");
            String ExpectedPassword = PasswordALLUSERS;
            t.assertEquals(TextPassword, ExpectedPassword, "Проверка поля Пароль ПРОВАЛЕНА!");
            t.assertNotNull(TextPassword);
            System.out.println("*Проверка пароля, ---> выполнено*");

            //Клик "Войти" в учётку
            step("Клик Войти в учётку", Status.PASSED);
            waitUtils.waitForPageToLoad();
            driver.findElement(By.id("login-form-login")).click();
            waitUtils.waitForPageToLoad();

            System.out.println("Авторизация пройдена");
            waitUtils.waitForPageToLoad();
            byte[] screenshotTo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            //Аллюр Аттач
            LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
            String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
            Allure.addAttachment("Логи", "text/plain", formattedLogs, ".txt");
            Allure.addAttachment("Скриншот: Попап авторизации", new ByteArrayInputStream(loginPopup));
            Allure.addAttachment("Скриншот: Ввод логина", new ByteArrayInputStream(screenshotLogin));
            Allure.addAttachment("Скриншот: Ввод пароля", new ByteArrayInputStream(screenshotPassword));
            Allure.addAttachment("Скриншот: вход", new ByteArrayInputStream(screenshotTo));

            t.assertAll();
        } catch (Exception e) {
            step("Шаг выполнен с ошибкой", Status.FAILED);
            Allure.addAttachment("Ошибка", "text/plain", e.getMessage(), ".txt");
            Allure.addAttachment("Скриншот ошибки", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            throw e;
        }
    }
}

