package Games;

import io.qameta.allure.Allure;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Description;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static Games.ConfigFileGames.LocatorsGames.GetXMINER;
import static io.qameta.allure.Allure.step;
@Link(name = "Test", type = "https://ppgetx.click/")
@Link(name = "Prod", type = "https://get22.cfd/")
@Owner("Makeenkov Igor")
@Description("Игра Минер")
public class GameMiner {
    String PlayMiner = "//button[@class=\"btn btn_full btn_long btn_can-cancel\"]";
    String WinMinerPlay = "//button[@class=\"btn btn_full btn_long btn_can-cancel btn-pick-up\"]";
    String NumberofBombs = "//button[@class=\"bit-feed__btn\"]";
    String BOmbsNamberFrom5Before24 = "//button[@class=\"bit-feed__btn active\"]";
    String OpenaCell = "//div[@class=\"miner_cell_container opened-cell\"]";
    String AnotherInput = "//input[@class=\"number-field__value number-field__value_bid\"]";
    public void gameminer (WebDriver driver) throws InterruptedException, IOException {
        driver.get(GetXMINER);
        Thread.sleep(1500);
        SoftAssert t = new SoftAssert();

        //До
        byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        //Выставляем колличество бомб (кнопки) + проверяем, что выбралось нужное
        step("Выставляем колличество бомб (кнопки) + проверяем, что выбралось нужное", Status.PASSED);
        //5 бомб
        step("5 бомб", Status.PASSED);
        driver.findElements(By.xpath(NumberofBombs)).get(0).click(); //5 бомб клик
        Thread.sleep(800);
            String TextFiveBomb =  driver.findElement(By.xpath(BOmbsNamberFrom5Before24)).getText();
            String ExpectedFiveBomb = "5";
            t.assertEquals(TextFiveBomb, ExpectedFiveBomb, "Проверка кнопки №5, выбора кол-во бомб провалена!");
            t.assertNotNull(TextFiveBomb);
        System.out.println("*Проверка кнопки №5, выбора кол-во бомб, ---> выполнено*");

        //10 бомб
        step("10 бомб", Status.PASSED);
        driver.findElements(By.xpath(NumberofBombs)).get(1).click(); //10 бомб клик
        Thread.sleep(800);
            String TextTenBomb =  driver.findElement(By.xpath(BOmbsNamberFrom5Before24)).getText();
            String ExpectedTenBomb = "10";
            t.assertEquals(TextTenBomb, ExpectedTenBomb, "Проверка кнопки №10, выбора кол-во бомб провалена!");
            t.assertNotNull(TextTenBomb);
        System.out.println("*Проверка кнопки №10, выбора кол-во бомб, ---> выполнено*");

        //16 бомб
        step("16 бомб", Status.PASSED);
        driver.findElements(By.xpath(NumberofBombs)).get(2).click(); //16 бомб клик
        Thread.sleep(800);
            String TextSixteenBomb =  driver.findElement(By.xpath(BOmbsNamberFrom5Before24)).getText();
            String ExpectedSixteenBomb = "16";
            t.assertEquals(TextSixteenBomb, ExpectedSixteenBomb, "Проверка кнопки №16, выбора кол-во бомб провалена!");
            t.assertNotNull(TextSixteenBomb);
        System.out.println("*Проверка кнопки №16, выбора кол-во бомб, ---> выполнено*");

        //24 бомбы
        step("24 бомбы", Status.PASSED);
        driver.findElements(By.xpath(NumberofBombs)).get(3).click(); //24 бомб клик
        Thread.sleep(800);
            String TextTwentyFourBomb =  driver.findElement(By.xpath(BOmbsNamberFrom5Before24)).getText();
            String ExpectedTwentyFourBomb = "24";
            t.assertEquals(TextTwentyFourBomb, ExpectedTwentyFourBomb, "Проверка кнопки №24, выбора кол-во бомб провалена!");
            t.assertNotNull(TextTwentyFourBomb);
        System.out.println("*Проверка кнопки №24, выбора кол-во бомб, ---> выполнено*");

        //Выставляем свое колличество бомб (без кнопок) + проверяем, что выбралось нужное
        step("Выставляем свое колличество бомб (без кнопок)", Status.PASSED);
        driver.findElements(By.xpath(AnotherInput)).get(0).sendKeys(Keys.CONTROL,"a");
        Thread.sleep(600);
        driver.findElements(By.xpath(AnotherInput)).get(0).sendKeys("4");
        Thread.sleep(900);
            /*String TextYourExampleBomb =  driver.findElement(By.xpath(AnotherInput)).getText();
            String ExpectedYourExampleBomb = "4";
            t.assertEquals(TextYourExampleBomb, ExpectedYourExampleBomb, "Проверка кнопки №4, выбора кол-во бомб провалена!");
            t.assertNotNull(TextYourExampleBomb);
        System.out.println("*Проверка кнопки №4, выбора кол-во бомб, ---> выполнено*");*/

        //Жмем "Играть"
        step("Жмем Играть", Status.PASSED);
        driver.findElements(By.xpath(PlayMiner)).get(0).click();
        Thread.sleep(600);

        //Выбор ячейки 23
        step("Выбор ячейки 23", Status.PASSED);
        driver.findElements(By.xpath(OpenaCell)).get(22).click();
        Thread.sleep(600);

        //После
        step("После, скриншот", Status.PASSED);
        byte[] screenshotAsTo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);


        //Забрать приз. Если выйграл, то забрал, если нет, то завершаем тест.
        step("Забрать приз", Status.PASSED);
        driver.findElements(By.xpath(WinMinerPlay)).get(0).click();
        Thread.sleep(400);

        WebElement currentDiv = null;
        WebElement[] divs = driver.findElement(By.className("bit-feed__cell_bit")).findElements(By.className("btn_full")).toArray(new WebElement[0]);
        for (WebElement div : divs) {
            if (!div.getCssValue("display").equals("none")) {
                currentDiv = div;
            }
        }

        if (currentDiv != null && currentDiv.getText().contains("Забрать")) {
            currentDiv.click();
        }

        //Аллюр Аттач
        Allure.attachment("Логи", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
        Allure.addAttachment("Скриншот: До начала игры", new ByteArrayInputStream(screenshotAs));
        Allure.addAttachment("Скриншот: Сыграли, но приз не забрали", new ByteArrayInputStream(screenshotAsTo));

        t.assertAll();
    }
}
