package de.se.trechner.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.ActionCmds;
import de.se.trechner.model.Term;


public class ActionCmdListener implements EventHandler<ActionEvent> {
    private static ActionCmdListener ownInstance;

    private FrameInterface fi;
    private Term term;
    private boolean isAddingNumber;
    private boolean isInteger;
    private String numInput;
    

    private ActionCmdListener(FrameInterface fi) {
        this.fi = fi;
        term = new Term();
        isAddingNumber = false;
        isInteger = true;
        numInput = "0";
        fi.setSmallLabel(term.toString());
        fi.setBigLabel(numInput);
    }

    @Override
    public void handle(ActionEvent e) {
    	double value;
        String cmdID = ((Button)e.getSource()).getId();
        ActionCmds c = ActionCmds.valueOf(cmdID);
        switch (c) {
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
        	case CLEARINPUT:
        		if(! isAddingNumber) {
        			isAddingNumber = true;
        			isInteger = true;
        			numInput = "";
        		}
            break;
        	case PI:
        		break;
        	default:
        		if(isAddingNumber) {
        			isAddingNumber = false;
        			term.addNumber(Double.valueOf(numInput.replace(',', '.')));
        			numInput = "0";
        		}
        		break;
        }
        // Entscheidung welche Funktion aufgerufen wird.
        switch (c) {
            case SQR:
                term.addUnaryOperator(19);
                System.out.println("SQR");
                break;
            case CUBIC:
                // DO Something
                System.out.println("CUBIC");
                break;
            case SIN:
                // DO Something
                System.out.println("SIN");
                break;
            case COS:
                // DO Something
                System.out.println("COS");
                break;
            case TAN:
                // DO Something
                System.out.println("TAN");
                break;
            case XPOWY:
                // DO Something
                System.out.println("XPOWY");
                break;
            case RECVAL:
                term.addUnaryOperator(24);
                System.out.println("RECVAL");
                break;
            case ARCSIN:
                // DO Something
                System.out.println("ARCSIN");
                break;
            case ARCCOS:
                // DO Something
                System.out.println("ARCCOS");
                break;
            case ARCTAN:
                // DO Something
                System.out.println("ARCTAN");
                break;
            case SQRT:
                term.addUnaryOperator(22);
                System.out.println("SQRT");
                break;
            case YSQRT:
                // DO Something
                System.out.println("YSQRT");
                break;
            case EXP:
                // DO Something
                System.out.println("EXP");
                break;
            case DMS:
                // DO Something
                System.out.println("DMS");
                break;
            case DEG:
                // DO Something
                System.out.println("DEG");
                break;
            case EXPF:
                // DO Something
                System.out.println("EXPF");
                break;
            case LOG:
                term.addUnaryOperator(29);
                System.out.println("LOG");
                break;
            case MOD:
                // DO Something
                System.out.println("MOD");
                break;
            case OBRACKET:
                term.addBracket(true);
                System.out.println("OBRACKET");
                break;
            case CBRACKET:
                term.addBracket(false);
                System.out.println("CBRACKET");
                break;
            case EX:
                // DO Something
                System.out.println("EX");
                break;
            case LN:
                term.addUnaryOperator(30);
                System.out.println("LN");
                break;
            case FACT:
                term.addUnaryOperator(26);
                System.out.println("FACT");
                break;
            case PI:
                // DO Something
                System.out.println("PI");
                break;
            case SIGNCHANGE:
                term.addUnaryOperator(10);
                System.out.println("SIGNCHANGE");
                break;
            case CLEARINPUT:
            	isAddingNumber = false;
            	isInteger = true;
    			numInput = "0";
                System.out.println("CLEARINPUT");
                break;
            case DELETEALL:
                term.initialize();
                isAddingNumber = false;
            	isInteger = true;
    			numInput = "0";
                System.out.println("DELETEALL");
                break;
            case DELLASTCHAR:
                // DO Something
                System.out.println("DELLASTCHAR");
                break;
            case ONE:
                numInput += "1";
                System.out.println("ONE");
                break;
            case TWO:
            	numInput += "2";
                System.out.println("TWO");
                break;
            case THREE:
            	numInput += "3";
                System.out.println("THREE");
                break;
            case FOUR:
            	numInput += "4";
                System.out.println("FOUR");
                break;
            case FIVE:
            	numInput += "5";
                System.out.println("FIVE");
                break;
            case SIX:
            	numInput += "6";
                System.out.println("SIX");
                break;
            case SEVEN:
            	numInput += "7";
                System.out.println("SEVEN");
                break;
            case EIGHT:
            	numInput += "8";
                System.out.println("EIGHT");
                break;
            case NINE:
            	numInput += "9";
                System.out.println("NINE");
                break;
            case ZERO:
            	numInput += "0";
                System.out.println("ZERO");
                break;
            case KOMMA:
            	if(isInteger) {
            		isInteger = false;
            		numInput += ",";
            	}
                System.out.println("KOMMA");
                break;
            case DIVIDE:
                term.addBinaryOperator(5);
                System.out.println("DIVIDE");
                break;
            case MULTIPLY:
                term.addBinaryOperator(4);
                System.out.println("MULTIPLY");
                break;
            case SUBTRACT:
                term.addBinaryOperator(3);
                System.out.println("SUBTRACT");
                break;
            case ADDITION:
                term.addBinaryOperator(2);
                System.out.println("ADDITION");
                break;
            case EQUALS:
                value = term.solve();
                if(value == (long) value)
        			numInput = String.format("%d", (long) value);
                else numInput = String.format("%s", value);
                System.out.println("EQUALS");
                break;
            default:
                System.out.println("ActionCommand ist nicht bekannt");
                break;
        }
        fi.setSmallLabel(term.toString());
        fi.setBigLabel(numInput);
        e.consume();
    }

    public static ActionCmdListener getInstance(FrameInterface fi) {
        if (ownInstance == null) {
            ownInstance = new ActionCmdListener(fi);
        }
        return ownInstance;
    }
}
