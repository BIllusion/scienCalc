package de.se.trechner.interfaces;


public interface ActionsInterface<T> {

    public void requestFocus(T obj);

    public void fireActionEvent(T obj);

    public void releaseActionEvent(T obj);
}
