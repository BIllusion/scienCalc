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

    private final KeyCombination keyCombinationExpf = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationSin = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationConv = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationExtras = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationDel = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationNum = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationOp = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);

    @Override
    public void handle(KeyEvent e) {
        if (e.isControlDown()) {
            // Keystroke Checks
            if (keyCombinationExpf.match(e)) {
                fi.getFuncGrid().setButtonFocus(ActionCmds.SQR);

            } else if (keyCombinationSin.match(e)) {
                fi.getFuncGrid().setButtonFocus(ActionCmds.SIN);

            } else if (keyCombinationConv.match(e)) {
                fi.getFuncGrid().setButtonFocus(ActionCmds.EXP);

            } else if (keyCombinationExtras.match(e)) {
                fi.getFuncGrid().setButtonFocus(ActionCmds.MOD);

            } else if (keyCombinationDel.match(e)) {
                fi.getNrGrid().setButtonFocus(ActionCmds.CLEARINPUT);

            } else if (keyCombinationNum.match(e)) {
                fi.getNrGrid().setButtonFocus(ActionCmds.ONE);

            } else if (keyCombinationOp.match(e)) {
                fi.getNrGrid().setButtonFocus(ActionCmds.DIVIDE);

            }
        } else {
            // Single Button press Check
            if (e.getCode() == KeyCode.ADD) {
                fi.getNrGrid().fireButton(ActionCmds.ADDITION);
            }
            String keyText = e.getText();
            String keyCharacter = e.getCharacter();
            String keyCode = e.getCode().toString();
            System.out.println("Text: "+ keyText + " Char: " + keyCharacter + " Code: " + keyCode);
        }


    }


}
