package ProfileUser.СontrolProfile;

import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Description;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Owner("Makeenkov Igor")
@Description("Проверка, что промокод успешно активирован")
public class ControlPromoCode {
    String GetXProfileHistory = "https://ppgetx.click/profile/history";
    String Bonuses = "//button[@class=\"button-group__link\"]";

    public void controlPromoCode (WebDriver driver) throws InterruptedException {
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //До
        try {
            FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        } catch (IOException e){e.printStackTrace();}

        driver.get(GetXProfileHistory);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElements(By.xpath(Bonuses)).get(2).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //После
        try {
            FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        } catch (IOException e){e.printStackTrace();}
        Allure.attachment("Dynamic attachment", "attachment content");


    }
}
