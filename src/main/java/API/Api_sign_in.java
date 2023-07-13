package API;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class Api_sign_in {
        private static final String BASE_URL = "https://api.gz62f.top";
        private static final String ENDPOINT = "/auth/login/email";

        @Test
        public void testAPI() {
            RestAssured.baseURI = BASE_URL;

            String email = "pro100igo228@gmail.com";
            String password = "23Makey23";

            // Шаги теста
            loginWithEmail(email, password);
        }

        @Step("Авторизация с использованием электронной почты: {email}")
        private void loginWithEmail(String email, String password) {
            String requestBody = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";

            given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(ENDPOINT)
                    .then()
                    .statusCode(200)
                    .body("status", equalTo("success"))
                    .extract()
                    .path("data.token");

            // Добавление данных в отчет Allure
            Allure.attachment("Отчет", requestBody);
        }
}


