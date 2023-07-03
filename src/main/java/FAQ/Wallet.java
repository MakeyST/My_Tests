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

public class Wallet {
    String CheckQuestions = "//button[@class=\"spoiler-block__title\"]";
    String ButtonWallet = "//button[@class=\"nav-tabs__btn\"]";
    public void genWallet(WebDriver driver) throws InterruptedException, IOException {
        driver.get(GetXFAQ);
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        SoftAssert t = new SoftAssert();

        //Открыть "Кошелек"
        driver.findElement(By.xpath(ButtonWallet)).click();

        //До открытия FAQ
        byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Как пополнить баланс?
        step("Как пополнить баланс?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(0).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,300)");
        byte[] screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-300)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(0).click();


        //Как вывести средства?
        step("Как вывести средства?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(1).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,420)");
        byte[] screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-420)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(1).click();

        //Я вывел деньги, но они не пришли
        step("Я вывел деньги, но они не пришли", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(2).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,250)");
        byte[] screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-250)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(2).click();

        //Существуют ли на сайте комиссии при пополнении и выводе?
        step("Существуют ли на сайте комиссии при пополнении и выводе?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(3).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,250)");
        byte[] screenshot4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-250)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(3).click();

        //Что такое “Промокод”?
        step("Что такое Промокод?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(4).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,250)");
        byte[] screenshot5 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-250)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(4).click();

        //Как использовать промокод
        step("Как использовать промокод", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(5).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,400)");
        byte[] screenshot6 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-400)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(5).click();

        //Что такое “Бесплатные монеты”?
        step("Что такое Бесплатные монеты?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(6).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,250)");
        byte[] screenshot7 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл вверх
        js.executeScript("window.scrollBy(0,-250)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(6).click();

        //Где найти свой ID
        step("Где найти свой ID", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(7).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,420)");
        byte[] screenshot8 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл
        js.executeScript("window.scrollBy(0,-420)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(7).click();

        //Привязка номера телефона
        step("Привязка номера телефона", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(8).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,850)");
        byte[] screenshot9 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл
        js.executeScript("window.scrollBy(0,-850)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(8).click();

        //Не пришли деньги на депозит?
        step("Не пришли деньги на депозит?", Status.PASSED);
        driver.findElements(By.xpath(CheckQuestions)).get(9).click();
        Thread.sleep(200);
        //Скролл вниз
        js.executeScript("window.scrollBy(0,350)");
        byte[] screenshot10 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        //Скролл
        js.executeScript("window.scrollBy(0,-350)");
        //Закрываем активный блок
        driver.findElements(By.xpath(CheckQuestions)).get(9).click();

        //Аллюр Аттач
        Allure.attachment("Логи", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: ДО", new ByteArrayInputStream(screenshotAs));
        Allure.addAttachment("Скриншот: Как пополнить баланс?", new ByteArrayInputStream(screenshot1));
        Allure.addAttachment("Скриншот: Как вывести средства?", new ByteArrayInputStream(screenshot2));
        Allure.addAttachment("Скриншот: Я вывел деньги, но они не пришли", new ByteArrayInputStream(screenshot3));
        Allure.addAttachment("Скриншот: Существуют ли на сайте комиссии при пополнении и выводе?", new ByteArrayInputStream(screenshot4));
        Allure.addAttachment("Скриншот: Что такое Промокод?", new ByteArrayInputStream(screenshot5));
        Allure.addAttachment("Скриншот: Как использовать промокод", new ByteArrayInputStream(screenshot6));
        Allure.addAttachment("Скриншот: Что такое Бесплатные монеты?", new ByteArrayInputStream(screenshot7));
        Allure.addAttachment("Скриншот: Где найти свой ID", new ByteArrayInputStream(screenshot8));
        Allure.addAttachment("Скриншот: Привязка номера телефона", new ByteArrayInputStream(screenshot9));
        Allure.addAttachment("Скриншот: Не пришли деньги на депозит?", new ByteArrayInputStream(screenshot10));

        //Сбор данных по проверкам
        step("Сбор данных по проверкам", Status.PASSED);
        t.assertAll();
    }
}
