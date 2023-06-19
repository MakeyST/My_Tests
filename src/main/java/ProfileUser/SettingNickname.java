package ProfileUser;

import io.qameta.allure.Allure;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Description;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.qameta.allure.Allure.step;

@Owner("Makeenkov Igor")
@Description("Смена никнейма")
@Link(name = "Test", type = "https://s01getx.click/profile")
@Link(name = "Prod", type = "https://get22.cfd/profile")
public class SettingNickname {
    String GetXProfileTest = "https://s01getx.click/profile";
    String GetXProfilePROD = "https://get22.cfd/profile";
    String Setting = "//a[@class=\"rc-tabs__link js-open-tab\"]";
    String Nickname = "//input[@class=\"field field-group__field\"]";
    String Change = "//button[@class=\"btn field-group__btn\"]";
    @Step("Смена никнейма")
    public void setNickname (WebDriver driver) throws InterruptedException, IOException {
        driver.get(GetXProfileTest);
        SoftAssert t = new SoftAssert();
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //До
        FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        
        //Настройка профиля: "Аккаунт", "Настройки", "Доступ", "История"
        step("Настройка профиля: Аккаунт, Настройки, Доступ, История", Status.PASSED);
        Thread.sleep(500);
        driver.findElements(By.xpath(Setting)).get(0).click();

        //Ввод ENG никнейма
        step("Ввод ENG никнейма", Status.PASSED);
        Thread.sleep(500);
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(Nickname)).sendKeys("MakeyStar");

        //Клик "Сменить/Change"
        step("Клик Сменить/Change", Status.PASSED);
        Thread.sleep(500);
        driver.findElement(By.xpath(Change)).click();
        Thread.sleep(500);
        driver.navigate().refresh();
        Thread.sleep(500);

        //Проверка поля Никнейм (ENG)
        step("Проверка поля Никнейм (ENG)", Status.PASSED);
        Thread.sleep(500);
        String TextNicknameENG =  driver.findElement(By.xpath(Nickname)).getAttribute("value");
        String ExpectedNicknameENG = "MakeyStar";
        t.assertEquals(TextNicknameENG, ExpectedNicknameENG, "Проверка поля Никнейм ПРОВАЛЕНА![ENG]");
        t.assertNotNull(TextNicknameENG);
        System.out.println("*Проверка Никнейма [ENG], ---> выполнено*");
        //После
        FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));


        //Ввод RUS никнейма
        step("Ввод RUS никнейма", Status.PASSED);
        Thread.sleep(500);
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.xpath(Nickname)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(Nickname)).sendKeys("Игорь");

        //Клик "Сменить/Change"
        step("Клик Сменить/Change", Status.PASSED);
        Thread.sleep(500);
        driver.findElement(By.xpath(Change)).click();
        Thread.sleep(500);
        driver.navigate().refresh();
        Thread.sleep(500);

        //Проверка поля Никнейм (RUS)
        step("Проверка поля Никнейм (RUS)", Status.PASSED);
        Thread.sleep(500);
        String TextNicknameRUS =  driver.findElement(By.xpath(Nickname)).getAttribute("value");
        String ExpectedNicknameRUS = "Игорь";
        t.assertEquals(TextNicknameRUS, ExpectedNicknameRUS, "Проверка поля Никнейм ПРОВАЛЕНА![RUS]");
        t.assertNotNull(TextNicknameRUS);
        System.out.println("*Проверка Никнейма [RUS], ---> выполнено*");

        //После
        FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));
        Allure.attachment("Настройки, никнейм", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        t.assertAll();
    }
}
