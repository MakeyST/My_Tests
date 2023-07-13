package Slots;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static Slots.ConfigFileSlots.LocatorsSlots.*;


public class SearchbyGamesSlots {
    public void searchbyGamesSlots (WebDriver driver) throws InterruptedException, IOException {
        SoftAssert t = new SoftAssert();
        driver.get(GetX_Slots);
        Thread.sleep(1000);

        //До
        byte[] set_Search_by_Games_Slots_Before = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Кликаем по полю поиска и вводим данные
        driver.findElements(By.xpath(Input_Search)).get(3).click();
        driver.findElements(By.xpath(Input_Search)).get(3).sendKeys(Keys.CONTROL,"a");
        driver.findElements(By.xpath(Input_Search)).get(3).sendKeys(Keys.DELETE);
        driver.findElements(By.xpath(Input_Search)).get(3).sendKeys(Cleos_Gold);

        //После
        byte[] set_Search_by_Games_Slots_After = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Проверка введеных данных
        String TextSearch_by_Games_Slots =  driver.findElements(By.xpath(Input_Search)).get(3).getAttribute("value");
        String ExpectedSearch_by_Games_Slots = Cleos_Gold;
        t.assertEquals(TextSearch_by_Games_Slots, ExpectedSearch_by_Games_Slots, "Проверка поля поиска. Игра: Cleo's Gold, ПРОВАЛЕНА!");
        t.assertNotNull(TextSearch_by_Games_Slots);

        //До
        byte[] set_Search_by_Games_Slots_Before01 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Кликаем по полю поиска и вводим данные
        driver.findElements(By.xpath(Input_Search)).get(3).click();
        driver.findElements(By.xpath(Input_Search)).get(3).sendKeys(Keys.CONTROL,"a");
        driver.findElements(By.xpath(Input_Search)).get(3).sendKeys(Keys.DELETE);
        driver.findElements(By.xpath(Input_Search)).get(3).sendKeys(Crazy_Monkey);

        //После
        byte[] set_Search_by_Games_Slots_After01 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Проверка введеных данных
        String TextSearch_by_Games_Slots01 =  driver.findElements(By.xpath(Input_Search)).get(3).getAttribute("value");
        String ExpectedSearch_by_Games_Slots01 = Crazy_Monkey;
        t.assertEquals(TextSearch_by_Games_Slots01, ExpectedSearch_by_Games_Slots01, "Проверка поля поиска. Игра: Crazy Monkey, ПРОВАЛЕНА!");
        t.assertNotNull(TextSearch_by_Games_Slots01);

        //Проверки и отчеты
        Allure.attachment("Логи", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: До начала поиска Cleo's Gold", new ByteArrayInputStream(set_Search_by_Games_Slots_Before));
        Allure.addAttachment("Скриншот: Поиск Cleo's Gold", new ByteArrayInputStream(set_Search_by_Games_Slots_After));
        Allure.addAttachment("Скриншот: До начала поиска Crazy Monkey", new ByteArrayInputStream(set_Search_by_Games_Slots_Before01));
        Allure.addAttachment("Скриншот: Поиск Crazy Monkey", new ByteArrayInputStream(set_Search_by_Games_Slots_After01));
        t.assertAll();

    }
}
