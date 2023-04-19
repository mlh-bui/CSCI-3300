package v2;

public interface ISubject {

    void addObserver(IObserver observer);
    void notifyObserver(String message);

}