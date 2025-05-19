package dto;

import lombok.Data;

@Data
public class BookingResponse {
    private int bookingid;
    private BookingRequest booking;
}
