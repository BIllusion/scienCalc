package de.se.trechner.controller;

import de.se.trechner.model.GridActions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.MathFunction;
import de.se.trechner.model.Term;


public class GridActionsListener implements EventHandler<ActionEvent> {
    private static GridActionsListener ownInstance;

    private FrameInterface fi;
    private Term term;
    private boolean isAddingNumber;
    private boolean isInteger;
    private boolean isResult;
    private boolean isPI;
    private String numInput;
    private String calculation;
    

    private GridActionsListener(FrameInterface fi) {
        this.fi = fi;
        term = new Term();
        isAddingNumber = false;
        isInteger = true;
        isResult = false;
        isPI = false;
        numInput = "0";
        calculation = "";
        fi.setSmallLabel(calculation);
        fi.setBigLabel(numInput);
    }

    @Override
    public void handle(ActionEvent e) {
    	double value = 0;
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
        		if(isResult) {
        			deleteAll();
        			isResult = false;
        		}
        		if(isPI) { 
        			numInput = "";
        			isPI = false;
        		}
        		if(! isAddingNumber) {
        			isAddingNumber = true;
        			isInteger = true;
        			numInput = "";
        		}
        		break;
        	case DELLASTCHAR:
        		break;
        	default:
        		isResult = false;
        		isPI = false;
        		if(isAddingNumber) {
        			isAddingNumber = false;
        			term.addNumber(Double.valueOf(numInput.replace(',', '.')));
        			numInput = "0";
        			calculation = term.toString();
        		}
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
                // DO Something
                System.out.println("DMS");
                break;
            case DEG:
                // DO Something
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
                numInput = formatNumber(MathFunction.PI);
                isPI = true;
                System.out.println("PI");
                break;
            case SIGNCHANGE:
            	unaryOperator(ga);
                System.out.println("SIGNCHANGE");
                break;
            case CLEARINPUT:
            	isAddingNumber = false;
            	isInteger = true;
    			numInput = "0";
                System.out.println("CLEARINPUT");
                break;
            case DELETEALL:
                deleteAll();
                System.out.println("DELETEALL");
                break;
            case DELLASTCHAR:
                if(isAddingNumber && ! numInput.equals("")) {
                	numInput = numInput.substring(0, numInput.length()-1);
                	if(numInput.length() > 0 && numInput.charAt(numInput.length()-1) == ',')
                		isInteger = true;
                	if(numInput.equals("")) {
                		numInput = "0";
                		isAddingNumber = false;
                	}
                }
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
            		if(numInput.equals("")) numInput += "0";
            		numInput += ",";
            	}
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
            	try {
            		value = term.solve();
            		numInput = formatNumber(value);
            	} catch (Exception e1) {
            		numInput = e1.getMessage();
            	}
				isResult = true;
                System.out.println("EQUALS");
                break;
            default:
                System.out.println("ActionCommand ist nicht bekannt");
                break;
        }
        fi.setSmallLabel(calculation);
        fi.setBigLabel(numInput);
        e.consume();
    }
    
    private void binaryOperator(GridActions identifier) {
    	term.addBinaryOperator(identifier);
        calculation = term.toString();
    }
    
    private void unaryOperator(GridActions identifier) {
    	try {
			numInput = formatNumber(term.addUnaryOperator(identifier));
		} catch (Exception e) {
			numInput = e.getMessage();
		}
        calculation = term.toString();
    }
    
    private void deleteAll() {
    	term.initialize();
        isAddingNumber = false;
    	isInteger = true;
		numInput = "0";
		calculation = "";
    }
    
    private String formatNumber(double value) {
    	if(value == (long) value)
			return String.format("%d", (long) value);
        else return String.format("%s", value);
    }

    public static GridActionsListener getInstance(FrameInterface fi) {
        if (ownInstance == null) {
            ownInstance = new GridActionsListener(fi);
        }
        return ownInstance;
    }
}