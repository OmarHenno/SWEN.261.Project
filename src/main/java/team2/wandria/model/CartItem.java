package team2.wandria.model;

public class CartItem {

    private String flightNumber;
    private int quantity;
    private double price;

    public CartItem() {
    }

    public CartItem(String flightNumber, int quantity, double price) {
        this.flightNumber = flightNumber;
        this.quantity = quantity;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
