package team2.wandria.service;

import org.springframework.stereotype.Service;
import team2.wandria.model.Booking;
import team2.wandria.model.BookingItem;
import team2.wandria.model.Cart;
import team2.wandria.model.CartItem;
import team2.wandria.model.Customer;
import team2.wandria.model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();
    private final CartService cartService;
    private final FlightService flightService;

    public BookingService(CartService cartService, FlightService flightService) {
        this.cartService = cartService;
        this.flightService = flightService;
    }

    public Booking checkout(Customer customer) {
        Cart cart = cartService.getCartByCustomer(customer);

        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setCustomerId(customer.getCustomerId());
        booking.setStatus("CONFIRMED");

        List<BookingItem> bookingItems = new ArrayList<>();
        double total = 0.0;

        for (CartItem cartItem : cart.getItems()) {
            Flight flight = flightService.findByFlightNumber(cartItem.getFlightNumber());

            if (flight == null) {
                throw new IllegalArgumentException("Flight not found: " + cartItem.getFlightNumber());
            }

            if (flight.getSeatsAvailable() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("Not enough seats available for flight " + cartItem.getFlightNumber());
            }

            flight.setSeats(flight.getSeatsAvailable() - cartItem.getQuantity());

            BookingItem bookingItem = new BookingItem(
                    cartItem.getFlightNumber(),
                    cartItem.getQuantity(),
                    cartItem.getPrice()
            );

            bookingItems.add(bookingItem);
            total += cartItem.getPrice() * cartItem.getQuantity();
        }

        booking.setItems(bookingItems);
        booking.setTotalAmount(total);

        bookings.add(booking);
        cartService.clearCart(customer);

        return booking;
    }

    public List<Booking> getBookingsByCustomer(Customer customer) {
        List<Booking> customerBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getCustomerId().equals(customer.getCustomerId())) {
                customerBookings.add(booking);
            }
        }

        return customerBookings;
    }

    public Booking getBookingById(Customer customer, String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)
                    && booking.getCustomerId().equals(customer.getCustomerId())) {
                return booking;
            }
        }

        return null;
    }

    public Booking updateBookingStatus(Customer customer, String bookingId, String newStatus) {
        if (newStatus == null || newStatus.isBlank()) {
            throw new IllegalArgumentException("Status is required");
        }

        Booking booking = getBookingById(customer, bookingId);

        if (booking == null) {
            return null;
        }

        String currentStatus = booking.getStatus().toUpperCase();
        String requestedStatus = newStatus.toUpperCase();

        if (currentStatus.equals(requestedStatus)) {
            return booking;
        }

        if (requestedStatus.equals("CANCELLED")) {
            for (BookingItem item : booking.getItems()) {
                Flight flight = flightService.findByFlightNumber(item.getFlightNumber());

                if (flight != null) {
                    flight.setSeats(flight.getSeatsAvailable() + item.getQuantity());
                }
            }
        } else if (requestedStatus.equals("CONFIRMED")) {
            for (BookingItem item : booking.getItems()) {
                Flight flight = flightService.findByFlightNumber(item.getFlightNumber());

                if (flight == null) {
                    throw new IllegalArgumentException("Flight not found: " + item.getFlightNumber());
                }

                if (flight.getSeatsAvailable() < item.getQuantity()) {
                    throw new IllegalArgumentException("Not enough seats available to restore booking");
                }

                flight.setSeats(flight.getSeatsAvailable() - item.getQuantity());
            }
        } else {
            throw new IllegalArgumentException("Invalid status");
        }

        booking.setStatus(requestedStatus);
        return booking;
    }

    public boolean deleteBooking(Customer customer, String bookingId) {
        Booking booking = getBookingById(customer, bookingId);

        if (booking == null) {
            return false;
        }

        return bookings.remove(booking);
    }
}