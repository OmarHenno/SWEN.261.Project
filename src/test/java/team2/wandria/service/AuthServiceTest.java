package team2.wandria.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team2.wandria.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
    }

    @Test
    void loginWithValidCustomerCredentialsShouldReturnUser() {
        User user = authService.login("customer1", "1234");

        assertNotNull(user);
        assertEquals("customer1", user.getUsername());
        assertTrue(authService.isCustomer());
    }


    @Test
    void loginWithValidAdminCredentialsShouldReturnAdmin() {
        User user = authService.login("admin1", "admin");

        assertNotNull(user);
        assertEquals("admin1", user.getUsername());
        assertTrue(authService.isAdmin());
    }

    @Test
    void loginWithInvalidCredentialsShouldReturnNull() {
        User user = authService.login("customer1", "wrong");

        assertNull(user);
        assertFalse(authService.isLoggedIn());
    }

    @Test
    void logoutAfterLoginShouldReturnTrue() {
        authService.login("customer1", "1234");

        boolean result = authService.logout();

        assertTrue(result);
        assertNull(authService.getCurrentUser());
    }

    @Test
    void logoutWithoutLoginShouldReturnFalse() {
        boolean result = authService.logout();

        assertFalse(result);
    }

    @Test
    void getCurrentUserShouldReturnLoggedInUser() {
        authService.login("customer2", "1234");

        User currentUser = authService.getCurrentUser();

        assertNotNull(currentUser);
        assertEquals("customer2", currentUser.getUsername());
    }
}