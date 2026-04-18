package team2.wandria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team2.wandria.model.LoginRequest;
import team2.wandria.model.User;
import team2.wandria.model.UserResponse;
import team2.wandria.service.AuthServiceInterface;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceInterface authService;

    public AuthController(AuthServiceInterface authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getUsername(), request.getPassword());

        if (user == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        return ResponseEntity.ok(UserResponse.fromUser(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        boolean loggedOut = authService.logout();

        Map<String, Object> response = new HashMap<>();
        response.put("success", loggedOut);
        response.put("message", loggedOut
                ? "Logged out successfully"
                : "No user is currently logged in");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser() {
        User currentUser = authService.getCurrentUser();

        if (currentUser == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No user is currently logged in");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(UserResponse.fromUser(currentUser));
    }
}