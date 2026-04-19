package team2.wandria.model;

public class BookingItem {

    private String flightNumber;
    private int quantity;
    private double finalPrice;

    public BookingItem() {
    }

    public BookingItem(String flightNumber, int quantity, double finalPrice) {
        this.flightNumber = flightNumber;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}