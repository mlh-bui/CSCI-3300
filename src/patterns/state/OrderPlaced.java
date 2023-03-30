package patterns.state;

public class OrderPlaced implements IOrderStatus {

    @Override
    public void setPrevious(Order order) { }

    @Override
    public void setNext(Order order) {
        order.setStatus( new OrderPrepared() );
    }

    @Override
    public String getDescription() {
        return "The order has been placed, awaiting for preparation";
    }

}