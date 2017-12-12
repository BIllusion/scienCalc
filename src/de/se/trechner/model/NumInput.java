package de.se.trechner.model;

/**
 * Diese Klasse ist für Nummereingaben und Toolbaraktionen verantwortlich.
 * 
 * @author wojke_n
 * @version 2017-12-11
 * @see Term
 * @see de.se.trechner.controller.ToolbarActionsListener
 */
public class NumInput {
	private static NumInput ownInstance;
	private boolean isAddingNumber;
    private boolean isInteger;
    private boolean isResult;
    private boolean isPI;
    private boolean isFE;
    private String numInput;
    private double memory;
    
    private Term term;

    private NumInput(Term term) {
    	this.term = term;
    	initialize();
    }
    
    public void initialize() {
    	isAddingNumber = false;
        isInteger = true;
        isResult = false;
        isPI = false;
        isFE = false;
        numInput = "0";
    }
    
    public void memorySave() {
    	memory = Double.valueOf(numInput.replace(',', '.'));
    	isResult = true;
    }
    
    public String memoryRecall() {
    	isAddingNumber = true;
    	numInput = formatNumber(memory);
    	return numInput;
    }
    
    public void memoryClear() {
    	memory = 0;
		isResult = true;
    }
    
    public String changeFE() {
    	isFE = ! isFE;
    	return formatNumber(Double.valueOf(numInput.replace(',', '.')));
    }
    
    public void changeMode(AngleMode mode) {
    	term.changeMode(mode);
    }
    
    public void numEntered() {
    	if(isPI) { 
			numInput = "";
			isPI = false;
		}
		if(! isAddingNumber) {
			isAddingNumber = true;
			isInteger = true;
			numInput = "";
		}
    }
    
    public String nonNumEntered() {
    	isResult = false;
		isPI = false;
		if(isAddingNumber) {
			isAddingNumber = false;
			term.addNumber(Double.valueOf(numInput.replace(',', '.')));
			numInput = "0";
			return term.toString();
		}
		return null;
    }
    
    public void add(String num) {
    	numInput += num;
    }
    
    public void komma() {
    	if(isInteger) {
    		isInteger = false;
    		if(numInput.equals("")) numInput += "0";
    		numInput += ",";
    	}
    }
    
    public void solve() {
    	double value = 0;
    	try {
    		value = term.solve();
    		numInput = formatNumber(value);
    	} catch (MathException e1) {
    		numInput = e1.getMessage();
    	}
		isResult = true;
    }
    
    public void pi() {
    	numInput = formatNumber(MathFunction.PI);
        isPI = true;
    }
    
    public void clearInput() {
    	isAddingNumber = false;
    	isInteger = true;
		numInput = "0";
    }
    
    public void delLastChar() {
    	if(isAddingNumber && ! numInput.equals("")) {
        	numInput = numInput.substring(0, numInput.length()-1);
        	if(numInput.length() > 0 && numInput.charAt(numInput.length()-1) == ',')
        		isInteger = true;
        	if(numInput.equals("")) {
        		numInput = "0";
        		isAddingNumber = false;
        	}
        }
    }
    
    public String unaryOperator(GridActions identifier) {
    	try {
			numInput = formatNumber(term.addUnaryOperator(identifier));
		} catch (MathException e) {
			numInput = e.getMessage();
		}
    	return term.toString();
    }
    
    public void deleteAll() {
    	term.initialize();
    	isAddingNumber = false;
    	isInteger = true;
    	isFE = false;
		numInput = "0";
    }
    
    /**
     * Diese Methode ist für die Umwandlung von double zu Strings verantwortlich.
     * 
     * @param value double, der in ein String umgewandelt werden soll.
     * @return String, der den double-Wert darstellt.
     */
    private String formatNumber(double value) {
    	if(isFE) {
    		if(value == 0) return String.format("%d", (long) value);
    		return MathFunction.fe(value);
    	}else {
    		return MathFunction.formatDouble(value);
    	}
    }
    
    public String getNumInput() {
		return numInput;
	}
    
    public boolean isResult() {
		return isResult;
	}

	public void setResult(boolean isResult) {
		this.isResult = isResult;
	}

	public boolean isAddingNumber() {
		return isAddingNumber;
	}

	public void setAddingNumber(boolean isAddingNumber) {
		this.isAddingNumber = isAddingNumber;
	}

	public boolean isInteger() {
		return isInteger;
	}

	public void setInteger(boolean isInteger) {
		this.isInteger = isInteger;
	}

	public boolean isFE() {
		return isFE;
	}

	public boolean isPI() {
		return isPI;
	}

	public void setPI(boolean isPI) {
		this.isPI = isPI;
	}

	public static NumInput getInstance(Term term) {
    	if(ownInstance == null) {
    		ownInstance = new NumInput(term);
    	}
    	return ownInstance;
    }
}