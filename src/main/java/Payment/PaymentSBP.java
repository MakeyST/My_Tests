package Payment;

import Utils.LogUtils;
import Utils.WaitUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static Payment.ConfigFilePayment.LocatorsPayment.TestPromoCode;
import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.GetXProfileTest;
import static io.qameta.allure.Allure.step;

public class PaymentSBP {
    String Wallet = "//button[@class=\"btn btn_shadow headline__balance-btn\"]";
    String PaymentMethods = "//button[@class=\"pay__option js-wives-hover js-ven-wives\"]";
    String DepositAmount = "//input[@class=\"field field-group__field amount\"]";
    String ButtonPromoCode = "//div[@class=\"giftbox\"]";
    String ButtonDeposit = "//button[@class=\"bit-feed__btn\"]";
    String InputPromoCode = "//input[@class=\"field field-group__field\"]";
    String Continue = "//button[@class=\"btn btn_big btn_shadow\"]";
    String ControlPayment = "//div[@class=\"transfer-timer__label\"]";
    public void paymentSBP (WebDriver driver) throws InterruptedException, IOException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));

        //Перейти на сайт
        step("Перейти на сайт", Status.PASSED);
        driver.get(GetXProfileTest);
        waitUtils.waitForPageToLoad();
        driver.navigate().refresh();
        waitUtils.waitForPageToLoad();

        //Проверки
        SoftAssert t = new SoftAssert();

        //Клик по кнопке: "Кошелек"
        step("Клик по кнопке: Кошелек", Status.PASSED);
        driver.findElement(By.xpath(Wallet)).click();
        waitUtils.waitForPageToLoad();

        //Выбор метода оплаты СБП
        step("Выбор метода оплаты СБП", Status.PASSED);
        driver.findElements(By.xpath(PaymentMethods)).get(1).click();
        waitUtils.waitForPageToLoad();

        //Ручной ввод суммы
        step("Ручной ввод суммы", Status.PASSED);
        driver.findElement(By.xpath(DepositAmount)).click();
        driver.findElement(By.xpath(DepositAmount)).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.xpath(DepositAmount)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(DepositAmount)).sendKeys("200");
        waitUtils.waitForPageToLoad();

        //Проверка ручного ввода суммы
        step("Проверка ручного ввода суммы", Status.PASSED);
        String TextDep200 =  driver.findElement(By.xpath(DepositAmount)).getAttribute("value");
        String ExpectedDep200 = "200";
        t.assertEquals(TextDep200, ExpectedDep200, "Проверка ручного ввода суммы 200руб ПРОВАЛЕНА!");
        t.assertNotNull(TextDep200);
        System.out.println("*Проверка ручного ввода суммы 200руб, ---> выполнено*");

        //Кнопка 200
        step("Кнопка 200", Status.PASSED);
        driver.findElements(By.xpath(ButtonDeposit)).get(0).click();
        waitUtils.waitForPageToLoad();
        //Проверка кнопки 200
        step("Проверка кнопки 200руб", Status.PASSED);
        String TextClick200 =  driver.findElement(By.xpath(DepositAmount)).getAttribute("value");
        String ExpectedClick200 = "200";
        t.assertEquals(TextClick200, ExpectedClick200, "Проверка кнопки 200руб ПРОВАЛЕНА!");
        t.assertNotNull(TextClick200);
        System.out.println("*Проверка кнопки 200руб, ---> выполнено*");

        //Кнопка 500
        step("Кнопка 500", Status.PASSED);
        driver.findElements(By.xpath(ButtonDeposit)).get(1).click();
        waitUtils.waitForPageToLoad();
        //Проверка кнопки 500
        step("Проверка кнопки 500", Status.PASSED);
        String TextClick500 =  driver.findElement(By.xpath(DepositAmount)).getAttribute("value");
        String ExpectedClick500 = "500";
        t.assertEquals(TextClick500, ExpectedClick500, "Проверка кнопки 500руб ПРОВАЛЕНА!");
        t.assertNotNull(TextClick500);
        System.out.println("*Проверка кнопки 500руб, ---> выполнено*");

        //Кнопка 1000
        step("Кнопка 1000", Status.PASSED);
        driver.findElements(By.xpath(ButtonDeposit)).get(2).click();
        waitUtils.waitForPageToLoad();
        //Проверка кнопки 1000
        step("Проверка кнопки 1000", Status.PASSED);
        String TextClick1000 =  driver.findElement(By.xpath(DepositAmount)).getAttribute("value");
        String ExpectedClick1000 = "1000";
        t.assertEquals(TextClick1000, ExpectedClick1000, "Проверка кнопки 1000руб ПРОВАЛЕНА!");
        t.assertNotNull(TextClick1000);
        System.out.println("*Проверка кнопки 1000руб, ---> выполнено*");

        //Кнопка 2500
        step("Кнопка 2500", Status.PASSED);
        driver.findElements(By.xpath(ButtonDeposit)).get(3).click();
        waitUtils.waitForPageToLoad();
        //Проверка кнопки 2500
        step("Проверка кнопки 2500", Status.PASSED);
        String TextClick2500 =  driver.findElement(By.xpath(DepositAmount)).getAttribute("value");
        String ExpectedClick2500 = "2500";
        t.assertEquals(TextClick2500, ExpectedClick2500, "Проверка кнопки 2500руб ПРОВАЛЕНА!");
        t.assertNotNull(TextClick2500);
        System.out.println("*Проверка кнопки 2500руб, ---> выполнено*");

        //Кнопка 5000
        step("Кнопка 5000", Status.PASSED);
        driver.findElements(By.xpath(ButtonDeposit)).get(4).click();
        waitUtils.waitForPageToLoad();
        //Проверка кнопки 5000
        step("Проверка кнопки 5000", Status.PASSED);
        String TextClick5000 =  driver.findElement(By.xpath(DepositAmount)).getAttribute("value");
        String ExpectedClick5000 = "5000";
        t.assertEquals(TextClick5000, ExpectedClick5000, "Проверка кнопки 5000руб ПРОВАЛЕНА!");
        t.assertNotNull(TextClick5000);
        System.out.println("*Проверка кнопки 5000руб, ---> выполнено*");

        //Кнопка 10 000
        step("Кнопка 10 000", Status.PASSED);
        driver.findElements(By.xpath(ButtonDeposit)).get(5).click();
        waitUtils.waitForPageToLoad();
        //Проверка кнопки 10 000
        step("Проверка кнопки 10 000", Status.PASSED);
        String TextClick10000 =  driver.findElement(By.xpath(DepositAmount)).getAttribute("value");
        String ExpectedClick10000 = "10000";
        t.assertEquals(TextClick10000, ExpectedClick10000, "Проверка кнопки 10 000руб ПРОВАЛЕНА!");
        t.assertNotNull(TextClick10000);
        System.out.println("*Проверка кнопки 10 000руб, ---> выполнено*");

        //Кнопка 20 000
        step("Кнопка 20 000", Status.PASSED);
        driver.findElements(By.xpath(ButtonDeposit)).get(6).click();
        waitUtils.waitForPageToLoad();
        //Проверка кнопки 20 000
        step("Проверка кнопки 20000руб", Status.PASSED);
        String TextClick20000 =  driver.findElement(By.xpath(DepositAmount)).getAttribute("value");
        String ExpectedClick20000 = "20000";
        t.assertEquals(TextClick20000, ExpectedClick20000, "Проверка кнопки 20 000руб ПРОВАЛЕНА!");
        t.assertNotNull(TextClick20000);
        System.out.println("*Проверка кнопки 20 000руб, ---> выполнено*");

        //Нажать кнопку: "У меня есть промокод"
        step("Нажать кнопку: У меня есть промокод", Status.PASSED);
        driver.findElement(By.xpath(ButtonPromoCode)).click();
        waitUtils.waitForPageToLoad();

        //Ввод промокода (Test работоспособности поля)
        step("Ввод промокода (Test работоспособности поля)", Status.PASSED);
        driver.findElement(By.xpath(InputPromoCode)).sendKeys(TestPromoCode);
        waitUtils.waitForPageToLoad();

        //Проверка поля промокод
        step("Проверка поля промокод", Status.PASSED);
        String TextPromoCode =  driver.findElement(By.xpath(InputPromoCode)).getAttribute("value");
        String ExpectedPromoCode = "PROMOCODETEST";
        t.assertEquals(TextPromoCode, ExpectedPromoCode, "Проверка поля Промокод, ПРОВАЛЕНА!");
        t.assertNotNull(TextPromoCode);
        System.out.println("*Проверка поля Промокод, ---> выполнено*");

        //Нажать кнопку: "Продолжить"
        step("Нажать кнопку: Продолжить", Status.PASSED);
        driver.findElement(By.xpath(Continue)).click();
        waitUtils.waitForPageToLoad();

        //Скриншот
        step("Скриншот", Status.PASSED);
        waitUtils.waitForPageToLoad();
        byte[] PaymentSBP = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Проверка ожидания платежа
        step("Проверка ожидания платежа", Status.PASSED);
        String TextControlPayment =  driver.findElement(By.xpath(ControlPayment)).getText();
        String ExpectedControlPayment = "МЫ ОЖИДАЕМ ВАШ ПЛАТЕЖ";
        t.assertEquals(TextControlPayment, ExpectedControlPayment, "Проверка попапа оплаты сбера, ПРОВАЛЕНА!");
        t.assertNotNull(TextControlPayment);
        System.out.println("*Проверка попапа оплаты сбера, ---> выполнено*");

        //Аллюр Аттач
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: Страницы оплаты СБП", new ByteArrayInputStream(PaymentSBP));

        //Сбор данных по проверкам
        step("Сбор данных по проверкам", Status.PASSED);
        t.assertAll();
    }
}
