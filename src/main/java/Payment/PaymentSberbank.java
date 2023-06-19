package Payment;

import io.qameta.allure.Allure;
import io.qameta.allure.Link;
import io.qameta.allure.model.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.qameta.allure.Allure.step;
@Link(name = "Test", type = "https://s01getx.click/profile")
@Link(name = "Prod", type = "https://get22.cfd/profile")
public class PaymentSberbank {
    String GetXProfileTest = "https://s01getx.click/";
    String GetXProfilePROD = "https://get22.cfd/";
    String Wallet = "//button[@class=\"btn btn_shadow headline__balance-btn\"]";
    String PaymentMethods = "//button[@class=\"pay__option js-wives-hover js-ven-wives\"]";
    String ClickMinus = "//button[@class=\"number-field__btn number-field__btn_minus\"]";
    String ClickPlus = "//button[@class=\"number-field__btn number-field__btn_plus\"]";
    String InputDepositAmount = "//input[@class=\"number-field__value number-field__value_bid amount\"]";
    String PromoCode = "//input[@class=\"field field-group__field\"]";
    String TestPromoCode = "123TestTest123";
    String Continue = "//button[@class=\"btn btn_big btn_shadow\"]";
    String ControlPayment = "//div[@class=\"transfer-timer__label\"]";

    public void paymentSberbank (WebDriver driver) throws InterruptedException, IOException {

        //Аллюр Аттач
        Allure.attachment("Сбербанк отчет", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));

        //Перейти на сайт
        step("Перейти на сайт", Status.PASSED);
        driver.get(GetXProfileTest);
        Thread.sleep(1000);

        //Проверки
        SoftAssert t = new SoftAssert();

        //Клик по кнопке: "Кошелек"
        step("Клик по кнопке: Кошелек", Status.PASSED);
        driver.findElement(By.xpath(Wallet)).click();
        Thread.sleep(700);

        //Выбор метода оплаты Сбербанк
        step("Выбор метода оплаты Сбербанк", Status.PASSED);
        driver.findElements(By.xpath(PaymentMethods)).get(0).click();
        Thread.sleep(300);

        //Выбор суммы: Кнопка +
        step("Выбор суммы: Кнопка +", Status.PASSED);
        driver.findElement(By.xpath(ClickPlus)).click();
        Thread.sleep(200);

        //Проверка суммы +
        step("Проверка суммы +", Status.PASSED);
        String TextClickPlus =  driver.findElement(By.xpath(InputDepositAmount)).getAttribute("value");
        String ExpectedClickPlus = "110";
        t.assertEquals(TextClickPlus, ExpectedClickPlus, "Проверка поля Cуммы +1руб ПРОВАЛЕНА!");
        t.assertNotNull(TextClickPlus);
        System.out.println("*Проверка поля Cуммы +1руб, ---> выполнено*");

        //Выбор суммы: Кнопка -
        step("Выбор суммы: Кнопка -", Status.PASSED);
        driver.findElement(By.xpath(ClickMinus)).click();
        Thread.sleep(200);

        //Проверка суммы -
        step("Проверка суммы -", Status.PASSED);
        String TextClickMinus =  driver.findElement(By.xpath(InputDepositAmount)).getAttribute("value");
        String ExpectedClickMinus = "100";
        t.assertEquals(TextClickMinus, ExpectedClickMinus, "Проверка поля Cуммы -1руб ПРОВАЛЕНА!");
        t.assertNotNull(TextClickMinus);
        System.out.println("*Проверка поля Cуммы -1руб, ---> выполнено*");

        //Выбор суммы ручной
        step("Выбор суммы ручной", Status.PASSED);
        driver.findElement(By.xpath(InputDepositAmount)).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.xpath(InputDepositAmount)).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(InputDepositAmount)).sendKeys("150");
        Thread.sleep(1000);

        //Проверка ручного ввода суммы
        step("Проверка ручного ввода суммы", Status.PASSED);
        String TextDepositAmount =  driver.findElement(By.xpath(InputDepositAmount)).getAttribute("value");
        String ExpectedDepositAmount = "150";
        t.assertEquals(TextDepositAmount, ExpectedDepositAmount, "Проверка поля Cуммы 150руб ПРОВАЛЕНА!");
        t.assertNotNull(TextDepositAmount);
        System.out.println("*Проверка поля Cуммы 150руб, ---> выполнено*");

        //Ввод промокода (Test работоспособности поля)
        step("Ввод промокода (Test работоспособности поля)", Status.PASSED);
        driver.findElement(By.xpath(PromoCode)).sendKeys(TestPromoCode);
        Thread.sleep(150);

        //Проверка поля промокод
        step("Проверка поля промокод", Status.PASSED);
        String TextPromoCode =  driver.findElement(By.xpath(PromoCode)).getAttribute("value");
        String ExpectedPromoCode = "123TestTest123";
        t.assertEquals(TextPromoCode, ExpectedPromoCode, "Проверка поля Промокод, ПРОВАЛЕНА!");
        t.assertNotNull(TextPromoCode);
        System.out.println("*Проверка поля Промокод, ---> выполнено*");

        //Удаление промокода
        step("Удаление промокода", Status.PASSED);
        Thread.sleep(150);
        driver.findElement(By.xpath(PromoCode)).sendKeys(Keys.CONTROL,"a");
        driver.findElement(By.xpath(PromoCode)).sendKeys(Keys.DELETE);
        Thread.sleep(150);

        //Проверка, удаления промокода
        step("Проверка, удаления промокода", Status.PASSED);
        String TextPromoCodeDelete =  driver.findElement(By.xpath(PromoCode)).getAttribute("value");
        String ExpectedPromoCodeDelete = "";
        t.assertEquals(TextPromoCodeDelete, ExpectedPromoCodeDelete, "Проверка поля Промокод (удаление), ПРОВАЛЕНА!");
        t.assertNotNull(TextPromoCodeDelete);
        System.out.println("*Проверка поля Промокод(удаление), ---> выполнено*");

        //Жмем "Оплатить"
        step("Жмем Оплатить", Status.PASSED);
        driver.findElement(By.xpath(Continue)).click();
        Thread.sleep(1000);

        //Скриншот
        step("Скриншот", Status.PASSED);
        Thread.sleep(100);
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));

        //Проверка ожидания платежа
        step("Проверка ожидания платежа", Status.PASSED);
        String TextControlPayment =  driver.findElement(By.xpath(ControlPayment)).getAttribute("value");
        String ExpectedControlPayment = "Мы ожидаем ваш платеж";
        t.assertEquals(TextControlPayment, ExpectedControlPayment, "Проверка попапа оплаты сбера, ПРОВАЛЕНА!");
        t.assertNotNull(TextControlPayment);
        System.out.println("*Проверка попапа оплаты сбера, ---> выполнено*");

        //Сбор данных по проверкам
        step("Сбор данных по проверкам", Status.PASSED);
        t.assertAll();



    }
}
