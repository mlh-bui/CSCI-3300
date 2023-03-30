package patterns.observer;

public class TestProgram {

    /*
     * The Observer pattern defines a one to many association, when the subject changes its state, it sends a notification to its attached objects
     *
     * The interface ISubject declares methods to add, remove and notify observers, the interface IObject declares the method update to post the notifications received from the subject
     *
     * The class WeatherStation is the subject and WeatherDisplay is the observer
     */

    public static void main(String[] args) {
        WeatherStation m1 = new WeatherStation("Madrid Center");
        WeatherStation m2 = new WeatherStation("Madrid North");
        WeatherStation m3 = new WeatherStation("Madrid South");
        WeatherStation m4 = new WeatherStation("Madrid East");
        WeatherStation m5 = new WeatherStation("Madrid West");

        WeatherDisplay d1 = new WeatherDisplay("Display 1");
        WeatherDisplay d2 = new WeatherDisplay("Display 2");
        WeatherDisplay d3 = new WeatherDisplay("Display 3");

        m1.add(d1); // add and remove observers at runtime. Can decide which display and observers are controlled
        m1.add(d2);
        m2.add(d2);
        m2.add(d3);
        m3.add(d3);
        m4.add(d1);
        m5.add(d1);

        m1.notify("Cold and rainy in Madrid center");
        m2.notify("Rain shower in Madrid north");
        m1.notify("Cold and windy in Madrid center");
        m3.notify("Sunny in Madrid south");
        m4.notify("Very cold in Madrid east");
        m5.notify("Rainy in Madrid west");

        m1.remove(d2);

        m1.notify("Madrid center is sunny");

    }

}