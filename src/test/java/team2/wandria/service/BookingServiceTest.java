package team2.wandria.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team2.wandria.model.Booking;
import team2.wandria.model.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {

    private BookingService bookingService;
    private CartService cartService;
    private FlightService flightService;
    private Customer customer;

    @BeforeEach
    void setUp() {
        flightService = new FlightService();
        cartService = new CartService(flightService);
        bookingService = new BookingService(cartService, flightService);

        customer = new Customer("customer1", "1234", "c1@test.com", "C001");
    }

    @Test
    void checkoutShouldCreateBookingAndClearCart() {
        cartService.addFlightToCart(customer, "EK202", 1);

        Booking booking = bookingService.checkout(customer);

        assertNotNull(booking);
        assertEquals("CONFIRMED", booking.getStatus());
        assertFalse(booking.getItems().isEmpty());
        assertTrue(cartService.getCartByCustomer(customer).getItems().isEmpty());
    }

    @Test
    void checkoutWithEmptyCartShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bookingService.checkout(customer));
    }

    @Test
    void getBookingsByCustomerShouldReturnCustomerBookings() {
        cartService.addFlightToCart(customer, "EK202", 1);
        bookingService.checkout(customer);

        List<Booking> bookings = bookingService.getBookingsByCustomer(customer);

        assertEquals(1, bookings.size());
    }

    @Test
    void getBookingByIdShouldReturnCorrectBooking() {
        cartService.addFlightToCart(customer, "EK202", 1);
        Booking createdBooking = bookingService.checkout(customer);

        Booking foundBooking = bookingService.getBookingById(customer, createdBooking.getBookingId());

        assertNotNull(foundBooking);
        assertEquals(createdBooking.getBookingId(), foundBooking.getBookingId());
    }

    @Test
    void updateBookingStatusShouldCancelBooking() {
        cartService.addFlightToCart(customer, "EK202", 1);
        Booking booking = bookingService.checkout(customer);

        Booking updatedBooking = bookingService.updateBookingStatus(customer, booking.getBookingId(), "CANCELLED");

        assertNotNull(updatedBooking);
        assertEquals("CANCELLED", updatedBooking.getStatus());
    }

    @Test
    void updateBookingStatusShouldUncancelBooking() {
        cartService.addFlightToCart(customer, "EK202", 1);
        Booking booking = bookingService.checkout(customer);

        bookingService.updateBookingStatus(customer, booking.getBookingId(), "CANCELLED");
        Booking updatedBooking = bookingService.updateBookingStatus(customer, booking.getBookingId(), "CONFIRMED");

        assertNotNull(updatedBooking);
        assertEquals("CONFIRMED", updatedBooking.getStatus());
    }

    @Test
    void deleteBookingShouldRemoveBooking() {
        cartService.addFlightToCart(customer, "EK202", 1);
        Booking booking = bookingService.checkout(customer);

        boolean deleted = bookingService.deleteBooking(customer, booking.getBookingId());

        assertTrue(deleted);
        assertNull(bookingService.getBookingById(customer, booking.getBookingId()));
    }
}