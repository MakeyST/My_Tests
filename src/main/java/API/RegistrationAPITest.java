package API;

import io.qameta.allure.Allure;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationAPITest {

    private static final String BASE_URL = "https://api.gz62f.top";
    private static final String REGISTER_ENDPOINT = "/auth/register/email";

    @Test
    public void testRegistrationAPI() {
        RestAssured.baseURI = BASE_URL;

        String email = generateUniqueEmail();
        String password = "TestRegUser12345";

        registerWithEmail(email, password);
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

    private void registerWithEmail(String email, String password) {
        String requestBody = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(REGISTER_ENDPOINT)
                .then()
                .log().body()
                .statusCode(200)
                .body("original.success", equalTo(true))
                .extract().response(); // Извлечение ответа для последующего использования

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
    }
}

