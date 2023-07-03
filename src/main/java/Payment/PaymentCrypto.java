package Payment;

import io.qameta.allure.Allure;
import io.qameta.allure.Link;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static Payment.ConfigFilePayment.LocatorsPayment.WalletNumber;
import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.GetXProfileTest;
import static io.qameta.allure.Allure.step;

@Link(name = "Test", type = "https://ppgetx.click/")
@Link(name = "Prod", type = "https://get22.cfd/")
public class PaymentCrypto {
    String Wallet = "//button[@class=\"btn btn_shadow headline__balance-btn\"]";
    String PaymentMethods = "//button[@class=\"pay__option js-wives-hover js-ven-wives\"]";
    String Currency = "//div[@class=\"crypto-item\"]";
    String CryptoWallet = "//div[@class=\"crypto-wallet__text\"]";
    String ConvertBTC = "//input[@class=\"crypto-course__value\"]";
    String ConvertRUB = "//input[@class=\"crypto-course__value\"]";
    public void paymentCrypto (WebDriver driver) throws InterruptedException, IOException {
        //Перейти на сайт
        step("Перейти на сайт", Status.PASSED);
        driver.get(GetXProfileTest);
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);

        //Проверки
        SoftAssert t = new SoftAssert();

        //Клик по кнопке: "Кошелек"
        step("Клик по кнопке: Кошелек", Status.PASSED);
        driver.findElement(By.xpath(Wallet)).click();
        Thread.sleep(700);

        //Выбор метода оплаты Криптовалюта
        step("Выбор метода оплаты Криптовалюта", Status.PASSED);
        driver.findElements(By.xpath(PaymentMethods)).get(4).click();
        Thread.sleep(300);

        //Выбор BTC
        step("Выбор BTC", Status.PASSED);
        driver.findElements(By.xpath(Currency)).get(0).click();
        Thread.sleep(300);

        //Выбор сети btc
        step("Выбор сети btc", Status.PASSED);
        driver.findElements(By.xpath(Currency)).get(8).click();
        Thread.sleep(900);

        //Проверка адреса для депозита BTC
        step("Проверка адреса для депозита BTC", Status.PASSED);
        String TextWalletBTC =  driver.findElement(By.xpath(CryptoWallet)).getText();
        String ExpectedWalletBTC = WalletNumber;
        t.assertEquals(TextWalletBTC, ExpectedWalletBTC, "Проверка адреса для депозита BTC ПРОВАЛЕНА!");
        t.assertNotNull(TextWalletBTC);
        System.out.println("*Проверка адреса для депозита BTC, ---> выполнено*");

        //Конвертер
        //Ввод суммы крипты BTC
        step("Ввод суммы крипты BTC", Status.PASSED);
        driver.findElements(By.xpath(ConvertBTC)).get(0).sendKeys(Keys.CONTROL,"a");
        driver.findElements(By.xpath(ConvertBTC)).get(0).sendKeys(Keys.DELETE);
        driver.findElements(By.xpath(ConvertBTC)).get(0).sendKeys("1");

        //Проверка ввода количества BTC
        step("Проверка ввода количества BTC", Status.PASSED);
        String Text1BTC =  driver.findElements(By.xpath(ConvertBTC)).get(0).getAttribute("value");
        String Expected1BTC = "1";
        t.assertEquals(Text1BTC, Expected1BTC, "Проверка ввода количества BTC 1шт ПРОВАЛЕНА!");
        t.assertNotNull(Text1BTC);
        System.out.println("*Проверка ввода количества BTC 1шт, ---> выполнено*");

        //Проверка ввода количества RUB из BTC
        step("Проверка ввода количества RUB из BTC", Status.PASSED);
        String TextRUB =  driver.findElements(By.xpath(ConvertRUB)).get(0).getAttribute("value");
        String ExpectedRUB = ""; //Если null, то провален
        t.assertEquals(TextRUB, ExpectedRUB, "Проверка ввода количества RUB из BTC ПРОВАЛЕНА!");
        t.assertNotNull(TextRUB);
        System.out.println("*Проверка ввода количества RUB из BTC, ---> выполнено*");

        //Скриншот
        step("Скриншот", Status.PASSED);
        Thread.sleep(400);
        byte[] PaymentCrypto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Аллюр Аттач
        Allure.attachment("Логи", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: Страницы оплаты крипта", new ByteArrayInputStream(PaymentCrypto));

        //Сбор данных по проверкам
        step("Сбор данных по проверкам", Status.PASSED);
        t.assertAll();
    }

}
