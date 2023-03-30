package patterns.command;

public class TurnOn implements ICommand {
    private IReceiver system;

    public TurnOn(IReceiver system) {
        this.system = system;
    }

    @Override
    public void execute() {
        this.system.turnOn();
    }
}
