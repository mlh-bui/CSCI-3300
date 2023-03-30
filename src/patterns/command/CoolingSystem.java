package patterns.command;

public class CoolingSystem implements IReceiver {
    private String type;

    public CoolingSystem(String type) {
        // sets the cooling system type
        this.type = type;

    }

    @Override
    public void turnOn() {
        // turns on the cooling system
        System.out.println("Turning on " + this.type + " cooling");
    }

    @Override
    public void turnOff() {
        // turns off the cooling system
        System.out.println("Turning off " + this.type + " cooling");
    }
}