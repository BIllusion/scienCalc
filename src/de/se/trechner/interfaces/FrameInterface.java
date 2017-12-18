package de.se.trechner.interfaces;

import de.se.trechner.model.GridActions;
import de.se.trechner.model.ToolbarActions;

/**
 * Definiert die Schnittstellen der Basis GUI-Klasse TRechnerGUI um über diese Zugriff auf einzelne Komponenten der GUI
 * zu erhalten.
 *
 * @author ruess_c
 * @version 2017-12-16
 */
public interface FrameInterface {

    /**
     * Gibt Zugriff auf die definierten Funktionen einer zweigeteilten Ausgabekomponente
     *
     * @return das Interface der zweigeteilten Ausgabekomponente
     * @see de.se.trechner.interfaces.DisplayInterface
     */
    public DisplayInterface getDisplay();

    /**
     * Gibt die ID des momentan fokussierten Objekts zurück
     *
     * @return enthält die ID des momentan fokussierten Objekts
     */
    public String getIdFromFocus();

    /**
     * Gibt Zugriff auf vom ActionsInterface definierte Funktionen der Toolbar
     *
     * @return das Interface um die Funktionen zu nutzen
     * @see de.se.trechner.interfaces.ActionsInterface
     * @see de.se.trechner.view.ToolBar
     */
    public ActionsInterface<ToolbarActions> getToolBar();

    /**
     * Gibt Zugriff auf vom ActionsInterface definierte Funktionen des FuncGrids
     *
     * @return das Interface um die Funktionen zu nutzen
     * @see de.se.trechner.interfaces.ActionsInterface
     * @see de.se.trechner.view.FuncGrid
     */
    public ActionsInterface<GridActions> getFuncGrid();

    /**
     * Gibt Zugriff auf vom ActionsInterface definierte Funktionen des NrGrids
     *
     * @return das Interface um die Funktionen zu nutzen
     * @see de.se.trechner.interfaces.ActionsInterface
     * @see de.se.trechner.view.NrGrid
     */
    public ActionsInterface<GridActions> getNrGrid();

    /**
     * Öffnet das Hilfefenster
     *
     * @see de.se.trechner.view.HelpWindow
     */
    public void showHelp();

    /**
     * Beschreibt ob der aktuelle Fokus auf der großen Inputzeile liegt
     *
     * @return fokussiert
     */
    public boolean isBigLabelFocused();

}
