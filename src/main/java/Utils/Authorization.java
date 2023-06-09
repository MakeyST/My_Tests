package Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Description;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Owner("Makeenkov Igor")
@Description("Авторизация. Доступно несколько учетных записей, их нужно менять по необходимости")
public class Authorization {
    //pro100igo228@gmail.com
    String LoginPro100igo228 = "pro100igo228@gmail.com";
    //getxmakeystar@gmail.com
    String LoginGetxMakeyStar = "getxmakeystar@gmail.com";
    //Пароль от всех учеток мною созданых
    String PasswordALLUSERS = "23Makey23";
    @Step("Авторизация")
    public void authorization(WebDriver driver) throws InterruptedException {
        System.out.println("-----Авторизация запущена-----");
        SoftAssert t = new SoftAssert();
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        } catch (IOException e){e.printStackTrace();}

        //Клик "Войти"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("btn-login")).click();

        //Ввод логин
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("login-field-email")).sendKeys(LoginPro100igo228);

        //Проверка логина
        String TextLogin = driver.findElement(By.id("login-field-email")).getAttribute("value");
        String ExpectedLogin = LoginPro100igo228;
        t.assertEquals(TextLogin, ExpectedLogin,"Проверка поля E-mail ПРОВАЛЕНА!");
        t.assertNotNull(TextLogin);
        System.out.println("*Проверка E-mail, ---> выполнено*");

        //Ввод пароль
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("login-field-password")).sendKeys(PasswordALLUSERS);

        //Проверка пароля
        String TextPassword = driver.findElement(By.id("login-field-password")).getAttribute("value");
        String ExpectedPassword = PasswordALLUSERS;
        t.assertEquals(TextPassword, ExpectedPassword, "Проверка поля Пароль ПРОВАЛЕНА!");
        t.assertNotNull(TextPassword);
        System.out.println("*Проверка пароля, ---> выполнено*");

        //Клик "Войти" в учётку
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("login-form-login")).click();
        Thread.sleep(500);

        System.out.println("Авторизация пройдена");
        try {
            FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        } catch (IOException e){e.printStackTrace();}

        Allure.attachment("Dynamic attachment", "attachment content");
        t.assertAll();
    }
}
