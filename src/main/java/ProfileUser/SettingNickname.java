package ProfileUser;

import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Description;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Owner("Makeenkov Igor")
@Description("Смена никнейма")
public class SettingNickname {
    String GetXProfileTest = "https://s01getx.click/profile";
    String GetXProfilePROD = "https://get22.cfd/profile";
    String Setting = "//a[@class=\"rc-tabs__link js-open-tab\"]";
    String Nickname = "//input[@class=\"field field-group__field\"]";
    String Change = "//button[@class=\"btn field-group__btn\"]";
    @Step("Смена никнейма")
    public void setNickname (WebDriver driver) throws InterruptedException  {
        driver.get(GetXProfilePROD);
        SoftAssert t = new SoftAssert();
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //До
        try {
            FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        } catch (IOException e){e.printStackTrace();}

        //Настройка профиля: "Аккаунт", "Настройки", "Доступ", "История"
        Thread.sleep(500);
        driver.findElements(By.xpath(Setting)).get(0).click();

        //Ввод ENG никнейма
        Thread.sleep(500);
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(Nickname)).sendKeys("MakeyStar");

        //Клик "Сменить/Change"
        Thread.sleep(500);
        driver.findElement(By.xpath(Change)).click();
        Thread.sleep(500);
        driver.navigate().refresh();
        Thread.sleep(500);

        //Проверка поля Никнейм (ENG)
        Thread.sleep(500);
        String TextNicknameENG =  driver.findElement(By.xpath(Nickname)).getAttribute("value");
        String ExpectedNicknameENG = "MakeyStar";
        t.assertEquals(TextNicknameENG, ExpectedNicknameENG, "Проверка поля Никнейм ПРОВАЛЕНА![ENG]");
        t.assertNotNull(TextNicknameENG);
        System.out.println("*Проверка Никнейма [ENG], ---> выполнено*");
        //После
        try {
            FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        } catch (IOException e){e.printStackTrace();}

        //Ввод RUS никнейма
        Thread.sleep(500);
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(Nickname)).sendKeys("Игорь");

        //Клик "Сменить/Change"
        Thread.sleep(500);
        driver.findElement(By.xpath(Change)).click();
        Thread.sleep(500);
        driver.navigate().refresh();
        Thread.sleep(500);

        //Проверка поля Никнейм (RUS)
        Thread.sleep(500);
        String TextNicknameRUS =  driver.findElement(By.xpath(Nickname)).getAttribute("value");
        String ExpectedNicknameRUS = "Игорь";
        t.assertEquals(TextNicknameRUS, ExpectedNicknameRUS, "Проверка поля Никнейм ПРОВАЛЕНА![RUS]");
        t.assertNotNull(TextNicknameRUS);
        System.out.println("*Проверка Никнейма [RUS], ---> выполнено*");

        //После
        try {
            FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        } catch (IOException e){e.printStackTrace();}

        Allure.attachment("Dynamic attachment", "attachment content");
        t.assertAll();
    }
}
