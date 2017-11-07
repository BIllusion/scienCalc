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

    private final KeyCombination keyCombinationExpf = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY);
    private final KeyCombination keyCombinationSin = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY);
    private final KeyCombination keyCombinationConv = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);
    private final KeyCombination keyCombinationExtras = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_ANY);
    private final KeyCombination keyCombinationDel = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_ANY);
    private final KeyCombination keyCombinationNum = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_ANY);
    private final KeyCombination keyCombinationOp = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY);

    @Override
    public void handle(KeyEvent event) {
        if (keyCombinationExpf.match(event)) {
            fi.getFuncGrid().setButtonFocus(ActionCmds.SQR);

        } else if (keyCombinationSin.match(event)) {
            fi.getFuncGrid().setButtonFocus(ActionCmds.SIN);

        } else if (keyCombinationConv.match(event)) {
            fi.getFuncGrid().setButtonFocus(ActionCmds.EXP);

        } else if (keyCombinationExtras.match(event)) {
            fi.getFuncGrid().setButtonFocus(ActionCmds.MOD);

        } else if (keyCombinationDel.match(event)) {
            fi.getNrGrid().setButtonFocus(ActionCmds.CLEARINPUT);

        } else if (keyCombinationNum.match(event)) {
            fi.getNrGrid().setButtonFocus(ActionCmds.ONE);

        } else if (keyCombinationOp.match(event)) {
            fi.getNrGrid().setButtonFocus(ActionCmds.DIVIDE);

        }
    }


}
