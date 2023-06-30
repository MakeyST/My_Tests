package FAQ;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static io.qameta.allure.Allure.step;

public class General_Questions {
    String GetXFAQ = "https://ppgetx.click/faq";
    String CheckQuestions = "//button[@class=\"spoiler-block__title\"]";
    public void general_Questions (WebDriver driver) throws InterruptedException, IOException {

        driver.get(GetXFAQ);
        new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        SoftAssert t = new SoftAssert();

        //До открытия FAQ
        byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Что это за сайт?
        step("Что это за сайт?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(0).click();
        Thread.sleep(200);
        byte[] screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        driver.findElements(By.xpath(CheckQuestions)).get(0).click();


        //Как здесь играть?
        step("Как здесь играть?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(1).click();
        Thread.sleep(200);
        byte[] screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        driver.findElements(By.xpath(CheckQuestions)).get(1).click();

        //Могу ли я регистрировать несколько аккаунтов?
        step("Могу ли я регистрировать несколько аккаунтов?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(2).click();
        Thread.sleep(200);
        byte[] screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        driver.findElements(By.xpath(CheckQuestions)).get(2).click();

        //Мошенничество
        step("Мошенничество", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(3).click();
        Thread.sleep(200);
        byte[] screenshot4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        driver.findElements(By.xpath(CheckQuestions)).get(3).click();

        //Как работают наши игры
        step("Как работают наши игры", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(4).click();
        Thread.sleep(200);
        byte[] screenshot5 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        driver.findElements(By.xpath(CheckQuestions)).get(4).click();

        //Нарушение правил и блокировака аккаунта
        step("Нарушение правил и блокировака аккаунта", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(5).click();
        Thread.sleep(200);
        byte[] screenshot6 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        driver.findElements(By.xpath(CheckQuestions)).get(5).click();

        //Верификация
        step("Верификация", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(6).click();
        Thread.sleep(200);
        byte[] screenshot7 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        driver.findElements(By.xpath(CheckQuestions)).get(6).click();

        //Аллюр Аттач
        Allure.attachment("Общие вопросы FAQ", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: ДО", new ByteArrayInputStream(screenshotAs));
        Allure.addAttachment("Скриншот: Что это за сайт?", new ByteArrayInputStream(screenshot1));
        Allure.addAttachment("Скриншот: Как здесь играть?", new ByteArrayInputStream(screenshot2));
        Allure.addAttachment("Скриншот: Могу ли я регистрировать несколько аккаунтов?", new ByteArrayInputStream(screenshot3));
        Allure.addAttachment("Скриншот: Мошенничество", new ByteArrayInputStream(screenshot4));
        Allure.addAttachment("Скриншот: Как работают наши игры", new ByteArrayInputStream(screenshot5));
        Allure.addAttachment("Скриншот: Нарушение правил и блокировака аккаунта", new ByteArrayInputStream(screenshot6));
        Allure.addAttachment("Скриншот: Верификация", new ByteArrayInputStream(screenshot7));

        //Сбор данных по проверкам
        step("Сбор данных по проверкам", Status.PASSED);
        t.assertAll();

    }
}
