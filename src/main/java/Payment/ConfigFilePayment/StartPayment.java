package Payment.ConfigFilePayment;

import Payment.*;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@Owner("Makeenkov Igor")
public class StartPayment {
    @Step("Проверка Сбербанка")
    @Test(alwaysRun = true, description = "Сбербанк")
    public void paySber (WebDriver driver){
        try {
            PaymentSberbank pay = new PaymentSberbank();
            pay.paymentSberbank(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}

    @Step("Проверка Криптовалюта")
    @Test(alwaysRun = true, description = "Криптовалюта")
    public void payCrypto (WebDriver driver){
        try {
            PaymentCrypto pay = new PaymentCrypto();
            pay.paymentCrypto(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}

    @Step("Проверка QIWI")
    @Test(alwaysRun = true, description = "QIWI")
    public void payQIWI (WebDriver driver){
        try {
            PaymentQIWI pay = new PaymentQIWI();
            pay.paymentQIWI(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}

    @Step("Проверка SBP")
    @Test(alwaysRun = true, description = "СБП")
    public void paySBP (WebDriver driver){
        try {
            PaymentSBP pay = new PaymentSBP();
            pay.paymentSBP(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}

    @Step("Проверка Yoomoney")
    @Test(alwaysRun = true, description = "Yoomoney")
    public void payYoomoney (WebDriver driver){
        try {
            PaymentYoomoney pay = new PaymentYoomoney();
            pay.paymentYoomoney(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}
    }





}
