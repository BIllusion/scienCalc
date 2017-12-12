package de.se.trechner.controller;

import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.AngleMode;
import de.se.trechner.model.LangModel;
import de.se.trechner.model.NumInput;
import de.se.trechner.model.ToolbarActions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

public class ToolbarActionsListener implements EventHandler<ActionEvent> {

    private static ToolbarActionsListener ownInstance;
    private FrameInterface fi;

    private ToolbarActionsListener(FrameInterface fi) {
        this.fi = fi;
    }

    @Override
    public void handle(ActionEvent e) {
        Hyperlink hl = ((Hyperlink) e.getSource());
        String cmdID = hl.getId();
        ToolbarActions ta = ToolbarActions.valueOf(cmdID);
        NumInput numInput = NumInput.getInstance(null);

        switch (ta) {
            case TDEG:
            	numInput.changeMode(AngleMode.RAD);
                System.out.println("DEG");
                break;
            case TRAD:
            	numInput.changeMode(AngleMode.GRAD);
                System.out.println("RAD");
                break;
            case TGRAD:
            	numInput.changeMode(AngleMode.DEG);
                System.out.println("GRAD");
                break;
            case TFE:
            	fi.setBigLabel(numInput.changeFE());
                System.out.println("FE");
                break;
            case TMS:
            	numInput.memorySave();
                System.out.println("MS");
                break;
            case TMR:
            	fi.setBigLabel(numInput.memoryRecall());
                System.out.println("MR");
                break;
            case TMC:
            	numInput.memoryClear();
                System.out.println("MC");
                break;
            default:
                System.out.println("Default");
                break;
        }
        hl.setId(ta.getNextGroupItem().toString());
        hl.setText(LangModel.getInstance().getKeyCaption(ta.getNextGroupItem().toString()));
        hl.setAccessibleText(LangModel.getInstance().getAccessibleText(ta.getNextGroupItem().toString()));
        hl.setAccessibleHelp(LangModel.getInstance().getAccessibleHelp(ta.getNextGroupItem().toString()));
    }

    public static ToolbarActionsListener getInstance(FrameInterface fi) {
        if (ownInstance == null) {
            ownInstance = new ToolbarActionsListener(fi);
        }
        return ownInstance;
    }
}
