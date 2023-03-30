package patterns.state;

public class OrderDelivered implements IOrderStatus {

    @Override
    public void setPrevious(Order order) {
        order.setStatus( new OrderShipped() );
    }

    @Override
    public void setNext(Order order) { }

    @Override
    public String getDescription() {
        return "The order has been delivered to the customer";
    }

}