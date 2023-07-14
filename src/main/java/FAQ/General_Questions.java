package FAQ;

import Utils.LogUtils;
import Utils.WaitUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static FAQ.ConfigFileFAQ.LocatorsFAQ.GetXFAQ;
import static Utils.ScreenshotHelper.takeFullPageScreenshot;
import static io.qameta.allure.Allure.step;

public class General_Questions {
    private static final String CHECK_QUESTIONS_XPATH = "//button[@class=\"spoiler-block__title\"]";

    public void general_Questions (WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        driver.get(GetXFAQ);
        waitUtils.waitForPageToLoad();
        Actions actions = new Actions(driver);
        SoftAssert t = new SoftAssert();
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        //До открытия FAQ
        byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);


        //Что это за сайт?
        step("Что это за сайт?", Status.PASSED);
        WebElement question1 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(0);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question1).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot1 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question1).click().perform();


        //Как здесь играть?
        step("Как здесь играть?", Status.PASSED);
        WebElement question2 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(1);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question2).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot2 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question2).click().perform();

        //Могу ли я регистрировать несколько аккаунтов?
        step("Могу ли я регистрировать несколько аккаунтов?", Status.PASSED);
        WebElement question3 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(2);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question3).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot3 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question3).click().perform();

        //Мошенничество
        step("Мошенничество", Status.PASSED);
        WebElement question4 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(3);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question4).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot4 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question4).click().perform();

        //Как работают наши игры
        step("Как работают наши игры", Status.PASSED);
        WebElement question5 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(4);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question5).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot5 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question5).click().perform();

        //Нарушение правил и блокировака аккаунта
        step("Нарушение правил и блокировака аккаунта", Status.PASSED);
        WebElement question6 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(5);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question6).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot6 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question6).click().perform();

        //Верификация
        step("Верификация", Status.PASSED);
        step("Что такое Бесплатные монеты?", Status.PASSED);
        WebElement question7 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(6);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question7).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot7 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question7).click().perform();


        // Аллюр Аттач
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
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
