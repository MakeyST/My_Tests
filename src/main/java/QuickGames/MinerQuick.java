package QuickGames;

import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Description;

@Owner("Makeenkov Igor")
@Description("Быстрая игра Минер")
public class MinerQuick {
    String GetXMINERProd = "https://get22.cfd/games/miner";
    String GetXMINERTest = "https://s01getx.click/games/miner/";
    String PlayMiner = "//button[@class=\"btn btn_full btn_long btn_can-cancel\"]";
    String OpenaCell = "//div[@class=\"miner_cell_container opened-cell\"]";
    String WinMiner = "//button[@class=\"btn btn_full btn_long btn_can-cancel btn-pick-up\"]";
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
