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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\chromedriver113.0.5672.127.exe"
        );
        driver = new ChromeDriver();
        httpClient = HttpClientBuilder.create().build();
    }

    @DataProvider(name = "domains")
    public Object[][] getDomains() throws IOException {
        // Путь к Excel-документу
        String filePath = "/Excel/";
        // Номер листа в Excel-документе, где находятся URL-адреса
        int sheetIndex = 2;
        // Номер столбца в Excel-документе, где находятся URL-адреса
        int columnIndex = 1;

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
        openPage(domain);
        checkAPIStatuses();
    }

    @Step("Открытие страницы {domain}")
    private void openPage(String domain) {
        driver.get(domain);
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
            logAPIResponse(url, statusCode, response.getEntity());
        } catch (IOException e) {
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

            // Выполняйте действия на странице, которые вызывают запросы к API
            // Например, нажатие кнопки или другие действия, вызывающие запросы к API
            WebElement buttonElement = driver.findElement(By.xpath("//button[@id=\"btn-login\"]"));
            buttonElement.click();
            //Клик "Войти"
            step("Открыть окно авторизации", Status.PASSED);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.id("btn-login")).click();
            Thread.sleep(400);

            //Ввод логин
            step("Ввод логин", Status.PASSED);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.id("login-field-email")).sendKeys(LoginPro100igo228);
            Thread.sleep(100);

            //Проверка логина
            step("Проверка логина", Status.PASSED);
            String TextLogin = driver.findElement(By.id("login-field-email")).getAttribute("value");
            String ExpectedLogin = LoginPro100igo228;
            Assert.assertEquals(TextLogin, ExpectedLogin,"Проверка поля E-mail ПРОВАЛЕНА!");
            Assert.assertNotNull(TextLogin);
            System.out.println("*Проверка E-mail, ---> выполнено*");

            //Ввод пароль
            step("Ввод пароль", Status.PASSED);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.id("login-field-password")).sendKeys(PasswordALLUSERS);
            Thread.sleep(100);

            //Проверка пароля
            step("Проверка пароля", Status.PASSED);
            String TextPassword = driver.findElement(By.id("login-field-password")).getAttribute("value");
            String ExpectedPassword = PasswordALLUSERS;
            Assert.assertEquals(TextPassword, ExpectedPassword, "Проверка поля Пароль ПРОВАЛЕНА!");
            Assert.assertNotNull(TextPassword);

            //Клик "Войти" в учётку
            step("Клик Войти в учётку", Status.PASSED);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.id("login-form-login")).click();
            Thread.sleep(200);

            devTools.send(Network.disable());

            devTools.close();
        } catch (DevToolsException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
