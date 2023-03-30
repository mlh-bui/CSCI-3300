package patterns.command;

public interface IInvoker {

    public void setCommand(ICommand command);
    public void execute();

}