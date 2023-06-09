package Utils.ConfigFileUtils;

import Utils.Authorization;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
@Owner("Makeenkov Igor")
public class StartUtils {
    @Test(alwaysRun = true)
    public void Authorization(WebDriver driver) {
        try {
            Authorization authorization = new Authorization();
            authorization.authorization(driver);
        } catch (InterruptedException e) {e.printStackTrace();}
    }
}
