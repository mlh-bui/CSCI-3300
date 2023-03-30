package patterns.observer;

import java.util.List;
import java.util.ArrayList;

public class WeatherStation implements ISubject {
    private String id;
    private List<IObserver> displays;

    public WeatherStation(String id) {
        this.id = id;
        this.displays = new ArrayList<IObserver>();
    }

    @Override
    public void add(IObserver o) {
        // adds a new observer
        this.displays.add(o);
    }

    @Override
    public void remove(IObserver o) {
        // removes an observer
        this.displays.remove(o);
    }

    @Override
    public void notify(String message) {
        System.out.println(this.id);

        for(IObserver o :this.displays) {
            o.update(this.id, message);
        }
    }
}