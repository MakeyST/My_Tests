package Utils;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.chrome.ChromeDriver;

import static Utils.LocatorsDriver.Test;

@Owner("Makeenkov Igor")
public class RegressDriver {

    private static ChromeDriver _regressDriver;
    @Description("Использования драйвера для запуска страницы")
    public static ChromeDriver getDriver() {
        if (RegressDriver._regressDriver == null) {
            RegressDriver._regressDriver = new ChromeDriver();
            _regressDriver.get(Test);
        }
        return RegressDriver._regressDriver;
    }
}