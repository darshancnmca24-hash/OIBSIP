
package com.oibsip.reservation;

import com.oibsip.reservation.dao.ReservationDAO;
import com.oibsip.reservation.model.Reservation;
import com.oibsip.reservation.service.ReservationBookingService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ReservationBookingService bookingService =
            new ReservationBookingService();

    private static final ReservationDAO reservationDAO =
            new ReservationDAO();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== ONLINE RESERVATION SYSTEM =====");
            System.out.println("1. Book Reservation");
            System.out.println("2. Search Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> bookReservation();
                    case "2" -> searchReservation();
                    case "3" -> cancelReservation();
                    case "4" -> {
                        running = false;
                        System.out.println("Thank you for using the system!");
                    }
                    default -> System.out.println("Invalid choice. Please enter 1 to 4.");
                }
            } catch (SQLException exception) {
                System.out.println("Database error: " + exception.getMessage());
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid input: " + exception.getMessage());
            }
        }

        scanner.close();
    }

    private static void bookReservation() throws SQLException {
        System.out.println("\n--- Book Reservation ---");

        System.out.print("Passenger name: ");
String passengerName = scanner.nextLine().trim();

if (passengerName.isEmpty()) {
    System.out.println("Passenger name cannot be empty.");
    return;
}

        System.out.print("Train number: ");
String trainNumber = scanner.nextLine().trim();

if (trainNumber.isEmpty()) {
    System.out.println("Train number cannot be empty.");
    return;
}

        System.out.print("Train name: ");
String trainName = scanner.nextLine().trim();

if (trainName.isEmpty()) {
    System.out.println("Train name cannot be empty.");
    return;
}

        
        LocalDate travelDate;

try {
    System.out.print("Travel date (YYYY-MM-DD): ");
    travelDate = LocalDate.parse(scanner.nextLine().trim());
} catch (java.time.format.DateTimeParseException exception) {
    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
    return;
}

        System.out.print("Source station: ");
String source = scanner.nextLine().trim();

if (source.isEmpty()) {
    System.out.println("Source station cannot be empty.");
    return;
}

        System.out.print("Destination station: ");
String destination = scanner.nextLine().trim();

if (destination.isEmpty()) {
    System.out.println("Destination station cannot be empty.");
    return;
}

        System.out.print("Travel class: ");
String travelClass = scanner.nextLine().trim();

if (travelClass.isEmpty()) {
    System.out.println("Travel class cannot be empty.");
    return;
}

        String pnr = bookingService.bookReservation(
                passengerName,
                trainNumber,
                trainName,
                travelDate,
                source,
                destination,
                travelClass
        );

        System.out.println("\nReservation booked successfully!");
        System.out.println("Your PNR is: " + pnr);
    }

    private static void searchReservation() throws SQLException {
        System.out.println("\n--- Search Reservation ---");
        System.out.print("Enter PNR: ");

        String pnr = scanner.nextLine().trim();
        Reservation reservation = reservationDAO.findByPnr(pnr);

        if (reservation == null) {
            System.out.println("No reservation found for this PNR.");
            return;
        }

        System.out.println("\n--- Reservation Details ---");
        System.out.println("PNR: " + reservation.getPnr());
        System.out.println("Passenger: " + reservation.getPassengerName());
        System.out.println("Train: " + reservation.getTrainName());
        System.out.println("Train number: " + reservation.getTrainNumber());
        System.out.println("Travel date: " + reservation.getTravelDate());
        System.out.println("Source: " + reservation.getSource());
        System.out.println("Destination: " + reservation.getDestination());
        System.out.println("Class: " + reservation.getTravelClass());
    }

    private static void cancelReservation() throws SQLException {
        System.out.println("\n--- Cancel Reservation ---");
        System.out.print("Enter PNR to cancel: ");

        String pnr = scanner.nextLine().trim();

        Reservation reservation = reservationDAO.findByPnr(pnr);

        if (reservation == null) {
            System.out.println("No reservation found for this PNR.");
            return;
        }

        System.out.println("Passenger: " + reservation.getPassengerName());
        System.out.print("Confirm cancellation? (yes/no): ");

        String confirmation = scanner.nextLine().trim();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Cancellation aborted.");
            return;
        }

        boolean cancelled = reservationDAO.cancelByPnr(pnr);

        if (cancelled) {
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Reservation could not be cancelled.");
        }
    }
}
