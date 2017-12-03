package de.se.trechner.controller;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.ActionCmds;

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
    private final KeyCombination keyCombinationEquals = new KeyCodeCombination(KeyCode.DIGIT0, KeyCombination.SHIFT_DOWN);

    private KeyEvent e;

    @Override
    public void handle(KeyEvent e) {
        this.e = e;
        String keyText = e.getText();
        String keyCharacter = e.getCharacter();
        String keyC = e.getCode().toString();
        System.out.println("Text: "+ keyText + " Char: " + keyCharacter + " Code: " + keyC);

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
        } else if (e.isShiftDown()) {
            if (keyCombinationEquals.match(e)) {
                fireButton(ActionCmds.EQUALS);
            }
        } else {
            // Single Key
            switch ( e.getCode()) {
                case ADD:
                    fireButton(ActionCmds.ADDITION);
                    break;
                case SUBTRACT:
                    fireButton(ActionCmds.SUBTRACT);
                    break;
                case MULTIPLY:
                    fireButton(ActionCmds.MULTIPLY);
                    break;
                case DIVIDE:
                    fireButton(ActionCmds.DIVIDE);
                    break;
                case NUMPAD0:
                case DIGIT0:
                    fireButton(ActionCmds.ZERO);
                    break;
                case NUMPAD1:
                case DIGIT1:
                    fireButton(ActionCmds.ONE);
                    break;
                case NUMPAD2:
                case DIGIT2:
                    fireButton(ActionCmds.TWO);
                    break;
                case NUMPAD3:
                case DIGIT3:
                    fireButton(ActionCmds.THREE);
                    break;
                case NUMPAD4:
                case DIGIT4:
                    fireButton(ActionCmds.FOUR);
                    break;
                case NUMPAD5:
                case DIGIT5:
                    fireButton(ActionCmds.FIVE);
                    break;
                case NUMPAD6:
                case DIGIT6:
                    fireButton(ActionCmds.SIX);
                    break;
                case NUMPAD7:
                case DIGIT7:
                    fireButton(ActionCmds.SEVEN);
                    break;
                case NUMPAD8:
                case DIGIT8:
                    fireButton(ActionCmds.EIGHT);
                    break;
                case NUMPAD9:
                case DIGIT9:
                    fireButton(ActionCmds.NINE);
                    break;
                case COMMA:
                case PERIOD:
                case DECIMAL:
                    fireButton(ActionCmds.KOMMA);
                    break;
                case DEAD_CIRCUMFLEX:
                    fireButton(ActionCmds.XPOWY);
                    break;
                case EQUALS:
                    fireButton(ActionCmds.EQUALS);
                    break;
                case ENTER:
                    if (fi.isBigLabelFocused()) {
                        fireButton(ActionCmds.EQUALS);
                    } else {
                        Button btn = fi.getFocusedButton();
                        if (btn != null) {
                            ActionCmds c = ActionCmds.valueOf(btn.getId());
                            fireButton(c);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void fireButton(ActionCmds a) {
        if (e.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            if (a.getGridID() == a.NR_GRID_ID) {
                fi.getNrGrid().fireButtonEvent(a);
            } else if (a.getGridID() == a.FUNC_GRID_ID) {
                fi.getFuncGrid().fireButtonEvent(a);
            }
        }
        if (e.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            if (a.getGridID() == a.NR_GRID_ID) {
                fi.getNrGrid().releaseButtonEvent(a);
            } else if (a.getGridID() == a.FUNC_GRID_ID) {
                fi.getFuncGrid().releaseButtonEvent(a);
            }
        }
        e.consume();
    }


}
