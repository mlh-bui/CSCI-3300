// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300
package v1;

public interface ISubject {

    void addObserver(IObserver observer);
    void notifyObserver(String message);

} // interface ISubject