package ProfileUser;

import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
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
import java.util.Date;

@Owner("Makeenkov Igor")
@Description("Промокоды в профиле юзера")
public class SettingPromoCode {
    String GetXProfileTest = "https://s01getx.click/profile";
    String GetXProfilePROD = "https://get22.cfd/profile";
    String InputPromoCode = "//input[@class=\"field field-group__field\"]";
    String promo = "482IE334JPWGI1GRFSO1";
    String ApplyPromoCode = "//button[@class=\"btn field-group__btn\"]";
    public void settingPromoCode(WebDriver driver) throws InterruptedException{
        SoftAssert t = new SoftAssert();
        driver.get(GetXProfilePROD);
        Thread.sleep(1000);
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        //До
        try {
            FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        } catch (IOException e){e.printStackTrace();}

        //Клик по полю ввода промокода + ввод промокода
        driver.findElement(By.xpath(InputPromoCode)).sendKeys(promo);
        Thread.sleep(500);

        //Проверка введенного промокода
        String TextPromoCode =  driver.findElement(By.xpath(InputPromoCode)).getAttribute("value");
        String ExpectedPromoCode = "482IE334JPWGI1GRFSO1";
        t.assertEquals(TextPromoCode, ExpectedPromoCode, "Проверка поля Промокод, ПРОВАЛЕНА!");
        t.assertNotNull(TextPromoCode);
        System.out.println("*Проверка поля Промокод, ---> выполнено*");

        //Применить
        driver.findElement(By.xpath(ApplyPromoCode)).click();

        //После
        try {
            FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        } catch (IOException e){e.printStackTrace();}
        Thread.sleep(200);

        Allure.attachment("Dynamic attachment", "attachment content");
        t.assertAll();
    }
}
