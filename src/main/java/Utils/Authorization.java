package Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Description;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static io.qameta.allure.Allure.step;
@Link(name = "Test", type = "https://s01getx.click/profile")
@Link(name = "Prod", type = "https://get22.cfd/profile")
@Owner("Makeenkov Igor")
@Description("Авторизация. Доступно несколько учетных записей, их нужно менять по необходимости")
public class Authorization implements TestWatcher {
    //pro100igo228@gmail.com
    String LoginPro100igo228 = "pro100igo228@gmail.com";
    //getxmakeystar@gmail.com
    String LoginGetxMakeyStar = "getxmakeystar@gmail.com";
    //Пароль от всех учеток мною созданых
    String PasswordALLUSERS = "23Makey23";
    @Step("Авторизация")
    public void authorization(WebDriver driver) throws InterruptedException, IOException {
        System.out.println("-----Авторизация запущена-----");
        SoftAssert t = new SoftAssert();
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //Клик "Войти"
        step("Войти", Status.PASSED);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("btn-login")).click();

        //Ввод логин
        step("Ввод логин", Status.PASSED);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("login-field-email")).sendKeys(LoginPro100igo228);

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
        Allure.getLifecycle().addAttachment("Скриншот после прохождения теста", "image/png", "png",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        Allure.attachment("Авторизация юзера через e-mail", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        t.assertAll();
    }
}
