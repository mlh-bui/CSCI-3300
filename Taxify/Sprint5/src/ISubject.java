// Sprint 5 Project: Taxify
// Marissa Bui - CSCI 3300

public interface ISubject {

    void addObserver(IObserver observer);
    void notifyObserver(String message);

} // interface ISubject