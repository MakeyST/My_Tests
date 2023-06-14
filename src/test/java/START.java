import Games.ConfigFileGames.StartGames;
import Payment.ConfigFilePayment.StartPayment;
import ProfileUser.ConfigFileProfile.StartProfileUser;
import QuickGames.ConfigFileQuickGames.StartQuickGames;
import Utils.ConfigFileUtils.StartUtils;
import Utils.RegressDriver;
import io.qameta.allure.*;
import io.qameta.allure.model.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.qameta.allure.Allure.step;


public class START {
    public static WebDriver driver;
    //Система
    @BeforeSuite(description = "Путь к драйверу + все опции и аргументы", inheritGroups = true, groups = {"StartThis", "Profile", "Miner", "Quit", "Payment"})
    @Feature("Фаил запуска кейсов")
    @Owner("Makeenkov Igor")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Запускаются как отдельно, так и все вместе, зависимости между кейсами отсутсвуют")
    public void Start() {
        step("Кофниг, запуск", Status.PASSED);

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\chromedriver113.0.5672.127.exe"
        );

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10 Pro");
        browserOptions.setBrowserVersion("113.0.5672.127");
        browserOptions.addArguments("start-maximized");
        browserOptions.addArguments("incognito");
        driver = RegressDriver.getDriver();
        System.out.println("Драйвер ===>ON");
    }

    @BeforeTest(description = "Развернуть окно + Авторизация", inheritGroups = true , groups = {"StartThis"})
    public void WindowProfit() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver.manage().window().maximize();
        step("Развернуть окно + Авторизация", Status.PASSED);
        try {
            StartUtils auth = new StartUtils();
            auth.Authorization(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}


    @Epic("ПРОФИЛЬ ЮЗЕРА")
    @Owner("Makeenkov Igor")
    @Step("Смена никнейма")
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Profile")
    public void SettingNiknameENGRUS(){
        step("Смена никнейма", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.nicknameEngRus(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}

    @Step("Смена языка системы")
    @Test(alwaysRun = true, priority = 2, invocationCount = 1, groups = "Profile")
    public void SettingSystemlanguage(){
        step("Смена языка системы", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.systemLanguage(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Step("Использование промокода")
    @Test(alwaysRun = true, priority = 3, invocationCount = 1, groups = "Profile")
    public void SettingPromoCode(){
        step("Использование промокода", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.settingsPromoCode(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Step("Проверка введенного промокода")
    @Severity(SeverityLevel.NORMAL)
    @Description("Пока не готово, в планах, будет делать скрины, что промокод активирован")
    @Test(alwaysRun = true, priority = 4, invocationCount = 1, groups = "Profile")
    public void SettingControlPromoCode(){
        step("Проверка введенного промокода", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.controlPromocode(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Step("Смена пароля")
    @Test(alwaysRun = true, priority = 5, invocationCount = 1, groups = "Profile")
    public void SettingPassword (){
        step("Смена пароля", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.settingsPassword(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Step("Возврат прошлого пароля")
    @Test(alwaysRun = true, priority = 6, invocationCount = 1, groups = "Profile")
    public void SettingPasswordBack (){
        step("Возврат прошлого пароля", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.settingsPasswordBack(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}


    @Epic("ИГРА В МИНЕРА")
    @Owner("Makeenkov Igor")
    @Step("Игра Минер")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Miner")
    public void GamesMiner(){
        step("Игра Минер", Status.PASSED);
        try {
            StartGames Games = new StartGames();
            Games.miner(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}


    @Feature("БЫСТРЫЕ ИГРЫ")
    @Owner("Makeenkov Igor")
    @Step("Быстрая игра Минер")
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "FastMiner")
    public void QuickGamesMiner(){
        step("Быстрая игра Минер", Status.PASSED);
        try {
            StartQuickGames GamesFast = new StartQuickGames();
            GamesFast.quickMiner(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Feature("СПОСОБЫ ОПЛАТЫ")
    @Owner("Makeenkov Igor")
    @Step("Оплата Сбербанк")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Payment")
    public void PaymentSberbankCase(){
        step("Оплата Сбербанк", Status.PASSED);
        try {
            StartPayment pay = new StartPayment();
            pay.paySber(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}


    @Feature("ВЫХОД")
    @Step("Выход с теста")
    @Owner("Makeenkov Igor")
    @Test(description = "Выход из теста", groups = "Quit")
    public void QUITUp() throws IOException {
        step("Выход с теста", Status.PASSED);
        driver.quit();
    }

}
