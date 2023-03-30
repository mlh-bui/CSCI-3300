package patterns.command;

public class TurnOff implements ICommand {
    private IReceiver system;

    public TurnOff(IReceiver system) {
        this.system = system;
    }

    @Override
    public void execute() {
        this.system.turnOff();
    }
}