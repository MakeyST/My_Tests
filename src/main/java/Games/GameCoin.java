package Games;

import io.qameta.allure.model.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Games.ConfigFileGames.LocatorsGames.GETXCOIN;
import static io.qameta.allure.Allure.step;

public class GameCoin {
    String BID = "//input[@id=\"BidMobile\"]";
    String Control_BID_button = "//button[@class=\"coinflip-control__bid-field__control__btn\"]";
    String EAGLE = "//div[@class=\"bit-feed__cell bit-feed__eagle\"]";
    String TAILS = "//div[@class=\"bit-feed__cell bit-feed__tails\"]";
    String START = "//button[@class=\"btn btn_full btn_long btn_can-cancel\"]";
    public void gamecoin (WebDriver driver) throws InterruptedException, IOException {
        driver.get(GETXCOIN);
        Thread.sleep(1500);
        SoftAssert t = new SoftAssert();
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd_hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        //До
        FileUtils.copyFile(screenshot, new File("C:\\WorkScreen\\" + fileName));

        //Проверка панели BID
        //Клик на 1/2
        step("Проверка кнопки ½", Status.PASSED);
        driver.findElements(By.xpath(Control_BID_button)).get(0).click();
        Thread.sleep(700);
            String TextBID1 =  driver.findElements(By.xpath(Control_BID_button)).get(0).getText();
            String ExpectedBID1 = "½";
            t.assertEquals(TextBID1, ExpectedBID1, "Проверка кнопки ½, провалена!");
            t.assertNotNull(TextBID1);
        step("Проверка суммы ставки", Status.PASSED);
            String TextBID1Input = driver.findElement(By.id(BID)).getText();
            String ExpectedBID1Input = "1,00";
            t.assertEquals(TextBID1Input, ExpectedBID1Input, "Проверка поля 1,00, провалена!");
            t.assertNotNull(TextBID1Input);
        System.out.println("*Проверка поля 1,00, ---> выполнено*");

        //Клик на 2ХWallet
        step("Проверка кнопки 2x", Status.PASSED);
        driver.findElements(By.xpath(Control_BID_button)).get(1).click();
        Thread.sleep(700);
            String TextBID2x =  driver.findElements(By.xpath(Control_BID_button)).get(1).getText();
            String ExpectedBID2x = "2x";
            t.assertEquals(TextBID2x, ExpectedBID2x, "Проверка кнопки 2x, провалена!");
            t.assertNotNull(TextBID2x);
        step("Проверка суммы ставки", Status.PASSED);
            String TextBID2xInput = driver.findElement(By.id(BID)).getText();
            String ExpectedBID2xInput = "2,00";
            t.assertEquals(TextBID2xInput, ExpectedBID2xInput, "Проверка поля 2,00, провалена!");
            t.assertNotNull(TextBID2xInput);
        System.out.println("*Проверка поля 2,00, ---> выполнено*");

        //Клик на Min
        step("Проверка кнопки Min", Status.PASSED);
        driver.findElements(By.xpath(Control_BID_button)).get(2).click();
        Thread.sleep(700);
            String TextBIDMin =  driver.findElements(By.xpath(Control_BID_button)).get(2).getText();
            String ExpectedBIDMin = "Min";
            t.assertEquals(TextBIDMin, ExpectedBIDMin, "Проверка кнопки Min, провалена!");
            t.assertNotNull(TextBIDMin);
        step("Проверка суммы ставки", Status.PASSED);
            String TextBIDMinInput = driver.findElement(By.id(BID)).getText();
            String ExpectedBIDMinInput = "1,00";
            t.assertEquals(TextBIDMinInput, ExpectedBIDMinInput, "Проверка поля 1,00, провалена!");
            t.assertNotNull(TextBIDMinInput);
        System.out.println("*Проверка поля 1,00, ---> выполнено*");

        //Клик на Min
        step("Проверка кнопки Max", Status.PASSED);
        driver.findElements(By.xpath(Control_BID_button)).get(3).click();
        Thread.sleep(700);
            String TextBIDMax =  driver.findElements(By.xpath(Control_BID_button)).get(3).getText();
            String ExpectedBIDMax = "Max";
            t.assertEquals(TextBIDMax, ExpectedBIDMax, "Проверка кнопки Max, провалена!");
            t.assertNotNull(TextBIDMax);
        step("Проверка суммы ставки", Status.PASSED);
            String TextBIDMaxInput = driver.findElement(By.id(BID)).getText();
            String ExpectedBIDMaxInput = "1,00";
            t.assertEquals(TextBIDMaxInput, ExpectedBIDMaxInput, "Проверка поля 1,00, провалена!");
            t.assertNotNull(TextBIDMaxInput);
        System.out.println("*Проверка поля 1,00, ---> выполнено*");

        //Жмем кнопку Орел
        driver.findElement(By.xpath(EAGLE)).click();
        Thread.sleep(3000);

    }
}
