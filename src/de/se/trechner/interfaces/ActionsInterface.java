package de.se.trechner.interfaces;

/**
 * Definiert die Schnittstellen um von extern ein Event der Komponente ausführen zu können.
 *
 * @author ruess_c
 * @version 2017-12-16
 */
public interface ActionsInterface<T> {


    /**
     * Anfrage um den Fokus auf die Komponente zu setzen
     *
     * @param obj ein Identifizierer für eine Komponente
     */
    public void requestFocus(T obj);

    /**
     * Führt eine Aktion innerhalb der Komponente aus
     *
     * @param obj ein Identifizierer für eine Komponente
     */
    public void fireActionEvent(T obj);

    /**
     * Benachrichtigt das die Aktion nicht mehr ausgeführt wird.
     *
     * @param obj ein Identifizierer für eine Komponente
     */
    public void releaseActionEvent(T obj);
}
