package patterns.strategy.find;

public interface IItem extends Cloneable {

    public String getId();

    public int getDistance();

    public boolean isAvailable();

    public void setAvailable(boolean available);

    public String toString();

    public Object clone() throws CloneNotSupportedException;

}