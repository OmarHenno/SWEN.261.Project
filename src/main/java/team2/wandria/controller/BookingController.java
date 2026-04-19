package team2.wandria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.wandria.model.Booking;
import team2.wandria.model.Customer;
import team2.wandria.model.UpdateBookingRequest;
import team2.wandria.model.User;
import team2.wandria.service.AuthServiceInterface;
import team2.wandria.service.BookingService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final AuthServiceInterface authService;

    public BookingController(BookingService bookingService, AuthServiceInterface authService) {
        this.bookingService = bookingService;
        this.authService = authService;
    }

    private Customer getLoggedInCustomer() {
        User user = authService.getCurrentUser();

        if (user instanceof Customer customer) {
            return customer;
        }

        return null;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout() {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        try {
            Booking booking = bookingService.checkout(customer);
            return ResponseEntity.ok(booking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getBookings() {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        List<Booking> bookings = bookingService.getBookingsByCustomer(customer);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable String id) {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        Booking booking = bookingService.getBookingById(customer, id);

        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Booking not found"));
        }

        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateBooking(@PathVariable String id, @RequestBody UpdateBookingRequest request) {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        try {
            Booking updatedBooking = bookingService.updateBookingStatus(customer, id, request.getStatus());

            if (updatedBooking == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Booking not found"));
            }

            return ResponseEntity.ok(updatedBooking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteBooking(@PathVariable String id) {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        boolean deleted = bookingService.deleteBooking(customer, id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Booking not found"));
        }

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Booking deleted successfully"
        ));
    }
}