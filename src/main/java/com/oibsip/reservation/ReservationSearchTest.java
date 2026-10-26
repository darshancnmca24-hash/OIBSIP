
package com.oibsip.reservation;

import com.oibsip.reservation.dao.ReservationDAO;
import com.oibsip.reservation.model.Reservation;

import java.sql.SQLException;

public class ReservationSearchTest {

    public static void main(String[] args) {
        ReservationDAO reservationDAO = new ReservationDAO();

        try {
            Reservation reservation =
                    reservationDAO.findByPnr("93DDAB494A");

            if (reservation != null) {
                System.out.println("Reservation found!");
                System.out.println("PNR: " + reservation.getPnr());
                System.out.println("Passenger: " + reservation.getPassengerName());
                System.out.println("Train: " + reservation.getTrainName());
                System.out.println("Travel date: " + reservation.getTravelDate());
                System.out.println("From: " + reservation.getSource());
                System.out.println("To: " + reservation.getDestination());
                System.out.println("Class: " + reservation.getTravelClass());
            } else {
                System.out.println("No reservation found for this PNR.");
            }

        } catch (SQLException exception) {
            System.out.println("Search failed: " + exception.getMessage());
        }
    }
}
