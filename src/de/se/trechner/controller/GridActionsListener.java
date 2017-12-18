package de.se.trechner.controller;

import de.se.trechner.model.GridActions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.NumInput;
import de.se.trechner.model.Term;

/**
 * Diese Klasse wertet Nutzereingaben aus und steuert damit die Modellklassen Term und MathFunction.
 * Sie ist als Singleton realisiert.
 * 
 * @author wojke_n
 * @author ruess_c
 * @version 2017-12-09
 * @see de.se.trechner.model.Term
 */
public class GridActionsListener implements EventHandler<ActionEvent> {
    private static GridActionsListener ownInstance;
    private NumInput numInput;
    private FrameInterface fi;
    private Term term;
    private String calculation;
    
    /**
     * Konstruktor ist privat. Er ist für die Initialisierung zuständig.
     * 
     * @param fi das Frameinterface ermöglicht den Zugriff auf die GUI
     */
    private GridActionsListener(FrameInterface fi) {
        this.fi = fi;
        term = Term.getInstance();
        numInput = NumInput.getInstance(term);
        calculation = "";
        fi.getDisplay().setSmallMsgBox(calculation);
        fi.getDisplay().setBigMsgBox(numInput.getNumInput());
    }

    /**
     * Ermittelt welcher Button aus dem FuncGrid und NrGrid gedrückt wurde und
     * bestimmt welche Aktion ausgeführt wird.
     *
     * @param e ermögicht die Unterscheidung zwischen den einzelnen Aktionen und enthält den GridActions Command
     * @see de.se.trechner.view.NrGrid
     * @see de.se.trechner.view.FuncGrid
     * @see de.se.trechner.model.GridActions
     */
    @Override
    public void handle(ActionEvent e) {
        String cmdID = ((Button)e.getSource()).getId();
        GridActions ga = GridActions.valueOf(cmdID);
        switch (ga) {
        	case ONE:
        	case TWO:
        	case THREE:
        	case FOUR:
        	case FIVE:
        	case SIX:
        	case SEVEN:
        	case EIGHT:
        	case NINE:
        	case ZERO:
        	case KOMMA:
        	case PI:
        	case CLEARINPUT:
        		if(numInput.isResult()) {
        			deleteAll();
        			numInput.setResult(false);
        		}
        		numInput.numEntered();
        		break;
        	case DELLASTCHAR:
        		break;
        	default:
        		String temp = numInput.nonNumEntered();
        		if(temp != null);
        		calculation = temp;
        		break;
        }
        // Entscheidung welche Funktion aufgerufen wird.
        switch (ga) {
            case SQR:
            	unaryOperator(ga);
                break;
            case CUBIC:
                unaryOperator(ga);
                break;
            case SIN:
                unaryOperator(ga);
                break;
            case COS:
                unaryOperator(ga);
                break;
            case TAN:
                unaryOperator(ga);
                break;
            case XPOWY:
            	binaryOperator(ga);
                break;
            case RECVAL:
            	unaryOperator(ga);
                break;
            case ARCSIN:
                unaryOperator(ga);
                break;
            case ARCCOS:
            	unaryOperator(ga);
                break;
            case ARCTAN:
            	unaryOperator(ga);
                break;
            case SQRT:
            	unaryOperator(ga);
                break;
            case YSQRT:
            	binaryOperator(ga);
                break;
            case EXP:
            	binaryOperator(ga);
                break;
            case DMS:
                unaryOperator(ga);
                break;
            case DEG:
            	unaryOperator(ga);
                break;
            case EXPF:
            	unaryOperator(ga);
                break;
            case LOG:
            	unaryOperator(ga);
                break;
            case MOD:
            	binaryOperator(ga);
                break;
            case OBRACKET:
                term.addBracket(true);
                calculation = term.toString();
                break;
            case CBRACKET:
                term.addBracket(false);
                calculation = term.toString();
                break;
            case EX:
                unaryOperator(ga);
                break;
            case LN:
            	unaryOperator(ga);
                break;
            case FACT:
            	unaryOperator(ga);
                break;
            case PI:
                numInput.pi();
                break;
            case SIGNCHANGE:
            	unaryOperator(ga);
                break;
            case CLEARINPUT:
            	numInput.clearInput();
                break;
            case DELETEALL:
                deleteAll();
                break;
            case DELLASTCHAR:
                numInput.delLastChar();
                break;
            case ONE:
                numInput.add("1");
                break;
            case TWO:
            	numInput.add("2");
                break;
            case THREE:
            	numInput.add("3");
                break;
            case FOUR:
            	numInput.add("4");
                break;
            case FIVE:
            	numInput.add("5");
                break;
            case SIX:
            	numInput.add("6");
                break;
            case SEVEN:
            	numInput.add("7");
                break;
            case EIGHT:
            	numInput.add("8");
                break;
            case NINE:
            	numInput.add("9");
                break;
            case ZERO:
            	numInput.add("0");
                break;
            case KOMMA:
            	numInput.komma();
                break;
            case DIVIDE:
                binaryOperator(ga);
                break;
            case MULTIPLY:
                binaryOperator(ga);
                break;
            case SUBTRACT:
                binaryOperator(ga);
                break;
            case ADDITION:
            	binaryOperator(ga);
                break;
            case EQUALS:
            	numInput.solve();
                break;
            default:
                System.out.println("ActionCommand ist nicht bekannt");
                break;
        }
        fi.getDisplay().setSmallMsgBox(calculation);
        fi.getDisplay().setBigMsgBox(numInput.getNumInput());
        e.consume();
    }
    
    /**
     * Diese Methode fügt dem Term einen Binäroperator hinzu.
     * 
     * @param identifier ermöglicht Identifizierung des Operators
     */
    private void binaryOperator(GridActions identifier) {
    	term.addBinaryOperator(identifier);
        calculation = term.toString();
    }
    
    /**
     * Diese Methode fügt den Term einen Unäroperator hinzu.
     * 
     * @param identifier ermöglicht Identifizierung des Operators
     */
    private void unaryOperator(GridActions identifier) {
    	calculation = numInput.unaryOperator(identifier);
    }
    
    private void deleteAll() {
		calculation = "";
		numInput.deleteAll();
    }

    /**
     * Diese statische Methode gibt die Instanz dieser Klasse zurück. (Singleton-Pattern)
     * 
     * @param fi das Frameinterface ermöglicht den Zugriff auf die GUI
     * @return Instanz dieser Klasse
     */
    public static GridActionsListener getInstance(FrameInterface fi) {
        if (ownInstance == null) {
            ownInstance = new GridActionsListener(fi);
        }
        return ownInstance;
    }
}