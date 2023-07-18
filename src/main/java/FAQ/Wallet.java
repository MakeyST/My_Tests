/*
package FAQ;

import Utils.LogUtils;
import Utils.ScreenshotHelper;
import Utils.WaitUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static FAQ.ConfigFileFAQ.LocatorsFAQ.GetXFAQ;
import static Utils.ScreenshotHelper.compareScreenshots;
import static io.qameta.allure.Allure.step;
import static ru.yandex.qatools.ashot.util.ImageTool.toByteArray;

public class Wallet {
    private static final String CHECK_QUESTIONS_XPATH = "//button[@class=\"spoiler-block__title\"]";
    private static final String BUTTON_WALLET_XPATH = "//button[@class=\"nav-tabs__btn\"]";
    private static final String DIV_SPOILER_BLOCK = "//div[@class=\"spoiler-block__content content\"]";

    private WebDriver driver;
    private Actions actions;
    private WaitUtils waitUtils;

    public Wallet(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    private void scrollToAndClick(WebElement element) {
        actions.moveToElement(element).click().perform();
        waitUtils.waitForPageToLoad();
    }

    private void attachScreenshot(String name, byte[] screenshot) {
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }

    private byte[] takeElementScreenshot(WebElement element) throws IOException {
        Screenshot screenshot = new AShot().takeScreenshot(driver, element);
        return toByteArray(screenshot.getImage());
    }

    public void genWallet(WebDriver driver) throws IOException {
        this.driver.get(GetXFAQ);
        SoftAssert t = new SoftAssert();

        try {
            new WebDriverWait(this.driver, Duration.ofSeconds(20)).until((ExpectedCondition<Boolean>) wd -> {
                try {
                    System.out.println("Попытка выполнить скрипт...");
                    boolean result = wd != null && ((JavascriptExecutor) wd).executeScript("return !!window.jQuery && window.jQuery.active == 0").equals(true);
                    System.out.println("Результат выполнения скрипта: " + result);
                    return result;
                } catch (Exception e) {
                    System.err.println("Ошибка при выполнении скрипта: " + e.getMessage());
                    return false;
                }
            });
        } catch (TimeoutException e) {
            System.err.println("Время ожидания истекло: " + e.getMessage());
        }




        // Открыть "Кошелек"
        this.driver.findElement(By.xpath(BUTTON_WALLET_XPATH)).click();

        // До открытия FAQ
        byte[] screenshotAs = ScreenshotHelper.takeScreenshot(this.driver);

        // Как пополнить баланс?
        step("Как пополнить баланс?", Status.PASSED);
        WebElement myElement1 = this.driver.findElements(By.xpath(DIV_SPOILER_BLOCK)).get(0);
        Screenshot screenshot = new AShot().takeScreenshot(this.driver, myElement1);
        WebElement question1 = this.driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(0);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question1).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot1 = toByteArray(screenshot.getImage());
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question1).click().perform();

        */
/*//*
/ Как вывести средства?
        step("Как вывести средства?", Status.PASSED);
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


        //Я вывел деньги, но они не пришли
        step("Я вывел деньги, но они не пришли", Status.PASSED);
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


        //Существуют ли на сайте комиссии при пополнении и выводе?
        step("Существуют ли на сайте комиссии при пополнении и выводе?", Status.PASSED);
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

        //Что такое “Промокод”?
        step("Что такое Промокод?", Status.PASSED);
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

        //Как использовать промокод
        step("Как использовать промокод", Status.PASSED);
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

        //Что такое “Бесплатные монеты”?
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

        //Где найти свой ID
        step("Где найти свой ID", Status.PASSED);
        WebElement question8 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(7);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question8).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot8 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question8).click().perform();

        //Привязка номера телефона
        step("Привязка номера телефона", Status.PASSED);
        WebElement question9 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(8);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question9).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot9 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question9).click().perform();

        //Не пришли деньги на депозит?
        step("Не пришли деньги на депозит?", Status.PASSED);
        WebElement question10 = driver.findElements(By.xpath(CHECK_QUESTIONS_XPATH)).get(9);
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question10).click().perform();
        //Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();
        //Делаем скриншот через aShot
        byte[] screenshot10 = takeFullPageScreenshot(driver);
        //Скролим, пока элемент не появится в видимой области для клика
        actions.moveToElement(question10).click().perform();*//*


        // Аллюр Аттач
        LogEntries browserLogs = this.driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: ДО", new ByteArrayInputStream(screenshotAs));
        Allure.addAttachment("Скриншот элемента: Как пополнить баланс?", new ByteArrayInputStream(screenshot1));
       */
/* Allure.addAttachment("Скриншот: Как вывести средства?", new ByteArrayInputStream(screenshot2));
        Allure.addAttachment("Скриншот: Я вывел деньги, но они не пришли", new ByteArrayInputStream(screenshot3));
        Allure.addAttachment("Скриншот: Существуют ли на сайте комиссии при пополнении и выводе?", new ByteArrayInputStream(screenshot4));
        Allure.addAttachment("Скриншот: Что такое Промокод?", new ByteArrayInputStream(screenshot5));
        Allure.addAttachment("Скриншот: Как использовать промокод", new ByteArrayInputStream(screenshot6));
        Allure.addAttachment("Скриншот: Что такое Бесплатные монеты?", new ByteArrayInputStream(screenshot7));
        Allure.addAttachment("Скриншот: Где найти свой ID", new ByteArrayInputStream(screenshot8));
        Allure.addAttachment("Скриншот: Привязка номера телефона", new ByteArrayInputStream(screenshot9));
        Allure.addAttachment("Скриншот: Не пришли деньги на депозит?", new ByteArrayInputStream(screenshot10));*//*


        // Сравнение скриншотов
        compareScreenshots("Ожидаемый результат: Как пополнить баланс?", screenshot1, "expected_screenshot1.png");
        */
/*compareScreenshots("Ожидаемый результат: Как вывести средства?", screenshot2, "expected_screenshot2.png");
        compareScreenshots("Ожидаемый результат: Я вывел деньги, но они не пришли", screenshot3, "expected_screenshot3.png");
        compareScreenshots("Ожидаемый результат: Существуют ли на сайте комиссии при пополнении и выводе?", screenshot4, "expected_screenshot4.png");
        compareScreenshots("Ожидаемый результат: Что такое Промокод?", screenshot5, "expected_screenshot5.png");
        compareScreenshots("Ожидаемый результат: Как использовать промокод", screenshot6, "expected_screenshot6.png");
        compareScreenshots("Ожидаемый результат: Что такое Бесплатные монеты?", screenshot7, "expected_screenshot7.png");
        compareScreenshots("Ожидаемый результат: Где найти свой ID", screenshot8, "expected_screenshot8.png");
        compareScreenshots("Ожидаемый результат: Привязка номера телефона", screenshot9, "expected_screenshot9.png");
        compareScreenshots("Ожидаемый результат: Не пришли деньги на депозит?", screenshot10, "expected_screenshot10.png");*//*


        // Сбор данных по проверкам
        step("Сбор данных по проверкам", Status.PASSED);
        t.assertAll();
    }
}
*/
