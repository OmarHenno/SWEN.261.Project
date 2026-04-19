package team2.wandria.model;

public class UpdateBookingRequest {

    private String status;

    public UpdateBookingRequest() {
    }

    public UpdateBookingRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}