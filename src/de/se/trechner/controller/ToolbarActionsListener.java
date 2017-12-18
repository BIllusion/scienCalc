package de.se.trechner.controller;

import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.AngleMode;
import de.se.trechner.model.LangModel;
import de.se.trechner.model.NumInput;
import de.se.trechner.model.ToolbarActions;
import de.se.trechner.view.HelpWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

/**
 * Diese Klasse wertet Nutzereingaben aus und steuert damit die Modellklasse NumInput.
 * Sie ist als Singleton realisiert.
 * 
 * @author wojke_n
 * @author ruess_c
 * @version 2017-12-16
 * @see de.se.trechner.model.NumInput
 */
public class ToolbarActionsListener implements EventHandler<ActionEvent> {

    private static ToolbarActionsListener ownInstance;
    private FrameInterface fi;

    /**
     * Konstruktor erstellt den Zugriff auf die GUI um dort Aktionen auszuführen
     *
     * @param fi das Frameinterface ermöglicht den Zugriff auf die GUI
     */
    private ToolbarActionsListener(FrameInterface fi) {
        this.fi = fi;
    }

    /**
     * Ermittelt welcher Button aus der Toolbar gedrückt wurde und bestimmt welche Aktion ausgeführt wird.
     *
     * @param e ermögicht die Unterscheidung zwischen den einzelnen Aktionen und enthält den ToolbarActionsCommand
     * @see de.se.trechner.view.ToolBar
     * @see de.se.trechner.model.ToolbarActions
     */
    @Override
    public void handle(ActionEvent e) {
        Hyperlink hl = ((Hyperlink) e.getSource());
        String cmdID = hl.getId();
        ToolbarActions ta = ToolbarActions.valueOf(cmdID);
        NumInput numInput = NumInput.getInstance(null);

        switch (ta) {
            case TDEG:
            	numInput.changeMode(AngleMode.RAD);
                break;
            case TRAD:
            	numInput.changeMode(AngleMode.GRAD);
                break;
            case TGRAD:
            	numInput.changeMode(AngleMode.DEG);
                break;
            case TFE:
            	fi.getDisplay().setBigMsgBox(numInput.changeFE());
                break;
            case THELP:
                HelpWindow.openHelpInBrowser();
                break;
            case TMS:
            	numInput.memorySave();
                break;
            case TMR:
            	fi.getDisplay().setBigMsgBox(numInput.memoryRecall());
                break;
            case TMC:
            	numInput.memoryClear();
                break;
            default:
                System.out.println("Aktion für Toolbar nicht gefunden");
                break;
        }
        hl.setId(ta.getNextGroupItem().toString());
        hl.setText(LangModel.getInstance().getKeyCaption(ta.getNextGroupItem().toString()));
        hl.setAccessibleText(LangModel.getInstance().getAccessibleText(ta.getNextGroupItem().toString()));
        hl.setAccessibleHelp(LangModel.getInstance().getAccessibleHelp(ta.getNextGroupItem().toString()));
    }

    /**
     * Diese statische Methode gibt die Instanz dieser Klasse zurück (Singleton-Pattern).
     * 
     * @param fi ermöglicht Zugriff auf die GUI 
     * @return Instanz dieser Klasse.
     */
    public static ToolbarActionsListener getInstance(FrameInterface fi) {
        if (ownInstance == null) {
            ownInstance = new ToolbarActionsListener(fi);
        }
        return ownInstance;
    }
}
