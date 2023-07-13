package QuickGames;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static QuickGames.ConfigFileQuickGames.LocatorsQuickGames.GetXMINERTest;

@Link(name = "Test", type = "https://ppgetx.click/games/miner")
@Link(name = "Prod", type = "https://get22.cfd/games/miner")
@Owner("Makeenkov Igor")
public class MinerQuick {
    String PlayMiner = "//button[@class=\"btn btn_full btn_long btn_can-cancel\"]";
    String OpenaCell = "//div[@class=\"miner_cell_container opened-cell\"]";
    String WinMiner = "//button[@class=\"btn btn_full btn_long btn_can-cancel btn-pick-up\"]";
    @Description("Быстрая игра Минер")
    public void minerQuick (WebDriver driver) throws InterruptedException {
        driver.get(GetXMINERTest);
        Thread.sleep(600);

        driver.findElements(By.xpath(PlayMiner)).get(0).click();
        Thread.sleep(600);

        driver.findElements(By.xpath(OpenaCell)).get(22).click();
        Thread.sleep(600);

        driver.findElements(By.xpath(WinMiner)).get(0).click();
        Thread.sleep(600);

    }
}
