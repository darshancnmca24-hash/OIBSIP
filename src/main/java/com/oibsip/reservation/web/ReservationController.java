package com.oibsip.reservation.web;

import com.oibsip.reservation.service.ReservationBookingService;
import com.oibsip.reservation.dao.ReservationDAO;
import com.oibsip.reservation.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationBookingService bookingService =
            new ReservationBookingService();

    private final ReservationDAO reservationDAO = new ReservationDAO();

    @org.springframework.web.bind.annotation.GetMapping("/{pnr}")
    public Reservation findReservationByPnr(
            @org.springframework.web.bind.annotation.PathVariable("pnr") String pnr)
            throws SQLException {

        Reservation reservation = reservationDAO.findByPnr(pnr.trim().toUpperCase());

        if (reservation == null) {
            throw new java.util.NoSuchElementException("Reservation not found for the given PNR.");
        }

        return reservation;
    }

    @PostMapping
    public Map<String, String> bookReservation(
            @RequestBody BookingRequest request) throws SQLException {

        String pnr = bookingService.bookReservation(
                request.passengerName(),
                request.trainNumber(),
                request.trainName(),
                request.travelDate(),
                request.source(),
                request.destination(),
                request.travelClass()
        );

        return Map.of(
                "message", "Reservation booked successfully",
                "pnr", pnr
        );
    }

    public record BookingRequest(
            String passengerName,
            String trainNumber,
            String trainName,
            LocalDate travelDate,
            String source,
            String destination,
            String travelClass) {
    }
}

@RestControllerAdvice
class ReservationApiExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(
            IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidInput(
            IllegalArgumentException exception) {
        return Map.of("message", exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(
            java.util.NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleReservationNotFound(
            java.util.NoSuchElementException exception) {
        return Map.of("message", exception.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(
            SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleDatabaseError(SQLException exception) {
        exception.printStackTrace();
        return Map.of("message", "A database error occurred.");
    }
}
