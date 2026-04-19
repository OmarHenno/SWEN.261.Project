package team2.wandria.service;

import org.springframework.stereotype.Service;
import team2.wandria.model.Administrator;
import team2.wandria.model.Customer;
import team2.wandria.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthService implements AuthServiceInterface {

    private final List<User> users = new ArrayList<>();
    private User currentUser;

    public AuthService() {
        users.add(new Customer("customer1", "1234", "customer1@wandria.com", "C001"));
        users.add(new Customer("customer2", "1234", "customer2@wandria.com", "C002"));
        users.add(new Customer("customer3", "1234", "customer3@wandria.com", "C003"));

        users.add(new Administrator("admin1", "admin", "admin1@wandria.com", "A001"));
        users.add(new Administrator("admin2", "admin", "admin2@wandria.com", "A002"));

    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return null;
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.matchesPassword(password)) {
                currentUser = user;
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean logout() {
        if (currentUser != null) {
            currentUser = null;
            return true;
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }

    @Override
    public boolean isCustomer() {
        return currentUser != null && currentUser.isCustomer();
    }

    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.unmodifiableList(users);
    }
}