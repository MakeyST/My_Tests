package API;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiSignIn {
    private static final String BASE_URL = "https://api.gz62f.top";
    private static final String ENDPOINT = "/auth/login/email";
    private RequestSpecification request;
    private String requestBody;

    @Test
    public void testAPI() {
        RestAssured.baseURI = BASE_URL;

        String email = "pro100igo228@gmail.com";
        String password = "23Makey23";

        // Шаги теста
        Response response = loginWithEmail(email, password);

        // Добавление данных в отчет Allure
        Allure.attachment("Статус код", String.valueOf(response.getStatusCode()));
        Allure.attachment("JSON ответ", response.getBody().prettyPrint());
        Allure.attachment("Отчет", requestBody);
    }

    @Step("Авторизация с использованием электронной почты: {email}")
    private Response loginWithEmail(String email, String password) {
        requestBody = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";

        request = given()
                .contentType(ContentType.JSON)
                .body(requestBody);

        Response response = request.post(ENDPOINT);

        response.then()
                .statusCode(200)
                .body("success", equalTo(true));

        return response;
    }
}


