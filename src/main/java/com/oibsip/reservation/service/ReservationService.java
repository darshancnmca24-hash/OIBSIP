
package com.oibsip.reservation.service;

import com.oibsip.reservation.model.Reservation;

import java.time.LocalDate;
import java.util.UUID;

public class ReservationService {

    public String generatePnr() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 10)
                .toUpperCase();
    }

    public void validateReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException(
                    "Reservation cannot be null."
            );
        }

        requireText(reservation.getPassengerName(), "Passenger name");
        requireText(reservation.getTrainNumber(), "Train number");
        requireText(reservation.getTrainName(), "Train name");
        requireText(reservation.getSource(), "Source");
        requireText(reservation.getDestination(), "Destination");
        requireText(reservation.getTravelClass(), "Travel class");

        if (reservation.getTravelDate() == null) {
            throw new IllegalArgumentException(
                    "Travel date is required."
            );
        }

        if (reservation.getTravelDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Travel date cannot be in the past."
            );
        }

        if (reservation.getSource().trim()
                .equalsIgnoreCase(reservation.getDestination().trim())) {
            throw new IllegalArgumentException(
                    "Source and destination must be different."
            );
        }
    }

    private void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " is required."
            );
        }
    }
}
