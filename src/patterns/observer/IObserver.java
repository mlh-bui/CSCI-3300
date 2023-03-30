package patterns.observer;

/*
 * The interface IObserver declares the update method to post the notifications received from the subject
 */

public interface IObserver {

    public void update(String id, String message);

}