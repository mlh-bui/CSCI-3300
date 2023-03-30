package patterns.command;

public class TestProgram {

    public static void main(String[] args) {

        // receivers implement and perform a set of actions that are executed when the invoker calls the method execute()

        IReceiver electric = new HeatingSystem("Electric");
        IReceiver aircooler = new CoolingSystem("Electric");
        IReceiver gas = new HeatingSystem("Gas");

        IInvoker control = new RemoteControl();

        control.setCommand(new TurnOn(electric));
        control.execute();
        control.setCommand(new TurnOn(gas));
        control.execute();
        control.setCommand(new TurnOff(electric));
        control.execute();
        control.setCommand(new TurnOff(gas));
        control.execute();
        control.setCommand(new TurnOn(aircooler));
        control.execute();
        control.setCommand(new TurnOff(aircooler));
        control.execute();

        // the remote control acts as invoker, it executes commands on objects of type IReceiver

        // turn on the electric heating system
        // turn on the gas heating system
        // turn off the electric heating system
        // turn off the gas heating system
        // turn on the air cooling system
        // turn off the air cooling system

    }
}