# OASIS INFOBYTE - Online Reservation System

## Project Overview
A Java Swing application for booking, searching, and cancelling train reservations using JDBC and MySQL.

## Technologies
- Java 17
- Java Swing
- JDBC
- MySQL
- Maven
- JUnit 5

## Features
- Login validation
- Book reservations and generate PNR
- Search reservations by PNR
- Fetch details before cancellation
- Confirm cancellation before deleting
- Validate journey details

## Demo Login
- Username: admin
- Password: admin123

## Database Setup
Create the oibsip_reservation database and required reservations table in MySQL. Set the OIBSIP_DB_PASSWORD environment variable to your own database password. Never commit your actual password.

## Build and Test
Run: mvn clean test

## Run Application
Set OIBSIP_DB_PASSWORD in PowerShell, then run the Swing main class com.oibsip.reservation.swing.ReservationSwingApplication using Maven.

## Internship
Organization: OASIS INFOBYTE
Track: Java Development
Task: Online Reservation System
