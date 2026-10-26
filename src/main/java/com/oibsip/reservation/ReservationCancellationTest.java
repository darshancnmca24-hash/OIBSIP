
package com.oibsip.reservation;

import com.oibsip.reservation.dao.ReservationDAO;
import com.oibsip.reservation.model.Reservation;
import com.oibsip.reservation.service.ReservationBookingService;

import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationCancellationTest {

    public static void main(String[] args) {
        ReservationBookingService bookingService =
                new ReservationBookingService();
        ReservationDAO reservationDAO = new ReservationDAO();

        try {
            String pnr = bookingService.bookReservation(
                    "Cancellation Test",
                    "99999",
                    "Test Express",
                    LocalDate.now().plusDays(10),
                    "Bengaluru",
                    "Mysuru",
                    "Sleeper"
            );

            System.out.println("Test booking created: " + pnr);

            boolean cancelled = reservationDAO.cancelByPnr(pnr);

            if (cancelled && reservationDAO.findByPnr(pnr) == null) {
                System.out.println("Cancellation successful!");
                System.out.println("Test reservation is no longer in the database.");
            } else {
                System.out.println("Cancellation verification failed.");
            }

        } catch (SQLException | IllegalArgumentException exception) {
            System.out.println("Test failed: " + exception.getMessage());
        }
    }
}
