package API;

import io.qameta.allure.Allure;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationAPITest {

    private static final String BASE_URL = "https://api.gz62f.top";
    private static final String REGISTER_ENDPOINT = "/auth/register/email";

    @Test
    public void testRegistrationAPIAndSaveAccounts() {
        Set<String> registeredEmails = new HashSet<>(); // Сет для хранения уже зарегистрированных email-адресов

        // Открываем файл для записи данных о зарегистрированных аккаунтах
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("registered_accounts.csv", true))) {
            // Выполняем регистрацию 300 раз и записываем данные в файл
            for (int i = 0; i < 300; i++) {
                RestAssured.baseURI = BASE_URL;
                String email = generateUniqueEmail();

                // Проверяем, не использовался ли уже данный email-адрес
                if (registeredEmails.contains(email)) {
                    continue; // Пропускаем регистрацию итерации, если email уже использовался
                }

                String password = "TestRegUser12345";
                try {
                    Response response = registerWithEmail(email, password);
                    // Извлекаем информацию о зарегистрированном аккаунте из ответа
                    String accountInfo = response.getBody().asString();
                    // Записываем информацию в файл
                    writer.write(accountInfo);
                    writer.newLine();
                    // Добавляем email в список зарегистрированных
                    registeredEmails.add(email);
                } catch (Exception e) {
                    // Если произошла ошибка "E-mail уже используется", просто продолжаем цикл
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String generateUniqueEmail() {
        String baseEmail = "pro100igo228.";
        String emailDomain = "@gmail.com";
        long emailSuffix = System.currentTimeMillis() % 10000; // просто пример для ограничения длины суффикса

        // Создаем полный адрес электронной почты
        String fullEmail = baseEmail + emailSuffix + emailDomain;

        // Проверяем длину
        if (fullEmail.length() > 30) {
            // Высчитываем количество символов, которые нужно удалить
            int excessLength = fullEmail.length() - 30;

            // Получаем текущую длину суффикса
            int suffixLength = String.valueOf(emailSuffix).length();

            // Урезаем суффикс на необходимое количество символов
            emailSuffix = (long) (emailSuffix / Math.pow(10, excessLength));

            // Пересоздаем адрес электронной почты с новым суффиксом
            fullEmail = baseEmail + emailSuffix + emailDomain;
        }

        return fullEmail;
    }

    private Response registerWithEmail(String email, String password) {
        String requestBody = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(REGISTER_ENDPOINT);

        if (response.getStatusCode() == 422) {
            System.out.println("Статусный код 422 (Unprocessable Entity). Продолжаем выполнение теста.");
        } else {
            response.then()
                    .log().body()
                    .statusCode(200)
                    .body("original.success", equalTo(true));

            // Добавляем код для сохранения данных в файл
            String accountInfo = response.getBody().asString();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("registered_accounts.csv", true))) {
                writer.write(accountInfo);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Создание ObjectMapper для форматирования JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Парсинг тела ответа в JsonNode
            JsonNode jsonNode = mapper.readTree(response.getBody().asString());

            // Преобразование JsonNode в отформатированную строку
            String prettyJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            // Добавление данных в отчет Allure
            Allure.attachment("Тело запроса", requestBody);
            Allure.attachment("Тело ответа", prettyJsonString);
            Allure.attachment("Код статуса", String.valueOf(response.getStatusCode()));
            Allure.attachment("Origin из JSON", response.jsonPath().getString("original.origin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}

