package Games.ConfigFileGames;

import Games.GameMiner;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
@Owner("Makeenkov Igor")
public class StartGames {
    @Test(alwaysRun = true, description = "Играем в минера + проверка")
    public void miner(WebDriver driver){
        try {
            GameMiner GM = new GameMiner();
            GM.gameminer(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}
}
