package QuickGames.ConfigFileQuickGames;

import QuickGames.MinerQuick;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@Owner("Makeenkov Igor")
public class StartQuickGames {
    @Test(alwaysRun = true, description = "Быстрые игры Минер")
    public void quickMiner (WebDriver driver){
        try {
            MinerQuick minerFast = new MinerQuick();
            minerFast.minerQuick(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}


}
