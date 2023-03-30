package patterns.state;

public interface IOrderStatus {

    public void setPrevious(Order o);
    public void setNext(Order o);
    public String getDescription();

}