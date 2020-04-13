package observer;

public abstract class Observer {
    protected ObserverSubject subject;
    public abstract void update();
}