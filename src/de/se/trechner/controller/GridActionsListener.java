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
        fi.setSmallLabel(calculation);
        fi.setBigLabel(numInput.getNumInput());
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
                System.out.println("SQR");
                break;
            case CUBIC:
                unaryOperator(ga);
                System.out.println("CUBIC");
                break;
            case SIN:
                unaryOperator(ga);
                System.out.println("SIN");
                break;
            case COS:
                unaryOperator(ga);
                System.out.println("COS");
                break;
            case TAN:
                unaryOperator(ga);
                System.out.println("TAN");
                break;
            case XPOWY:
            	binaryOperator(ga);
                System.out.println("XPOWY");
                break;
            case RECVAL:
            	unaryOperator(ga);
                System.out.println("RECVAL");
                break;
            case ARCSIN:
                unaryOperator(ga);
                System.out.println("ARCSIN");
                break;
            case ARCCOS:
            	unaryOperator(ga);
                System.out.println("ARCCOS");
                break;
            case ARCTAN:
            	unaryOperator(ga);
                System.out.println("ARCTAN");
                break;
            case SQRT:
            	unaryOperator(ga);
                System.out.println("SQRT");
                break;
            case YSQRT:
            	binaryOperator(ga);
                System.out.println("YSQRT");
                break;
            case EXP:
            	binaryOperator(ga);
                System.out.println("EXP");
                break;
            case DMS:
                unaryOperator(ga);
                System.out.println("DMS");
                break;
            case DEG:
            	unaryOperator(ga);
                System.out.println("DEG");
                break;
            case EXPF:
            	unaryOperator(ga);
                System.out.println("EXPF");
                break;
            case LOG:
            	unaryOperator(ga);
                System.out.println("LOG");
                break;
            case MOD:
            	binaryOperator(ga);
                System.out.println("MOD");
                break;
            case OBRACKET:
                term.addBracket(true);
                calculation = term.toString();
                System.out.println("OBRACKET");
                break;
            case CBRACKET:
                term.addBracket(false);
                calculation = term.toString();
                System.out.println("CBRACKET");
                break;
            case EX:
                unaryOperator(ga);
                System.out.println("EX");
                break;
            case LN:
            	unaryOperator(ga);
                System.out.println("LN");
                break;
            case FACT:
            	unaryOperator(ga);
                System.out.println("FACT");
                break;
            case PI:
                numInput.pi();
                System.out.println("PI");
                break;
            case SIGNCHANGE:
            	unaryOperator(ga);
                System.out.println("SIGNCHANGE");
                break;
            case CLEARINPUT:
            	numInput.clearInput();
                System.out.println("CLEARINPUT");
                break;
            case DELETEALL:
                deleteAll();
                System.out.println("DELETEALL");
                break;
            case DELLASTCHAR:
                numInput.delLastChar();
                System.out.println("DELLASTCHAR");
                break;
            case ONE:
                numInput.add("1");
                System.out.println("ONE");
                break;
            case TWO:
            	numInput.add("2");
                System.out.println("TWO");
                break;
            case THREE:
            	numInput.add("3");
                System.out.println("THREE");
                break;
            case FOUR:
            	numInput.add("4");
                System.out.println("FOUR");
                break;
            case FIVE:
            	numInput.add("5");
                System.out.println("FIVE");
                break;
            case SIX:
            	numInput.add("6");
                System.out.println("SIX");
                break;
            case SEVEN:
            	numInput.add("7");
                System.out.println("SEVEN");
                break;
            case EIGHT:
            	numInput.add("8");
                System.out.println("EIGHT");
                break;
            case NINE:
            	numInput.add("9");
                System.out.println("NINE");
                break;
            case ZERO:
            	numInput.add("0");
                System.out.println("ZERO");
                break;
            case KOMMA:
            	numInput.komma();
                System.out.println("KOMMA");
                break;
            case DIVIDE:
                binaryOperator(ga);
                System.out.println("DIVIDE");
                break;
            case MULTIPLY:
                binaryOperator(ga);
                System.out.println("MULTIPLY");
                break;
            case SUBTRACT:
                binaryOperator(ga);
                System.out.println("SUBTRACT");
                break;
            case ADDITION:
            	binaryOperator(ga);
                System.out.println("ADDITION");
                break;
            case EQUALS:
            	numInput.solve();
                System.out.println("EQUALS");
                break;
            default:
                System.out.println("ActionCommand ist nicht bekannt");
                break;
        }
        fi.setSmallLabel(calculation);
        fi.setBigLabel(numInput.getNumInput());
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