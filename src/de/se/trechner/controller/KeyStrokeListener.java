package de.se.trechner.controller;

import de.se.trechner.model.GridActions;
import de.se.trechner.model.ToolbarActions;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import de.se.trechner.interfaces.FrameInterface;

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
    private final KeyCombination keyCombinationInput = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationPlus = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationMinus = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationMult = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationDiv = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);

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
                fi.getFuncGrid().requestFocus(GridActions.SQR);

            } else if (keyCombinationSin.match(e)) {
                fi.getFuncGrid().requestFocus(GridActions.SIN);

            } else if (keyCombinationConv.match(e)) {
                fi.getFuncGrid().requestFocus(GridActions.EXP);

            } else if (keyCombinationExtras.match(e)) {
                fi.getFuncGrid().requestFocus(GridActions.MOD);

            } else if (keyCombinationDel.match(e)) {
                fi.getNrGrid().requestFocus(GridActions.CLEARINPUT);

            } else if (keyCombinationNum.match(e)) {
                fi.getNrGrid().requestFocus(GridActions.ONE);

            } else if (keyCombinationOp.match(e)) {
                fi.getNrGrid().requestFocus(GridActions.DIVIDE);

            } else if (keyCombinationInput.match(e)) {
                fi.setInputFocus();
            }

        } else if (e.isShiftDown()) {
            if (keyCombinationEquals.match(e)) {
                fireAction(GridActions.EQUALS);
            }
        } else {
            // Single Key
            switch ( e.getCode()) {
                case ADD:
                    fireAction(GridActions.ADDITION);
                    break;
                case SUBTRACT:
                    fireAction(GridActions.SUBTRACT);
                    break;
                case MULTIPLY:
                    fireAction(GridActions.MULTIPLY);
                    break;
                case DIVIDE:
                    fireAction(GridActions.DIVIDE);
                    break;
                case NUMPAD0:
                case DIGIT0:
                    fireAction(GridActions.ZERO);
                    break;
                case NUMPAD1:
                case DIGIT1:
                    fireAction(GridActions.ONE);
                    break;
                case NUMPAD2:
                case DIGIT2:
                    fireAction(GridActions.TWO);
                    break;
                case NUMPAD3:
                case DIGIT3:
                    fireAction(GridActions.THREE);
                    break;
                case NUMPAD4:
                case DIGIT4:
                    fireAction(GridActions.FOUR);
                    break;
                case NUMPAD5:
                case DIGIT5:
                    fireAction(GridActions.FIVE);
                    break;
                case NUMPAD6:
                case DIGIT6:
                    fireAction(GridActions.SIX);
                    break;
                case NUMPAD7:
                case DIGIT7:
                    fireAction(GridActions.SEVEN);
                    break;
                case NUMPAD8:
                case DIGIT8:
                    fireAction(GridActions.EIGHT);
                    break;
                case NUMPAD9:
                case DIGIT9:
                    fireAction(GridActions.NINE);
                    break;
                case COMMA:
                case PERIOD:
                case DECIMAL:
                    fireAction(GridActions.KOMMA);
                    break;
                case DEAD_CIRCUMFLEX:
                case BACK_QUOTE:
                    fireAction(GridActions.XPOWY);
                    break;
                case EQUALS:
                    fireAction(GridActions.EQUALS);
                    break;
                case ESCAPE:
                    fireAction(GridActions.DELETEALL);
                    break;
                case DELETE:
                    fireAction(GridActions.CLEARINPUT);
                    break;
                case ENTER:
                case SPACE:
                    if (fi.isBigLabelFocused()) {
                        fireAction(GridActions.EQUALS);
                    } else {
                        fireAction(fi.getIdFromFocus());
                    }
                    break;
                case BACK_SPACE:
                    fireAction(GridActions.DELLASTCHAR);
                    break;
                case TAB:
                    if (fi.isBigLabelFocused() && e.getEventType().equals(KeyEvent.KEY_PRESSED)){
                        e.consume();
                        fi.getNrGrid().requestFocus(GridActions.ONE);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void fireAction(GridActions ga) {
        if (e.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            fi.getNrGrid().fireActionEvent(ga);
            fi.getFuncGrid().fireActionEvent(ga);
        }
        if (e.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            fi.getNrGrid().releaseActionEvent(ga);
            fi.getFuncGrid().releaseActionEvent(ga);
        }
    }

    private void fireAction(ToolbarActions ta) {
        if(e.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            fi.getToolBar().fireActionEvent(ta);
        }
        if (e.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            fi.getToolBar().releaseActionEvent(ta);
        }
    }

    private void fireAction(String cmd) {
        for (ToolbarActions tac: ToolbarActions.values()) {
            if (tac.toString().equals(cmd)) {
                fireAction(tac);
            }
        }

        for (GridActions ac : GridActions.values()) {
            if (ac.toString().equals(cmd)) {
                fireAction(ac);
            }
        }
        e.consume();
    }

}
