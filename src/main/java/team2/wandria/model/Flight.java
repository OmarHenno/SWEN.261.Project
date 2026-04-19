package team2.wandria.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {

    private String flightNumber;
    private String destination;
    private String category;
    private LocalDateTime departureTime;
    private int seats;
    private double price;

    public Flight(String flightNumber, String destination, String category,
                  LocalDateTime departureTime, int seats, double price) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.category = category;
        this.departureTime = departureTime;
        this.seats = seats;
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getSeatsAvailable() {
        return seats;
    }

    public double getPrice() {
        return price;
    }

    public String getFormattedDepartureTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return departureTime.format(formatter);
    }

    public String getAvailabilityStatus() {
        if (seats > 0) {
            return "Available";
        } else {
            return "Sold Out";
        }
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Flight() {
        // Empty constructor needed for testing and Spring Boot
    }
}