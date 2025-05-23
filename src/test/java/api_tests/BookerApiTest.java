package api_tests;

import dto.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestDataFactory;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class BookerApiTest extends BookerApiTestBase {

    @Test
    @DisplayName("CRUD: Создание => Обновление => Удаление")
    @Severity(SeverityLevel.CRITICAL)
    void fullTest() {
        createdBookingId = createTestBooking();
        assertThat(createdBookingId).isPositive();
        assertThat(isBookingPresent(createdBookingId)).isTrue();
        BookingRequest updateRequest = TestDataFactory.createUpdatedBooking();

        BookingRequest updatedBooking = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updateRequest)
                .when()
                .put("/booking/" + createdBookingId)
                .then()
                .statusCode(200)
                .extract()
                .as(BookingRequest.class);

        assertThat(updatedBooking.getFirstname()).isEqualTo("AfterUpdate");
        assertThat(updatedBooking.getTotalprice()).isEqualTo(200);
        assertThat(updatedBooking.isDepositpaid()).isFalse();

        given()
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + createdBookingId)
                .then()
                .statusCode(201);

        assertThat(isBookingPresent(createdBookingId)).isFalse();
    }

    @Test
    @DisplayName("Проверка отсутствия удаленного бронирования в списке")
    void getBookingIds_ShouldNotContainDeletedBooking() {
        int tempBookingId = createTestBooking();
        given()
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + tempBookingId)
                .then()
                .statusCode(201);

        Response response = getBookingIds();
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("bookingid")).doesNotContain(tempBookingId);
    }

    @Test
    @DisplayName("Негативный тест: Неверный токен при обновлении и удалении")
    @Severity(SeverityLevel.NORMAL)
    void createUpdateDelete_WithInvalidToken_ShouldFail() {
        createdBookingId = createTestBooking();
        BookingRequest updateRequest = TestDataFactory.createUpdatedBooking();

        given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=invalid_token")
                .body(updateRequest)
                .when()
                .put("/booking/" + createdBookingId)
                .then()
                .statusCode(403);

        given()
                .header("Cookie", "token=invalid_token")
                .when()
                .delete("/booking/" + createdBookingId)
                .then()
                .statusCode(403);

        given()
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + createdBookingId)
                .then()
                .statusCode(201);
    }

    @Test
    @DisplayName("Негативный тест: Бронирование с невалидными данными")
    @Severity(SeverityLevel.NORMAL)
    void createBooking_WithInvalidData_ShouldReturn400() {
        BookingRequest invalidBooking = new BookingRequest();
        invalidBooking.setFirstname("");
        invalidBooking.setTotalprice(-100);

        given()
                .contentType(ContentType.JSON)
                .body(invalidBooking)
                .when()
                .post("/booking")
                .then()
                .statusCode(400); // Фактически получаем 500 (БАГ)
    }
}
