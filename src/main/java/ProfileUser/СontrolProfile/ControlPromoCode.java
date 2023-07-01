package ProfileUser.СontrolProfile;

import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import ru.yandex.qatools.allure.annotations.Description;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

import static ProfileUser.ConfigFileProfile.LocatorsProfleUser.GetXProfileHistory;

@Owner("Makeenkov Igor")
@Description("Проверка, что промокод успешно активирован")
public class ControlPromoCode {
    String Bonuses = "//button[@class=\"button-group__link\"]";

    public void controlPromoCode (WebDriver driver) throws InterruptedException, IOException {

        driver.get(GetXProfileHistory);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElements(By.xpath(Bonuses)).get(2).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Thread.sleep(3000);

        //После
        byte[] controlPromocodeScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.attachment("Промокоды", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: Успешно примененного промокода", new ByteArrayInputStream(controlPromocodeScreen));

    }
}
