package team2.wandria.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team2.wandria.model.Cart;
import team2.wandria.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    private CartService cartService;
    private FlightService flightService;
    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    void setUp() {
        flightService = new FlightService();
        cartService = new CartService(flightService);

        customer1 = new Customer("customer1", "1234", "c1@test.com", "C001");
        customer2 = new Customer("customer2", "1234", "c2@test.com", "C002");
    }

    @Test
    void getCartByCustomerShouldCreateSeparateCartForEachCustomer() {
        Cart cart1 = cartService.getCartByCustomer(customer1);
        Cart cart2 = cartService.getCartByCustomer(customer2);

        assertNotNull(cart1);
        assertNotNull(cart2);
        assertNotEquals(cart1.getCustomerId(), cart2.getCustomerId());
    }

    @Test
    void addFlightToCartShouldAddItem() {
        Cart cart = cartService.addFlightToCart(customer1, "EK202", 1);

        assertNotNull(cart);
        assertEquals(1, cart.getItems().size());
        assertEquals("EK202", cart.getItems().get(0).getFlightNumber());
    }

    @Test
    void addFlightToCartWithInvalidFlightShouldReturnNull() {
        Cart cart = cartService.addFlightToCart(customer1, "FAKE999", 1);

        assertNull(cart);
    }

    @Test
    void addFlightToCartWithInvalidQuantityShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                cartService.addFlightToCart(customer1, "EK202", 0));
    }

    @Test
    void updateQuantityShouldChangeItemQuantity() {
        cartService.addFlightToCart(customer1, "EK202", 1);

        Cart updatedCart = cartService.updateQuantity(customer1, "EK202", 3);

        assertNotNull(updatedCart);
        assertEquals(3, updatedCart.getItems().get(0).getQuantity());
    }

    @Test
    void updateQuantityWithInvalidQuantityShouldThrowException() {
        cartService.addFlightToCart(customer1, "EK202", 1);

        assertThrows(IllegalArgumentException.class, () ->
                cartService.updateQuantity(customer1, "EK202", 0));
    }

    @Test
    void removeFlightFromCartShouldRemoveItem() {
        cartService.addFlightToCart(customer1, "EK202", 1);

        boolean removed = cartService.removeFlightFromCart(customer1, "EK202");

        assertTrue(removed);
        assertTrue(cartService.getCartByCustomer(customer1).getItems().isEmpty());
    }

    @Test
    void clearCartShouldRemoveAllItems() {
        cartService.addFlightToCart(customer1, "EK202", 1);
        cartService.addFlightToCart(customer1, "EK303", 2);

        cartService.clearCart(customer1);

        assertTrue(cartService.getCartByCustomer(customer1).getItems().isEmpty());
    }
}
