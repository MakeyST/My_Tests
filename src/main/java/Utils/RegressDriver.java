package Utils;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Description;

@Owner("Makeenkov Igor")
@Description("Использования драйвера для запуска страницы")
@Link(name = "Test", type = "https://ppgetx.click/")
@Link(name = "Prod", type = "https://get22.cfd/")
public class RegressDriver {

    private static ChromeDriver _regressDriver;
    public static ChromeDriver getDriver() {
        String PROD = "https://get22.cfd/";
        String Test = "https://ppgetx.click";
        if (RegressDriver._regressDriver == null) {
            RegressDriver._regressDriver = new ChromeDriver();
            _regressDriver.get(Test);
        }
        return RegressDriver._regressDriver;
    }
}