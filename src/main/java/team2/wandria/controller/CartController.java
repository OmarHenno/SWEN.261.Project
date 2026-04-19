package team2.wandria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.wandria.model.AddToCartRequest;
import team2.wandria.model.Cart;
import team2.wandria.model.Customer;
import team2.wandria.model.UpdateCartRequest;
import team2.wandria.model.User;
import team2.wandria.service.AuthServiceInterface;
import team2.wandria.service.CartService;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final AuthServiceInterface authService;

    public CartController(CartService cartService, AuthServiceInterface authService) {
        this.cartService = cartService;
        this.authService = authService;
    }

    private Customer getLoggedInCustomer() {
        User user = authService.getCurrentUser();

        if (user instanceof Customer customer) {
            return customer;
        }

        return null;
    }

    @GetMapping
    public ResponseEntity<?> getCart() {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        return ResponseEntity.ok(cartService.getCartByCustomer(customer));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest request) {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        try {
            Cart cart = cartService.addFlightToCart(customer, request.getFlightNumber(), request.getQuantity());

            if (cart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Flight not found"));
            }

            return ResponseEntity.ok(cart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCart(@RequestBody UpdateCartRequest request) {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        try {
            Cart cart = cartService.updateQuantity(customer, request.getFlightNumber(), request.getQuantity());

            if (cart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Cart item not found"));
            }

            return ResponseEntity.ok(cart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFromCart(@RequestParam String flightNumber) {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        boolean removed = cartService.removeFlightFromCart(customer, flightNumber);

        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Cart item not found"));
        }

        return ResponseEntity.ok(Map.of("message", "Flight removed from cart"));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart() {
        Customer customer = getLoggedInCustomer();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Customer login required"));
        }

        cartService.clearCart(customer);
        return ResponseEntity.ok(Map.of("message", "Cart cleared successfully"));
    }
}