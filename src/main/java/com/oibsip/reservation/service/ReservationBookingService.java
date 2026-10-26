
package com.oibsip.reservation.service;

import com.oibsip.reservation.dao.ReservationDAO;
import com.oibsip.reservation.model.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationBookingService {

    private final ReservationService reservationService;
    private final ReservationDAO reservationDAO;

    public ReservationBookingService() {
        this.reservationService = new ReservationService();
        this.reservationDAO = new ReservationDAO();
    }

    public String bookReservation(
            String passengerName,
            String trainNumber,
            String trainName,
            LocalDate travelDate,
            String source,
            String destination,
            String travelClass) throws SQLException {

        String pnr = reservationService.generatePnr();

        Reservation reservation = new Reservation(
                pnr,
                passengerName,
                trainNumber,
                trainName,
                travelDate,
                source,
                destination,
                travelClass
        );

        reservationService.validateReservation(reservation);
        reservationDAO.save(reservation);

        return pnr;
    }
}
