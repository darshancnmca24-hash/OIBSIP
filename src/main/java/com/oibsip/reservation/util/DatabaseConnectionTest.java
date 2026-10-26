
package com.oibsip.reservation.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {

            if (connection.isValid(5)) {
                System.out.println(
                        "SUCCESS: Java connected to the MySQL database!"
                );
            }

        } catch (SQLException exception) {
            System.out.println("Database connection failed.");
            System.out.println("Reason: " + exception.getMessage());
        }
    }
}
