package de.se.trechner.controller;

import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.LangModel;
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

        switch (ta) {
            case TDEG:
                System.out.println("DEG");
                break;
            case TRAD:
                System.out.println("RAD");
                break;
            case TGRAD:
                System.out.println("GRAD");
                break;
            case TFE:
                System.out.println("FE");
                break;
            case TMS:
                System.out.println("MS");
                break;
            case TMR:
                System.out.println("MR");
                break;
            case TMC:
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
