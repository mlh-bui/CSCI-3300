package patterns.command;

public class RemoteControl implements IInvoker {
    private ICommand command;

    public RemoteControl() { }

    @Override
    public void setCommand(ICommand command) {
        this.command = command;
    }

    @Override
    public void execute() {
        this.command.execute();
    }
}