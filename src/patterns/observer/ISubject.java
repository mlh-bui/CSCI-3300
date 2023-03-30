package patterns.observer;

/*
 * The interface ISubject declares methods to add, remove and notify observers
 */

public interface ISubject {

    public void add(IObserver o);
    public void remove(IObserver o);
    public void notify(String message); // sends a message to all objects observing the state of this object

}