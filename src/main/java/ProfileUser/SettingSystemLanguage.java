package ProfileUser;

import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import io.qameta.allure.model.Status;
import org.apache.commons.io.FileUtils;
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
import java.util.Date;

import static io.qameta.allure.Allure.step;

@Owner("Makeenkov Igor")
@Description("Смена языка")
public class SettingSystemLanguage {
    String GetXProfileTest = "https://s01getx.click/profile";
    String GetXProfilePROD = "https://get22.cfd/profile";
    String Setting = "//a[@class=\"rc-tabs__link js-open-tab\"]";
    String SystemLanguage = "//button[@class=\"select-box__main\"]";
    String SelectboxLanguage = "//a[@class=\"select-box__item\"]";
    String LookLanguage = "//button[@class=\"select-box__main\"]";
    public void settingSystemLanguage (WebDriver driver) throws InterruptedException, IOException {
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
        Thread.sleep(700);
        driver.findElements(By.xpath(Setting)).get(0).click();

        //Выбор языка системы
        step("Выбор языка системы", Status.PASSED);
        Thread.sleep(700);
        driver.findElements(By.xpath(SystemLanguage)).get(0).click();

        //Выбор в селектбоксе
        step("Выбор в селектбоксе", Status.PASSED);
        Thread.sleep(700);
        driver.findElements(By.xpath(SelectboxLanguage)).get(0).click();

        //Проверка какой язык установлен
        step("Проверка какой язык установлен", Status.PASSED);
        Thread.sleep(900);
        String TextSystemENG =  driver.findElement(By.xpath(LookLanguage)).getText();
        String ExpectedSystemENG = "English";
        t.assertEquals(TextSystemENG, ExpectedSystemENG, "Проверка English системы ПРОВАЛЕНА![ENG]");
        t.assertNotNull(TextSystemENG);
        System.out.println("*Проверка English системы [ENG], ---> выполнено*");

        FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));


        //Обратная проверка (возвращаем RUS)
        step("Обратная проверка (возвращаем RUS)", Status.PASSED);
        //Выбор языка системы
        step("Выбор языка системы", Status.PASSED);
        Thread.sleep(700);
        driver.findElements(By.xpath(SystemLanguage)).get(0).click();

        //Выбор в селектбоксе
        step("Выбор в селектбоксе", Status.PASSED);
        Thread.sleep(700);
        driver.findElements(By.xpath(SelectboxLanguage)).get(0).click();

        //Проверка какой язык установлен
        step("Проверка какой язык установлен", Status.PASSED);
        Thread.sleep(900);
        String TextSystemRUS =  driver.findElement(By.xpath(LookLanguage)).getText();
        String ExpectedSystemRUS = "Русский";
        t.assertEquals(TextSystemRUS, ExpectedSystemRUS, "Проверка Русский системы ПРОВАЛЕНА![RUS]");
        t.assertNotNull(TextSystemRUS);
        System.out.println("*Проверка Русский системы [RUS], ---> выполнено*");

        FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));

        Allure.attachment("Настройки, смена языка", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        t.assertAll();

    }
}
