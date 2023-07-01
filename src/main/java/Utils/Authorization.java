package Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Description;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static Utils.LocatorsAuthorization.LoginPro100igo228;
import static Utils.LocatorsAuthorization.PasswordALLUSERS;
import static io.qameta.allure.Allure.step;

@Link(name = "Test", type = "https://ppgetx.click/")
@Link(name = "Prod", type = "https://get22.cfd/")
@Owner("Makeenkov Igor")
@Description("Авторизация. Доступно несколько учетных записей, их нужно менять по необходимости")
public class Authorization implements TestWatcher {

    @Step("Авторизация")
    public void authorization(WebDriver driver) throws InterruptedException, IOException {
        System.out.println("-----Авторизация запущена-----");
        SoftAssert t = new SoftAssert();

        //Клик "Войти"
        step("Открыть окно авторизации", Status.PASSED);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("btn-login")).click();
        Thread.sleep(700);
        byte[] loginPopup = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Ввод логин
        step("Ввод логин", Status.PASSED);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("login-field-email")).sendKeys(LoginPro100igo228);
        Thread.sleep(500);
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("login-field-password")).sendKeys(PasswordALLUSERS);
        Thread.sleep(500);
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("login-form-login")).click();
        Thread.sleep(500);


        System.out.println("Авторизация пройдена");
        Thread.sleep(3000);
        byte[] screenshotTo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Аллюр Аттач
        Allure.attachment("Авторизация юзера через e-mail", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: Попап авторизации", new ByteArrayInputStream(loginPopup));
        Allure.addAttachment("Скриншот: Ввод логина", new ByteArrayInputStream(screenshotLogin));
        Allure.addAttachment("Скриншот: Ввод пароля", new ByteArrayInputStream(screenshotPassword));
        Allure.addAttachment("Скриншот: вход", new ByteArrayInputStream(screenshotTo));

        t.assertAll();
    }
}
