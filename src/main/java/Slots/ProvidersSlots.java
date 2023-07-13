package Slots;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static Slots.ConfigFileSlots.LocatorsSlots.*;

public class ProvidersSlots {
    public void providerSlots (WebDriver driver) throws InterruptedException, IOException {
        SoftAssert t = new SoftAssert();
        driver.get(GetX_Slots);
        Thread.sleep(1000);

        //До
        byte[] set_Providers_Before = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Выбираем провайдера через поиск
        driver.findElements(By.xpath(Input_Search)).get(2).click();
        driver.findElements(By.xpath(Input_Search)).get(2).sendKeys(Keys.CONTROL,"a");
        driver.findElements(By.xpath(Input_Search)).get(2).sendKeys(Keys.DELETE);
        driver.findElements(By.xpath(Input_Search)).get(2).sendKeys(Provider_Dlv);

        //Нашли
        byte[] set_Providers_Dlv = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Проверка введеных данных
        String TextProviders_Dlv =  driver.findElements(By.xpath(Input_Search)).get(2).getAttribute("value");
        String ExpectedProviders_Dlv = Provider_Dlv;
        t.assertEquals(TextProviders_Dlv, ExpectedProviders_Dlv, "Проверка поля поиска провайдера Dlv, ПРОВАЛЕНА!");
        t.assertNotNull(TextProviders_Dlv);

        //Выбираем DLV
        driver.findElements(By.xpath(Dlv_filter)).get(35).click();
        Thread.sleep(500);

        //После
        byte[] set_Providers_After = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Проверки и отчеты
        Allure.attachment("Логи", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: До всех действий", new ByteArrayInputStream(set_Providers_Before));
        Allure.addAttachment("Скриншот: Выполнили поиск", new ByteArrayInputStream(set_Providers_Dlv));
        Allure.addAttachment("Скриншот: После того как все выполнили", new ByteArrayInputStream(set_Providers_After));
        t.assertAll();


    }
}
