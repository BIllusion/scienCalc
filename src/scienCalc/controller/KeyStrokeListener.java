package scienCalc.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import scienCalc.calcInterfaces.FrameInterface;
import scienCalc.model.ActionCmds;

public class KeyStrokeListener implements EventHandler<KeyEvent> {


    private FrameInterface fi;
    public KeyStrokeListener(FrameInterface fi) {
        this.fi = fi;
    }

    private final KeyCombination keyCombinationShiftS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY);

    @Override
    public void handle(KeyEvent event) {
        if (keyCombinationShiftS.match(event)) {
            System.out.println("KeyStroke Detected");
            fi.getNrGrid().setButtonFocus(ActionCmds.ZERO);

        }
    }


}
