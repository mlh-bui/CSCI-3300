package patterns.strategy.find;

public class Item implements IItem {
    private String id;
    private int distance;
    private boolean available;

    public Item(String id, int distance, boolean available) {
        this.id = id;
        this.distance = distance;
        this.available = available;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    @Override
    public boolean isAvailable() {
        return this.available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return this.id + " " + this.distance + " " + this.available;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}