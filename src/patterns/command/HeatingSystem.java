package patterns.command;

public class HeatingSystem implements IReceiver {
    private String type;

    public HeatingSystem(String type) {
        this.type = type;
    }

    @Override
    public void turnOn() {
        System.out.println("Turning on " + this.type + " heating");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off " + this.type + " heating");
    }
}