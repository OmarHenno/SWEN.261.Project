package team2.wandria.model;

public class User {

    private String username;
    private String password;
    private String email;
    private Role role;

    public User() {
    }

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public boolean matchesPassword(String inputPassword) {
        return password != null && password.equals(inputPassword);
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public boolean isCustomer() {
        return role == Role.CUSTOMER;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Keeping setter because assignment is simple/in-memory
    public void setPassword(String password) {
        this.password = password;
    }

    // No getPassword() on purpose

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}