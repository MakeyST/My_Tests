package API;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.UUID;

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
        String baseEmail = "pro100igo228.test";
        return baseEmail + UUID.randomUUID().toString().replaceAll("-", "") + "@gmail.com";
    }

    private void registerWithEmail(String email, String password) {
        String requestBody = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(REGISTER_ENDPOINT)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"));

        // Добавление данных в отчет Allure
        Allure.attachment("Отчет", requestBody);
    }
}

