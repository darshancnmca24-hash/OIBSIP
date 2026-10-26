
package com.oibsip.reservation.dao;

import com.oibsip.reservation.model.Reservation;
import com.oibsip.reservation.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ReservationDAO {

    public void save(Reservation reservation) throws SQLException {

        String sql = """
                INSERT INTO reservations
                (pnr, passenger_name, train_number, train_name,
                 travel_date, source, destination, travel_class)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, reservation.getPnr());
            statement.setString(2, reservation.getPassengerName());
            statement.setString(3, reservation.getTrainNumber());
            statement.setString(4, reservation.getTrainName());
            statement.setDate(
                    5,
                    java.sql.Date.valueOf(reservation.getTravelDate())
            );
            statement.setString(6, reservation.getSource());
            statement.setString(7, reservation.getDestination());
            statement.setString(8, reservation.getTravelClass());

            statement.executeUpdate();
        }
    }
    public Reservation findByPnr(String pnr) throws SQLException {
        String sql = """
                SELECT pnr, passenger_name, train_number, train_name,
                       travel_date, source, destination, travel_class
                FROM reservations
                WHERE pnr = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Reservation(
                            resultSet.getString("pnr"),
                            resultSet.getString("passenger_name"),
                            resultSet.getString("train_number"),
                            resultSet.getString("train_name"),
                            resultSet.getDate("travel_date").toLocalDate(),
                            resultSet.getString("source"),
                            resultSet.getString("destination"),
                            resultSet.getString("travel_class")
                    );
                }
            }
        }

        return null;
    }
    public boolean cancelByPnr(String pnr) throws SQLException {
        String sql = "DELETE FROM reservations WHERE pnr = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}
