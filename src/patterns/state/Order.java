package patterns.state;

public class Order {
    private String id;
    private IOrderStatus status;

    public Order(String id) {
        this.id = id;
        this.status = new OrderPlaced();
    }

    public String getId() {
        return this.id;
    }

    public String getStatus() {
        return "[Order " + this.id + "] " + this.status.getDescription();
    }

    public void setStatus(IOrderStatus status) {
        this.status = status;
    }

    public void setPreviousStatus() {
        this.status.setPrevious(this);
    }

    public void setNextStatus() {
        this.status.setNext(this);
    }
}