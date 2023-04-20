// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300
package v2;

public interface ISubject {

    void addObserver(IObserver observer);
    void notifyObserver(String message);

} // interface ISubject