import FAQ.ConfigFileFAQ.StartFAQ;
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

import static io.qameta.allure.Allure.step;



public class START {
    public static WebDriver driver;
    //Система
    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("Запуск всех кейсов (конфиг)")
    @Owner("Makeenkov Igor")
    @Description("Запускаются как отдельно, так и все вместе, зависимости между кейсами отсутсвуют")
    @Severity(SeverityLevel.CRITICAL)
    @BeforeSuite(description = "Путь к драйверу + все опции и аргументы", inheritGroups = true, groups = {"StartThis", "Profile", "Miner", "Quit", "Payment", "FAQ"})
    public void Start() {
        step("Кофниг, запуск", Status.PASSED);

        step("Путь к драйверу", Status.PASSED);
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\chromedriver113.0.5672.127.exe"
        );

        step("Настройки бразуера", Status.PASSED);
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName(LocatorsBrowserOptions.PlatformName);
        browserOptions.setBrowserVersion(LocatorsBrowserOptions.BrowserVersion);
        browserOptions.addArguments(LocatorsBrowserOptions.WindowSize);
        browserOptions.addArguments(LocatorsBrowserOptions.BrowserMode);
        driver = RegressDriver.getDriver();
        step("Чистим куки при запуске", Status.PASSED);
        driver.manage().deleteAllCookies(); // Чистим куки
        step("Драйвер ===> ON", Status.PASSED);
    }
    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("Авторизация")
    @Owner("Makeenkov Igor")
    @Feature("Авторизация + настройки браузера")
    @Description("Настройка браузера, открыть в полном окне и пройти авторизацию")
    @Severity(SeverityLevel.CRITICAL)
    @BeforeTest(description = "Развернуть окно + Авторизация", inheritGroups = true , groups = {"StartThis"})
    public void WindowProfit() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver.manage().window().maximize();
        step("Развернуть окно + Авторизация", Status.PASSED);
        try {
            StartUtils auth = new StartUtils();
            auth.Authorization(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}


    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("ПРОФИЛЬ ЮЗЕРА")
    @Owner("Makeenkov Igor")
    @Step("Смена никнейма")
    @Description("Кейс смены никнейма")
    @Severity(SeverityLevel.NORMAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Profile", description = "Смена никнейма")
    public void SettingNiknameENGRUS(){
        step("Смена никнейма", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.nicknameEngRus(driver);
        } catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("ПРОФИЛЬ ЮЗЕРА")
    @Step("Смена языка системы")
    @Owner("Makeenkov Igor")
    @Description("Кейс смены языка системы англ + рус")
    @Severity(SeverityLevel.NORMAL)
    @Test(alwaysRun = true, priority = 2, invocationCount = 1, groups = "Profile", description = "Смена языка системы")
    public void SettingSystemlanguage(){
        step("Смена языка системы", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.systemLanguage(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("ПРОФИЛЬ ЮЗЕРА")
    @Step("Использование промокода")
    @Owner("Makeenkov Igor")
    @Description("Кейс использования промокода")
    @Severity(SeverityLevel.NORMAL)
    @Test(alwaysRun = true, priority = 3, invocationCount = 1, groups = "Profile", description = "Использование промокода")
    public void SettingPromoCode(){
        step("Использование промокода", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.settingsPromoCode(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("ПРОФИЛЬ ЮЗЕРА")
    @Step("Проверка введенного промокода")
    @Owner("Makeenkov Igor")
    @Description("Пока не готово, в планах, будет делать скрины, что промокод активирован")
    @Severity(SeverityLevel.NORMAL)
    @Test(alwaysRun = true, priority = 4, invocationCount = 1, groups = "Profile", description = "Проверка введенного промокода")
    public void SettingControlPromoCode(){
        step("Проверка введенного промокода", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.controlPromocode(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("ПРОФИЛЬ ЮЗЕРА")
    @Step("Смена пароля")
    @Owner("Makeenkov Igor")
    @Description("Кейс смена пароля")
    @Severity(SeverityLevel.NORMAL)
    @Test(alwaysRun = true, priority = 5, invocationCount = 1, groups = "Profile", description = "Смена пароля")
    public void SettingPassword (){
        step("Смена пароля", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.settingsPassword(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("ПРОФИЛЬ ЮЗЕРА")
    @Step("Возврат прошлого пароля")
    @Owner("Makeenkov Igor")
    @Description("Кейс возврата прошлого пароля, сделан для того, чтобы не было зависимости")
    @Severity(SeverityLevel.NORMAL)
    @Test(alwaysRun = true, priority = 6, invocationCount = 1, groups = "Profile", description = "Возврат прошлого пароля")
    public void SettingPasswordBack (){
        step("Возврат прошлого пароля", Status.PASSED);
        try {
            StartProfileUser spu = new StartProfileUser();
            spu.settingsPasswordBack(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}


    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("ИГРЫ")
    @Owner("Makeenkov Igor")
    @Step("Игра Минер")
    @Description("Кейс игры минер, пока не хвататет проверки условия, но позже допилю")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Miner", description = "Игра Минер")
    public void GamesMiner(){
        step("Игра Минер", Status.PASSED);
        try {
            StartGames Games = new StartGames();
            Games.miner(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}


    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("БЫСТРЫЕ ИГРЫ")
    @Owner("Makeenkov Igor")
    @Step("Быстрая игра Минер")
    @Description("Чтобы не проходить полный кейс игры минер, этот кейс быстрые игры проводит, без проверки условий")
    @Severity(SeverityLevel.NORMAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "FastMiner", description = "Быстрая игра Минер")
    public void QuickGamesMiner(){
        step("Быстрая игра Минер", Status.PASSED);
        try {
            StartQuickGames GamesFast = new StartQuickGames();
            GamesFast.quickMiner(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("СПОСОБЫ ОПЛАТЫ")
    @Owner("Makeenkov Igor")
    @Step("Оплата Сбербанк")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Payment", description = "Оплата Сбербанк")
    public void PaymentSberbankCase(){
        step("Оплата Сбербанк", Status.PASSED);
        try {
            StartPayment pay = new StartPayment();
            pay.paySber(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("СПОСОБЫ ОПЛАТЫ")
    @Owner("Makeenkov Igor")
    @Step("Оплата QIWI")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Payment", description = "Оплата QIWI")
    public void PaymentQIWI(){
        step("Оплата QIWI", Status.PASSED);
        try {
            StartPayment pay = new StartPayment();
            pay.payQIWI(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("СПОСОБЫ ОПЛАТЫ")
    @Owner("Makeenkov Igor")
    @Step("Оплата СБП")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Payment", description = "Оплата СБП")
    public void PaymentSBP(){
        step("Оплата СБП", Status.PASSED);
        try {
            StartPayment pay = new StartPayment();
            pay.paySBP(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("СПОСОБЫ ОПЛАТЫ")
    @Owner("Makeenkov Igor")
    @Step("Оплата Yoomoney")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Payment", description = "Оплата Yoomoney")
    public void PaymentYoomoney(){
        step("Оплата Yoomoney", Status.PASSED);
        try {
            StartPayment pay = new StartPayment();
            pay.payYoomoney(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("СПОСОБЫ ОПЛАТЫ")
    @Owner("Makeenkov Igor")
    @Step("Оплата Криптовалюты")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "Payment", description = "Оплата Криптовалюты")
    public void PaymentCrypto(){
        step("Оплата Криптовалюты", Status.PASSED);
        try {
            StartPayment pay = new StartPayment();
            pay.payCrypto(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("ПОПУЛЯРНЫЕ ВОПРОСЫ (F.A.Q.)")
    @Owner("Makeenkov Igor")
    @Step("Общие вопросы")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 1, invocationCount = 1, groups = "FAQ", description = "Общие вопросы")
    public void gensQuestions(){
        step("Общие вопросы", Status.PASSED);
        try {
            StartFAQ FAQ = new StartFAQ();
            FAQ.genQue(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}

    @Link(name = "Test", type = "https://ppgetx.click/")
    @Link(name = "Prod", type = "https://get22.cfd/")
    @Epic("ПОПУЛЯРНЫЕ ВОПРОСЫ (F.A.Q.)")
    @Owner("Makeenkov Igor")
    @Step("Кошелек")
    @Severity(SeverityLevel.CRITICAL)
    @Test(alwaysRun = true, priority = 2, invocationCount = 1, groups = "FAQ", description = "Кошелек")
    public void gensWallet(){
        step("Кошелек", Status.PASSED);
        try {
            StartFAQ FAQ = new StartFAQ();
            FAQ.genwal(driver);
        }catch (Exception e) {throw new RuntimeException(e);}}


    @Epic("ВЫХОД")
    @Step("Выход с теста")
    @Owner("Makeenkov Igor")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Выход из теста", groups = "Quit")
    public void QUITUp(){
        step("Выход с теста", Status.PASSED);
        driver.quit();
    }

}
