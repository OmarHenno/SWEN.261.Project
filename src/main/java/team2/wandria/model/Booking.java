package team2.wandria.model;

import java.util.ArrayList;
import java.util.List;

public class Booking {

    private String bookingId;
    private String customerId;
    private String status;
    private double totalAmount;
    private List<BookingItem> items;

    public Booking() {
        this.items = new ArrayList<>();
    }

    public Booking(String bookingId, String customerId, String status, double totalAmount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.items = new ArrayList<>();
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<BookingItem> getItems() {
        return items;
    }

    public void setItems(List<BookingItem> items) {
        this.items = items;
    }
}