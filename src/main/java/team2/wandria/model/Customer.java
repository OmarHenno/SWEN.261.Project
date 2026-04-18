package team2.wandria.model;

public class Customer extends User {

    private String customerId;

    public Customer() {
        super();
    }

    public Customer(String username, String password, String email, String customerId) {
        super(username, password, email, Role.CUSTOMER);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}