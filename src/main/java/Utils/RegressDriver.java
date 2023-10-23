package Utils;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static Utils.LocatorsDriver.Test;

@Owner("Makeenkov Igor")
public class RegressDriver {

    private static ChromeDriver _regressDriver;

    @Description("Использование драйвера для запуска страницы")
    public static ChromeDriver getDriver(ChromeOptions options) {
        if (_regressDriver == null) {
            _regressDriver = new ChromeDriver(options);
        }
        return _regressDriver;
    }
}



