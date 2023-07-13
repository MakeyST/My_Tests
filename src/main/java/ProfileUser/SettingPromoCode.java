package ProfileUser;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.model.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.GetXProfileTest;
import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.promo;
import static io.qameta.allure.Allure.step;
@Link(name = "Test", type = "https://ppgetx.click/profile")
@Link(name = "Prod", type = "https://get22.cfd/profile")
@Owner("Makeenkov Igor")
public class SettingPromoCode {
    String InputPromoCode = "//input[@class=\"field field-group__field\"]";
    String ApplyPromoCode = "//button[@class=\"btn field-group__btn\"]";
    @Description("Промокоды в профиле юзера")
    public void settingPromoCode(WebDriver driver) throws InterruptedException, IOException {
        SoftAssert t = new SoftAssert();
        driver.get(GetXProfileTest);
        Thread.sleep(1000);

        //Клик по полю ввода промокода + ввод промокода
        step("Клик по полю ввода промокода + ввод промокода", Status.PASSED);
        driver.findElement(By.xpath(InputPromoCode)).sendKeys(promo);
        Thread.sleep(500);

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
        Thread.sleep(400);
        byte[] settingPromoCode = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Аллюр Аттач
        Allure.attachment("Логи", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: Промокод введен", new ByteArrayInputStream(settingPromoCode));
        t.assertAll();
    }
}
