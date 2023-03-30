package patterns.state;

public class OrderPrepared implements IOrderStatus {

    @Override
    public void setPrevious(Order order) {
        order.setStatus( new OrderPlaced() );
    }

    @Override
    public void setNext(Order order) {
        order.setStatus( new OrderShipped() );
    }

    @Override
    public String getDescription() {
        return "The order has been prepared, awaiting for shipment";
    }

}