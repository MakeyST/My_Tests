package ProfileUser;

import io.qameta.allure.Allure;
import io.qameta.allure.Link;
import io.qameta.allure.model.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.*;
import static io.qameta.allure.Allure.step;
@Link(name = "Test", type = "https://ppgetx.click/profile/setting")
@Link(name = "Prod", type = "https://get22.cfd/profile/setting")
public class SettingPasswordBack {
    String InputOldPassword = "//input[@class=\"field field-group__field field_icon\"]";

    public void setPasswordBack (WebDriver driver) throws InterruptedException, IOException {
        //Перейти на сайт
        step("Перейти на сайт", Status.PASSED);
        driver.get(GetXProfileTest);
        Thread.sleep(300);

        //Проверки
        SoftAssert t = new SoftAssert();

        //Старый пароль
        step("Старый пароль", Status.PASSED);
        driver.findElements(By.xpath(InputOldPassword)).get(0).click();
        Thread.sleep(200);
        driver.findElements(By.xpath(InputOldPassword)).get(0).sendKeys(OldPassword);
        Thread.sleep(200);

        //Проверка старого пароля
        step("Проверка старого пароля", Status.PASSED);
        String TextOldPassword =  driver.findElements(By.xpath(InputOldPassword)).get(0).getAttribute("value");
        String ExpectedOldPassword = "23makey23";
        t.assertEquals(TextOldPassword, ExpectedOldPassword, "Проверка поля Старый пароль ПРОВАЛЕНА!");
        t.assertNotNull(TextOldPassword);
        System.out.println("*Проверка поля Старый пароль, ---> выполнено*");

        //Новый пароль
        step("Новый пароль", Status.PASSED);
        driver.findElements(By.xpath(InputOldPassword)).get(1).click();
        Thread.sleep(200);
        driver.findElements(By.xpath(InputOldPassword)).get(1).sendKeys(NewPassword);
        Thread.sleep(200);

        //Проверка нового пароля
        step("Проверка нового пароля", Status.PASSED);
        String TextNewPassword =  driver.findElements(By.xpath(InputOldPassword)).get(1).getAttribute("value");
        String ExpectedNewPassword = "23Makey23";
        t.assertEquals(TextNewPassword, ExpectedNewPassword, "Проверка поля Новый пароль ПРОВАЛЕНА!");
        t.assertNotNull(TextNewPassword);
        System.out.println("*Проверка поля Новый пароль, ---> выполнено*");

        //Повторить новый пароль
        step("Повторить новый пароль", Status.PASSED);
        driver.findElements(By.xpath(InputOldPassword)).get(2).click();
        Thread.sleep(200);
        driver.findElements(By.xpath(InputOldPassword)).get(2).sendKeys(ConfirmPassword);
        Thread.sleep(200);

        //Проверка повтора нового пароля
        step("Проверка повтора нового пароля", Status.PASSED);
        String TextConfirmPassword =  driver.findElements(By.xpath(InputOldPassword)).get(2).getAttribute("value");
        String ExpectedConfirmPassword = "23Makey23";
        t.assertEquals(TextConfirmPassword, ExpectedConfirmPassword, "Проверка поля Подтверждения нового пароля ПРОВАЛЕНА!");
        t.assertNotNull(TextConfirmPassword);
        System.out.println("*Проверка поля Подтверждения нового пароля, ---> выполнено*");
        Thread.sleep(500);
        byte[] setPasswordBack = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Аллюр Аттач
        Allure.attachment("Настройки, пароль вернуть назад", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: Пароль успешно изменен(вернули старый)", new ByteArrayInputStream(setPasswordBack));

        //Выход
        step("Выход", Status.PASSED);
        t.assertAll();

    }
}
