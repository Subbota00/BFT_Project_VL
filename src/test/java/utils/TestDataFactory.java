package utils;

import dto.BookingDates;
import dto.BookingRequest;
import org.junit.jupiter.api.DisplayName;

public class TestDataFactory {
    @DisplayName("Создание бронирования с параметрами по умолчанию")
    public static BookingRequest createDefaultBooking() {
        BookingDates dates = new BookingDates("2025-01-01", "2025-01-02");
        return new BookingRequest("Default", "User", 100, true, dates, "None");
    }

    @DisplayName("Создание бронирования для тестов обновления")
    public static BookingRequest createBookingForUpdate() {
        BookingDates dates = new BookingDates("2025-02-01", "2025-02-03");
        return new BookingRequest("BeforeUpdate", "User", 150, true, dates, "WiFi");
    }

    @DisplayName("Создание данных для обновления бронирования")
    public static BookingRequest createUpdatedBooking() {
        BookingDates dates = new BookingDates("2025-03-01", "2025-03-05");
        return new BookingRequest("AfterUpdate", "User", 200, false, dates, "Breakfast");
    }
}