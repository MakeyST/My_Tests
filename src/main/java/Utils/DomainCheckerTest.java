package Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.DevToolsException;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v113.network.Network;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Utils.LocatorsAuthorization.LoginPro100igo228;
import static Utils.LocatorsAuthorization.PasswordALLUSERS;
import static io.qameta.allure.Allure.step;

public class DomainCheckerTest {
    private WebDriver driver;
    private HttpClient httpClient;
    private DevTools devTools;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.logfile", "chromedriver.log");
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\ChromeDriver114.0.5735.90.exe"
        );
        driver = new ChromeDriver();
        httpClient = HttpClientBuilder.create().build();
    }

    @DataProvider(name = "domains")
    public Object[][] getDomains() throws IOException {
        // Путь к Excel-документу
        String filePath = "src/main/resources/Excel/DOMAIN.xlsx";
        // Номер листа в Excel-документе, где находятся URL-адреса
        int sheetIndex = 1; // Измените согласно вашим требованиям
        // Номер столбца в Excel-документе, где находятся URL-адреса
        int columnIndex = 1; // Измените согласно вашим требованиям

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(sheetIndex);

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

        Object[][] data = new Object[rowCount][1];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1);
            Cell cell = row.getCell(columnIndex);
            data[i][0] = cell.getStringCellValue();
        }

        fis.close();
        workbook.close();

        return data;
    }

    @Test(dataProvider = "domains")
    public void testDomainAvailability(String domain) {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));

        // Отладочная информация
        System.out.println("Открываю страницу: " + domain);

        // Открытие страницы
        openPage(domain);

        // Ожидание загрузки страницы
        waitUtils.waitForPageToLoad();

        // Другие шаги вашего тестового случая
        authorize();
        checkAPIStatuses();
    }



    private void openPage(String domain) {
        step("Открываю страницу: " + domain, Status.PASSED);
        System.out.println("Domain value: " + domain);
        try {
            driver.get(domain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Step("Авторизация на сайте")
    private void authorize() {
        WaitUtils waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
        System.out.println("-----Авторизация запущена-----");
        SoftAssert t = new SoftAssert();

        try {
            //Клик "Войти"
            step("Открыть окно авторизации", Status.PASSED);
            waitUtils.waitForPageToLoad();
            driver.findElement(By.id("btn-login")).click();
            waitUtils.waitForPageToLoad();
            byte[] loginPopup = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            //Ввод логина
            step("Ввод логина", Status.PASSED);
            waitUtils.waitForPageToLoad();
            driver.findElement(By.id("login-field-email")).sendKeys(LoginPro100igo228);
            waitUtils.waitForPageToLoad();
            byte[] screenshotLogin = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            //Проверка логина
            step("Проверка логина", Status.PASSED);
            String TextLogin = driver.findElement(By.id("login-field-email")).getAttribute("value");
            String ExpectedLogin = LoginPro100igo228;
            t.assertEquals(TextLogin, ExpectedLogin,"Проверка поля E-mail ПРОВАЛЕНА!");
            t.assertNotNull(TextLogin);
            System.out.println("*Проверка E-mail, ---> выполнено*");

            //Ввод пароль
            step("Ввод пароль", Status.PASSED);
            waitUtils.waitForPageToLoad();
            driver.findElement(By.id("login-field-password")).sendKeys(PasswordALLUSERS);
            waitUtils.waitForPageToLoad();
            byte[] screenshotPassword = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            //Проверка пароля
            step("Проверка пароля", Status.PASSED);
            String TextPassword = driver.findElement(By.id("login-field-password")).getAttribute("value");
            String ExpectedPassword = PasswordALLUSERS;
            t.assertEquals(TextPassword, ExpectedPassword, "Проверка поля Пароль ПРОВАЛЕНА!");
            t.assertNotNull(TextPassword);
            System.out.println("*Проверка пароля, ---> выполнено*");

            //Клик "Войти" в учётку
            step("Клик Войти в учётку", Status.PASSED);
            waitUtils.waitForPageToLoad();
            driver.findElement(By.id("login-form-login")).click();
            waitUtils.waitForPageToLoad();

            System.out.println("Авторизация пройдена");
            waitUtils.waitForPageToLoad();
            byte[] screenshotTo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            step("Ошибка при авторизации: " + e.getMessage(), Status.FAILED);
            throw new RuntimeException(e);
        }
    }

    @Step("Проверка статусов API")
    private void checkAPIStatuses() {
        List<String> apiUrls = getAPIUrlsFromNetworkTab();

        for (String apiUrl : apiUrls) {
            int statusCode = getResponseStatus(apiUrl);
            Assert.assertEquals(statusCode, 200, "Статус запроса не является 200");
        }
    }

    @Step("Получение статуса ответа для URL {url}")
    private int getResponseStatus(String url) {
        int statusCode = 0;
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(request);
            StatusLine statusLine = response.getStatusLine();
            statusCode = statusLine.getStatusCode();
            if (statusCode != 200 && statusCode != 204) {
                step("Ошибка: Статус запроса для " + url + " не является 200 или 204", Status.FAILED);
            }
            logAPIResponse(url, statusCode, response.getEntity());
        } catch (IOException e) {
            step("Ошибка при выполнении запроса к " + url + ": " + e.getMessage(), Status.FAILED);
            e.printStackTrace();
        }
        return statusCode;
    }

    @Step("Логирование ответа API для URL {url}")
    private void logAPIResponse(String url, int statusCode, HttpEntity entity) throws IOException {
        InputStream content = entity.getContent();
        byte[] bytes = content.readAllBytes();
        String responseString = new String(bytes, StandardCharsets.UTF_8);
        Allure.addAttachment("API Response - " + url, new ByteArrayInputStream(responseString.getBytes()));
        content.close();
    }

    @Step("Получение URL'ов API из вкладки Network в DevTools")
    private List<String> getAPIUrlsFromNetworkTab() {
        List<String> apiUrls = new ArrayList<>();

        try {
            devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSession();

            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            devTools.addListener(Network.requestWillBeSent(), event -> {
                String url = event.getRequest().getUrl();
                apiUrls.add(url);
            });

            devTools.send(Network.disable());

            devTools.close();
        } catch (DevToolsException e) {
            step("Ошибка при получении URL'ов API: " + e.getMessage(), Status.FAILED);
            e.printStackTrace();
        }

        return apiUrls;
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
