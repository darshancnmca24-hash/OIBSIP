
package com.oibsip.reservation;

import com.oibsip.reservation.service.ReservationBookingService;

import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationBookingTest {

    public static void main(String[] args) {
        ReservationBookingService bookingService =
                new ReservationBookingService();

        try {
            String pnr = bookingService.bookReservation(
                    "Darshan Test",
                    "12627",
                    "Karnataka Express",
                    LocalDate.now().plusDays(7),
                    "Bengaluru",
                    "New Delhi",
                    "Sleeper"
            );

            System.out.println("Booking saved successfully!");
            System.out.println("Generated PNR: " + pnr);

        } catch (SQLException | IllegalArgumentException exception) {
            System.out.println("Booking failed: " + exception.getMessage());
        }
    }
}
