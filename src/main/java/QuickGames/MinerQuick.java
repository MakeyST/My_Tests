package QuickGames;

import Utils.WaitUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static QuickGames.ConfigFileQuickGames.LocatorsQuickGames.GetXMINERTest;
@Owner("Makeenkov Igor")
public class MinerQuick {
    String PlayMiner = "//button[@class=\"btn btn_full btn_long btn_can-cancel\"]";
    String OpenaCell = "//div[@class=\"miner_cell_container opened-cell\"]";
    String WinMiner = "//button[@class=\"btn btn_full btn_long btn_can-cancel btn-pick-up\"]";
    @Description("Быстрая игра Минер")
    public void minerQuick (WebDriver driver) throws InterruptedException {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        driver.get(GetXMINERTest);
        waitUtils.waitForPageToLoad();

        driver.findElements(By.xpath(PlayMiner)).get(0).click();
        waitUtils.waitForPageToLoad();

        driver.findElements(By.xpath(OpenaCell)).get(22).click();
        waitUtils.waitForPageToLoad();

        driver.findElements(By.xpath(WinMiner)).get(0).click();
        waitUtils.waitForPageToLoad();

    }
}
