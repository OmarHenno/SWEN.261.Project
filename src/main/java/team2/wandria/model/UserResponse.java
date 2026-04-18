package team2.wandria.model;

public class UserResponse {

    private String username;
    private String email;
    private String role;

    public UserResponse() {
    }

    public UserResponse(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}