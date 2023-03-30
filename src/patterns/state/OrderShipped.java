package patterns.state;

public class OrderShipped implements IOrderStatus {

    @Override
    public void setPrevious(Order order) {
        order.setStatus( new OrderPrepared() );
    }

    @Override
    public void setNext(Order order) {
        order.setStatus( new OrderDelivered() );
    }

    @Override
    public String getDescription() {
        return "The order has been shipped";
    }

}