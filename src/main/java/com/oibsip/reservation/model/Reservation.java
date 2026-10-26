
package com.oibsip.reservation.model;

import java.time.LocalDate;

public class Reservation {

    private String pnr;
    private String passengerName;
    private String trainNumber;
    private String trainName;
    private LocalDate travelDate;
    private String source;
    private String destination;
    private String travelClass;

    public Reservation(
            String pnr,
            String passengerName,
            String trainNumber,
            String trainName,
            LocalDate travelDate,
            String source,
            String destination,
            String travelClass) {

        this.pnr = pnr;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.travelDate = travelDate;
        this.source = source;
        this.destination = destination;
        this.travelClass = travelClass;
    }

    public String getPnr() { return pnr; }
    public String getPassengerName() { return passengerName; }
    public String getTrainNumber() { return trainNumber; }
    public String getTrainName() { return trainName; }
    public LocalDate getTravelDate() { return travelDate; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getTravelClass() { return travelClass; }
}
