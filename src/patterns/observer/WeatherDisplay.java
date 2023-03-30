package patterns.observer;

public class WeatherDisplay implements IObserver {
    private String id;

    public WeatherDisplay(String id) {
        this.id = id;
    }

    @Override
    public void update(String id, String message) {
        System.out.println(this.id + " <- [" + id + "] " +  message);
    }
}