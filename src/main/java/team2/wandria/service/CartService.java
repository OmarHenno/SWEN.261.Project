package team2.wandria.service;

import org.springframework.stereotype.Service;
import team2.wandria.model.Cart;
import team2.wandria.model.CartItem;
import team2.wandria.model.Customer;
import team2.wandria.model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private final List<Cart> carts = new ArrayList<>();
    private final FlightService flightService;

    public CartService(FlightService flightService) {
        this.flightService = flightService;
    }

    public Cart getCartByCustomer(Customer customer) {
        for (Cart cart : carts) {
            if (cart.getCustomerId().equals(customer.getCustomerId())) {
                return cart;
            }
        }

        Cart newCart = new Cart(UUID.randomUUID().toString(), customer.getCustomerId());
        carts.add(newCart);
        return newCart;
    }

    public Cart addFlightToCart(Customer customer, String flightNumber, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        Cart cart = getCartByCustomer(customer);
        Flight flight = flightService.findByFlightNumber(flightNumber);

        if (flight == null) {
            return null;
        }

        if (flight.getSeatsAvailable() <= 0) {
            throw new IllegalArgumentException("Flight is sold out");
        }

        for (CartItem item : cart.getItems()) {
            if (item.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                item.setQuantity(item.getQuantity() + quantity);
                return cart;
            }
        }

        cart.getItems().add(new CartItem(flightNumber, quantity, flight.getPrice()));
        return cart;
    }

    public Cart updateQuantity(Customer customer, String flightNumber, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        Cart cart = getCartByCustomer(customer);

        for (CartItem item : cart.getItems()) {
            if (item.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                item.setQuantity(quantity);
                return cart;
            }
        }

        return null;
    }

    public boolean removeFlightFromCart(Customer customer, String flightNumber) {
        Cart cart = getCartByCustomer(customer);
        return cart.getItems().removeIf(item ->
                item.getFlightNumber().equalsIgnoreCase(flightNumber));
    }

    public void clearCart(Customer customer) {
        Cart cart = getCartByCustomer(customer);
        cart.getItems().clear();
    }
}