package api_tests;

import dto.AuthRequest;
import dto.AuthResponse;
import dto.BookingRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import utils.TestDataFactory;

import static io.restassured.RestAssured.given;

public class BookerApiTestBase {
    protected static final String BASE_URL = "https://restful-booker.herokuapp.com";
    protected static String token;
    protected static int createdBookingId;

    @BeforeAll
    @DisplayName("Настройка базовых параметров тестов")
    static void setup() {
        RestAssured.baseURI = BASE_URL;
        authenticate();
    }

    @DisplayName("Аутентификация пользователя")
    protected static void authenticate() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");

        AuthResponse authResponse = given()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .as(AuthResponse.class);

        token = authResponse.getToken();
    }

    @DisplayName("Создание тестового бронирования")
    protected static int createTestBooking() {
        BookingRequest bookingRequest = TestDataFactory.createDefaultBooking();

        return given()
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .path("bookingid");
    }

    @DisplayName("Получение списка бронирований")
    protected static Response getBookingIds() {
        return given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    @DisplayName("Проверка наличия бронирования в списке")
    protected static boolean isBookingPresent(int bookingId) {
        return getBookingIds().jsonPath().getList("bookingid").contains(bookingId);
    }
}
