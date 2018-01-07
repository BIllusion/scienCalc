package de.se.trechner.model;

/**
 * Diese Klasse ist für Nummereingaben und Toolbaraktionen verantwortlich.
 * 
 * @author wojke_n
 * @version 2017-12-16
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
    
    /**
     * Diese Methode dient der Initialisierung der Instanz.
     */
    public void initialize() {
    	isAddingNumber = false;
        isInteger = true;
        isResult = false;
        isPI = false;
        isFE = false;
        numInput = "0";
    }
    
    /**
     * Diese Methode implementiert die Funktionalität der MS-Taste.
     * Sie speichert den angezeigten Wert.
     */
    public void memorySave() {
    	memory = Double.valueOf(numInput.replace(',', '.').replace("=",""));
    	isResult = true;
    }
    
    /**
     * Diese Methode implementiert die Funktionalität der MR-Taste.
     * Sie gibt den abgespeicherten Wert zurück.
     * 
     * @return Abgespeicherter Wert
     */
    public String memoryRecall() {
    	isAddingNumber = true;
    	numInput = formatNumber(memory);
    	return numInput;
    }
    
    /**
     * Diese Methode implementiert die Funktionalität der MC-Taste.
     * Sie löscht den abgespeicherten Wert.
     */
    public void memoryClear() {
    	memory = 0;
		isResult = true;
    }
    
    /**
     * Wechselt den Anzeigemodus zu FE oder zurück.
     * 
     * @return den angezeigten Wert in den jeweils anderen Modus
     */
    public String changeFE() {
    	isFE = ! isFE;
    	return formatNumber(Double.valueOf(numInput.replace(',', '.')));
    }
    
    /**
     * Ändert den aktuellen Winkelmaß-Modus.
     * 
     * @param mode Modus in den gewechselt werden soll.
     */
    public void changeMode(AngleMode mode) {
    	term.changeMode(mode);
    }
    
    /**
     * Wird ausgeführt, wenn eine Zahl eingegeben wurde.
     */
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
    
    /**
     * Wird ausgeführt, wenn keine Zahl eingegeben wurde.
     * 
     * @return gibt die Berechnung zurück, falls eine Zahl eingefügt wurde.
     */
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
    
    /**
     * Dient dem Hinzufügen einer Zahl.
     * 
     * @param num die Zahl, die hinzugefügt werden soll
     */
    public void add(String num) {
    	numInput += num;
    }
    
    /**
     * Für das Hinzufügen von Komma zu einer Zahl.
     */
    public void komma() {
    	if(isInteger) {
    		isInteger = false;
    		if(numInput.equals("")) numInput += "0";
    		numInput += ",";
    	}
    }
    
    /**
     * Wird aufgerufen, wenn die Gleich-Taste gedrückt wurde.
     */
    public void solve() {
    	double value = 0;
    	try {
    		value = term.solve();
    		numInput = "=" + formatNumber(value);
    	} catch (MathException e1) {
    		numInput = e1.getMessage();
    	}
		isResult = true;
    }
    
    /**
     * Wird aufgerufen bei der Eingabe von Pi.
     */
    public void pi() {
    	numInput = formatNumber(MathFunction.PI);
        isPI = true;
    }
    
    /**
     * Diese Methode wird beim Hinzufügen von unären Operator aufgerufen.
     * 
     * @param identifier ermöglicht Identifizierung des unären Operators
     * @return String, der die Berechnung darstellt
     */
    public String unaryOperator(GridActions identifier) {
    	try {
			numInput = "=" + formatNumber(term.addUnaryOperator(identifier));
		} catch (MathException e) {
			numInput = e.getMessage();
		}
    	return term.toString();
    }
    
    /**
     * Wird aufgerufen beim Drücken der CE-Taste.
     */
    public void clearInput() {
    	isAddingNumber = false;
    	isInteger = true;
		numInput = "0";
    }
    
    /**
     * Wird aufgerufen beim Drücken der DEL-Taste.
     */
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
    
    /**
     * Wird aufgerufen, wenn die C-Taste gedrückt wird.
     */
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

	/**
	 * Gibt eine Instanz von NumInput zurück
	 * 
	 * @param term ermöglicht Zugriff auf die interne Logik
	 * @return Instanz dieser Klasse
	 */
	public static NumInput getInstance(Term term) {
    	if(ownInstance == null) {
    		ownInstance = new NumInput(term);
    	}
    	return ownInstance;
    }
}