package de.se.trechner.controller;

import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.ActionCmds;
import de.se.trechner.model.ToolbarActionCmds;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class ToolbarCmdListener implements EventHandler<ActionEvent> {


    private static ToolbarCmdListener ownInstance;
    private FrameInterface fi;

    private ToolbarCmdListener(FrameInterface fi) {
        this.fi = fi;
    }

    @Override
    public void handle(ActionEvent e) {
        String cmdID = ((Hyperlink) e.getSource()).getId();
        ToolbarActionCmds c = ToolbarActionCmds.valueOf(cmdID);
        switch (c) {
            case TDEG:
                System.out.println("DEG");
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
    }

    public static ToolbarCmdListener getInstance(FrameInterface fi) {
        if (ownInstance == null) {
            ownInstance = new ToolbarCmdListener(fi);
        }
        return ownInstance;
    }
}
