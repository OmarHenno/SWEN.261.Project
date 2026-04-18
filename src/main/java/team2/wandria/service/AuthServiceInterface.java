package team2.wandria.service;

import team2.wandria.model.User;

import java.util.List;

public interface AuthServiceInterface {

    User login(String username, String password);

    boolean logout();

    User getCurrentUser();

    boolean isAdmin();

    boolean isCustomer();

    boolean isLoggedIn();

    List<User> getAllUsers();
}