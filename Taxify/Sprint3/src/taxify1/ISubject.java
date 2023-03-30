package taxify1;

public interface ISubject {

    public void addObserver(IObserver observer);
    public void notifyObserver(String message);

}