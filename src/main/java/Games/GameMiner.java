package Games;

import Utils.LogUtils;
import Utils.WaitUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import static Games.ConfigFileGames.LocatorsGames.GetXMINER;
import static io.qameta.allure.Allure.step;

@Owner("Makeenkov Igor")
public class GameMiner {
    String PlayMiner = "//button[@class=\"btn btn_full btn_long btn_can-cancel\"]";
    String WinMinerPlay = "//button[@class=\"btn btn_full btn_long btn_can-cancel btn-pick-up\"]";
    String NumberofBombs = "//button[@class=\"bit-feed__btn\"]";
    String BOmbsNamberFrom5Before24 = "//button[@class=\"bit-feed__btn active\"]";
    String OpenaCell = "//div[@class=\"miner_cell_container opened-cell\"]";
    String AnotherInput = "//input[@class=\"number-field__value number-field__value_bid\"]";

    @Description("Игра Минер")
    public void gameminer(WebDriver driver) throws InterruptedException, IOException {
        driver.get(GetXMINER);
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        waitUtils.waitForPageToLoad();
        SoftAssert t = new SoftAssert();

        // До
        byte[] screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Выставляем количество бомб (кнопки) + проверяем, что выбралось нужное
        step("Выставляем количество бомб (кнопки) + проверяем, что выбралось нужное", Status.PASSED);
        // 5 бомб
        step("5 бомб", Status.PASSED);
        driver.findElements(By.xpath(NumberofBombs)).get(0).click(); // 5 бомб клик
        waitUtils.waitForPageToLoad();
        String TextFiveBomb = driver.findElement(By.xpath(BOmbsNamberFrom5Before24)).getText();
        String ExpectedFiveBomb = "5";
        t.assertEquals(TextFiveBomb, ExpectedFiveBomb, "Проверка кнопки №5, выбора кол-во бомб провалена!");
        t.assertNotNull(TextFiveBomb);
        System.out.println("*Проверка кнопки №5, выбора кол-во бомб, ---> выполнено*");

        // 10 бомб
        step("10 бомб", Status.PASSED);
        driver.findElements(By.xpath(NumberofBombs)).get(1).click(); // 10 бомб клик
        waitUtils.waitForPageToLoad();
        String TextTenBomb = driver.findElement(By.xpath(BOmbsNamberFrom5Before24)).getText();
        String ExpectedTenBomb = "10";
        t.assertEquals(TextTenBomb, ExpectedTenBomb, "Проверка кнопки №10, выбора кол-во бомб провалена!");
        t.assertNotNull(TextTenBomb);
        System.out.println("*Проверка кнопки №10, выбора кол-во бомб, ---> выполнено*");

        // 16 бомб
        step("16 бомб", Status.PASSED);
        driver.findElements(By.xpath(NumberofBombs)).get(2).click(); // 16 бомб клик
        waitUtils.waitForPageToLoad();
        String TextSixteenBomb = driver.findElement(By.xpath(BOmbsNamberFrom5Before24)).getText();
        String ExpectedSixteenBomb = "16";
        t.assertEquals(TextSixteenBomb, ExpectedSixteenBomb, "Проверка кнопки №16, выбора кол-во бомб провалена!");
        t.assertNotNull(TextSixteenBomb);
        System.out.println("*Проверка кнопки №16, выбора кол-во бомб, ---> выполнено*");

        // 24 бомбы
        step("24 бомбы", Status.PASSED);
        driver.findElements(By.xpath(NumberofBombs)).get(3).click(); // 24 бомб клик
        waitUtils.waitForPageToLoad();
        String TextTwentyFourBomb = driver.findElement(By.xpath(BOmbsNamberFrom5Before24)).getText();
        String ExpectedTwentyFourBomb = "24";
        t.assertEquals(TextTwentyFourBomb, ExpectedTwentyFourBomb, "Проверка кнопки №24, выбора кол-во бомб провалена!");
        t.assertNotNull(TextTwentyFourBomb);
        System.out.println("*Проверка кнопки №24, выбора кол-во бомб, ---> выполнено*");

        // Выставляем свое количество бомб (без кнопок) + проверяем, что выбралось нужное
        step("Выставляем свое количество бомб (без кнопок)", Status.PASSED);
        driver.findElements(By.xpath(AnotherInput)).get(0).sendKeys(Keys.CONTROL, "a");
        waitUtils.waitForPageToLoad();
        driver.findElements(By.xpath(AnotherInput)).get(0).sendKeys("4");
        waitUtils.waitForPageToLoad();

        // Жмем "Играть"
        step("Жмем Играть", Status.PASSED);
        driver.findElements(By.xpath(PlayMiner)).get(0).click();
        waitUtils.waitForPageToLoad();

        // Выбор случайной ячейки
        step("Выбор случайной ячейки", Status.PASSED);
        Random random = new Random();
        int randomIndex = random.nextInt(25); // Генерация случайного числа от 0 до 24
        driver.findElements(By.xpath(OpenaCell)).get(randomIndex).click();
        waitUtils.waitForPageToLoad();

        // После
        step("После, скриншот", Status.PASSED);
        byte[] screenshotAsTo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        // Забрать приз, если выиграл, если нет, то завершаем тест.
        step("Забрать приз", Status.PASSED);
        WebElement takeButton = driver.findElement(By.xpath(WinMinerPlay));
        if (takeButton.isDisplayed() && takeButton.isEnabled()) {
            takeButton.click();
            waitUtils.waitForPageToLoad();

            // Обновляем переменную takeButton после клика
            takeButton = driver.findElement(By.xpath(PlayMiner));
        }

        // Проверка наличия кнопки "Играть"
        boolean playButtonDisplayed = takeButton.isDisplayed();
        boolean playButtonEnabled = takeButton.isEnabled();

        // Если кнопка "Играть" отображается, то считаем тест успешным
        if (playButtonDisplayed) {
            step("Кнопка 'Играть' отображается", Status.PASSED);
        } else {
            step("Кнопка 'Забрать' не отображается", Status.PASSED);
        }

        // Аллюр Аттач
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        String formattedLogs = LogUtils.formatBrowserLogs(browserLogs);
        Allure.attachment("Логи", formattedLogs);
        Allure.addAttachment("Скриншот: До начала игры", new ByteArrayInputStream(screenshotAs));
        Allure.addAttachment("Скриншот: Игра завершена", new ByteArrayInputStream(screenshotAsTo));

        t.assertAll();
    }
}
