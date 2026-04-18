package team2.wandria.model;

public class Administrator extends User {

    private String adminId;

    public Administrator() {
        super();
    }

    public Administrator(String username, String password, String email, String adminId) {
        super(username, password, email, Role.ADMIN);
        this.adminId = adminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}