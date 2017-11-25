package de.se.trechner.controller;

import javafx.event.EventHandler;
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

    @Override
    public void handle(KeyEvent e) {
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
        } else {
            // Single Key
            switch ( e.getCode()) {
                case ADD:
                    fi.getNrGrid().setButtonFocus(ActionCmds.ADDITION);
                    break;
                case SUBTRACT:
                    fi.getNrGrid().setButtonFocus(ActionCmds.SUBTRACT);
                    break;
                case MULTIPLY:
                    fi.getNrGrid().setButtonFocus(ActionCmds.MULTIPLY);
                    break;
                case DIVIDE:
                    fi.getNrGrid().setButtonFocus(ActionCmds.DIVIDE);
                    break;
                case NUMPAD0:
                case DIGIT0:
                    fi.getNrGrid().setButtonFocus(ActionCmds.ZERO);
                    break;
                case NUMPAD1:
                case DIGIT1:
                    fi.getNrGrid().setButtonFocus(ActionCmds.ONE);
                    break;
                case NUMPAD2:
                case DIGIT2:
                    fi.getNrGrid().setButtonFocus(ActionCmds.TWO);
                    break;
                case NUMPAD3:
                case DIGIT3:
                    fi.getNrGrid().setButtonFocus(ActionCmds.THREE);
                    break;
                case NUMPAD4:
                case DIGIT4:
                    fi.getNrGrid().setButtonFocus(ActionCmds.FOUR);
                    break;
                case NUMPAD5:
                case DIGIT5:
                    fi.getNrGrid().setButtonFocus(ActionCmds.FIVE);
                    break;
                case NUMPAD6:
                case DIGIT6:
                    fi.getNrGrid().setButtonFocus(ActionCmds.SIX);
                    break;
                case NUMPAD7:
                case DIGIT7:
                    fi.getNrGrid().setButtonFocus(ActionCmds.SEVEN);
                    break;
                case NUMPAD8:
                case DIGIT8:
                    fi.getNrGrid().setButtonFocus(ActionCmds.EIGHT);
                    break;
                case NUMPAD9:
                case DIGIT9:
                    fi.getNrGrid().setButtonFocus(ActionCmds.NINE);
                    break;
                case COMMA:
                case PERIOD:
                case DECIMAL:
                    fi.getNrGrid().setButtonFocus(ActionCmds.KOMMA);
                    break;
                case DEAD_CIRCUMFLEX:
                    fi.getFuncGrid().setButtonFocus(ActionCmds.XPOWY);
                    break;
                case EQUALS:
                    fi.getNrGrid().setButtonFocus(ActionCmds.EQUALS);
                    break;
                case ENTER:
                    fi.getNrGrid().fireOnFocus();
                    fi.getFuncGrid().fireOnFocus();
                    break;
                default:
                    break;
            }
        }
    }


}
