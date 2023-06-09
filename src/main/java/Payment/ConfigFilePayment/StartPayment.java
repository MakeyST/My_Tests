package Payment.ConfigFilePayment;

import Payment.PaymentSberbank;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@Owner("Makeenkov Igor")
public class StartPayment {
    @Step("Проверка попапа Сбербанка")
    @Test(alwaysRun = true, description = "Сбербанк")
    public void paySber (WebDriver driver){
        try {
            PaymentSberbank pay = new PaymentSberbank();
            pay.paymentSberbank(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}

}
