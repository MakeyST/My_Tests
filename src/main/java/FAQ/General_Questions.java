package FAQ;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static FAQ.ConfigFileFAQ.LocatorsFAQ.GetXFAQ;
import static io.qameta.allure.Allure.step;

public class General_Questions {
    String CheckQuestions = "//button[@class=\"spoiler-block__title\"]";
    String BlockContentCheck = "//div[@class=\"spoiler-block__content content\"]";
    public void general_Questions (WebDriver driver) throws InterruptedException, IOException {
        driver.get(GetXFAQ);
        SoftAssert t = new SoftAssert();
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        //До открытия FAQ
        byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);


        //Что это за сайт?
        step("Что это за сайт?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(0).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,300)");
        byte[] screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-300)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(0).click();


        //Как здесь играть?
        step("Как здесь играть?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(1).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,300)");
        byte[] screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-300)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(1).click();

        //Могу ли я регистрировать несколько аккаунтов?
        step("Могу ли я регистрировать несколько аккаунтов?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(2).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,300)");
        byte[] screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-300)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(2).click();

        //Мошенничество
        step("Мошенничество", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(3).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,300)");
        byte[] screenshot4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-300)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(3).click();

        //Как работают наши игры
        step("Как работают наши игры", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(4).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,300)");
        byte[] screenshot5 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-300)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(4).click();

        //Нарушение правил и блокировака аккаунта
        step("Нарушение правил и блокировака аккаунта", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(5).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,400)");
        byte[] screenshot6 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-400)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(5).click();

        //Верификация
        step("Верификация", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(6).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,580)");
        //Уменьшаем масштаб страницы
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom = '80%';");
        byte[] screenshot7 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-580)");
        //Возврат в исходное состояние
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom = '100%';");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(6).click();


        //Аллюр Аттач
        Allure.attachment("Логи", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
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
