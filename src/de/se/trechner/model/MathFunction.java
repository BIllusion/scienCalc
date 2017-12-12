package de.se.trechner.model;

/**
 * Diese Klasse dient zur Berechnung von unären und binären Operationen.
 * Außerdem enthält sie Methoden zur Formatierung von doubles.
 * 
 * @author wojke_n
 * @version 2017-12-11
 * @see Term
 */
public class MathFunction {
	public static final double PI = Math.PI;
	
	private static MathFunction ownInstance;
	private AngleMode mode;
	
	private MathFunction(){
		mode = AngleMode.DEG;
	}
	
	/**
	 * Diese Methode ändert den Modus für das Winkelmaß.
	 * 
	 * @param mode Modus zu dem gewechselt werden soll.
	 * @see AngleMode
	 */
	public void changeMode(AngleMode mode){
		this.mode = mode;
	}
	
	/**
	 * Diese Methode formatiert einen double in FE-Schreibweise und gibt diese zurück.
	 * 
	 * @param value der zu formatierende Wert
	 * @return String, der den double entspricht
	 */
	public static String fe(double value) {
		int i = 0;
		if(value < 1) {
			for(; value < 1; i++)
				value *= 10;
			return String.format("%s", value).replace('.', ',') + ",e-" + i;
		}else {
			for(; value >= 10; i++)
				value /= 10;
			return String.format("%s", value).replace('.', ',') + ",e+" + i;
		}
	}
	
	/**
	 * Diese Methode sorgt dafür das double ordentlich angezeigt werden.
	 * Falls der double ein ganzen Wert darstellt, wird er ohne Komma und 0 angezeigt.
	 * 
	 * @param value der zu formatierende Wert
	 * @return String, der den double entspricht
	 */
	public static String formatDouble(double value) {
		if(value == (long) value)
			return String.format("%d", (long) value);
        else return String.format("%s", value).replace('.', ',');
	}
	
	/**
	 * Diese Methode ist für die Berechnung unärer Operatoren zuständig.
	 * 
	 * @param identifier ermöglicht die Identifizierung des Operators
	 * @param value Operand
	 * @return Ergebnis der Berechnung
	 * @throws MathException 
	 */
	public double unaryOperation(GridActions identifier, double value) throws MathException{
		switch(identifier){
		case SIGNCHANGE:
			return signChange(value);
		case SIN:
			return sin(value);
		case ARCSIN:
			return arcSin(value);
		case COS:
			return cos(value);
		case ARCCOS:
			return arcCos(value);
		case TAN:
			return tan(value);
		case ARCTAN:
			return arcTan(value);
		case SQR:
			return sqr(value);
		case CUBIC:
			return cubic(value);
		case SQRT:
			return sqrt(value);
		case RECVAL:
			return recVal(value);
		case FACT:
			return fact(value);
		case EX:
			return ex(value);
		case EXPF:
			return expf(value);
		case LOG:
			return log(value);
		case LN:
			return ln(value);
		case DMS:
			return dms(value);
		case DEG:
			return deg(value);
		default:
			return -1;
		}
	}
	
	/**
	 * Vorzeichenwechsel
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double signChange(double value) {
		return -value;
	}
	
	/**
	 * Sinus
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private double sin(double value) {
		switch(mode){
		default:
		case DEG:
			return Math.sin(Math.toRadians(value));
		case RAD:
			return Math.sin(value);
		case GRAD:
			return Math.sin(value*PI/200);
		}
	}
	
	/**
	 * Arkussinus
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private double arcSin(double value) {
		switch(mode){
		default:
		case DEG:
			return Math.toDegrees(Math.asin(value));
		case RAD:
			return Math.asin(value);
		case GRAD:
			return 200 / PI * Math.asin(value);
		}
	}
	
	/**
	 * Kosinus
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private double cos(double value) {
		switch(mode){
		default:
		case DEG:
			return Math.cos(Math.toRadians(value));
		case RAD:
			return Math.cos(value);
		case GRAD:
			return Math.cos(PI / 200 * value);
		}
	}
	
	/**
	 * Arkuskosinus
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private double arcCos(double value) {
		switch(mode){
		default:
		case DEG:
			return Math.toDegrees(Math.acos(value));
		case RAD:
			return Math.acos(value);
		case GRAD:
			return 200 / PI * Math.acos(value);
		}
	}
	
	/**
	 * Tangens
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private double tan(double value) {
		switch(mode){
		default:
		case DEG:
			return Math.tan(Math.toRadians(value));
		case RAD:
			return Math.tan(value);
		case GRAD:
			return Math.tan(PI / 200 * value);
		}
	}
	
	/**
	 * Arkustangens
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private double arcTan(double value) {
		switch(mode){
		default:
		case DEG:
			return Math.toDegrees(Math.atan(value));
		case RAD:
			return Math.atan(value);
		case GRAD:
			return 200 / PI * Math.atan(value);
		}
	}
	
	/**
	 * Quadratfunktion
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double sqr(double value){
		return Math.pow(value, 2);
	}
	
	/**
	 * Kubikwertberechnung
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double cubic(double value) {
		return Math.pow(value, 3);
	}
	
	/**
	 * Wurzel
	 * 
	 * @param value Radikand
	 * @return Ergebnis
	 * @throws MathException wird ausgelöst falls der Radikand negativ ist
	 */
	private static double sqrt(double value) throws MathException {
		if(value < 0) throw new MathException("Der Radikand darf nicht negativ sein.");
		return Math.sqrt(value);
	}
	
	/**
	 * Kehrwert
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 * @throws MathException falls der Operand 0 ist
	 */
	private static double recVal(double value) throws MathException {
		return divide(1, value);
	}
	
	/**
	 * Fakultät
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double fact(double value) {
		long product = 1;
		for(int i=2; i <= value; i++) product *= i;
		return product;
	}
	
	/**
	 * e hoch x
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double ex(double value) {
		return Math.pow(Math.E, value);
	}
	
	/**
	 * 10 hoch x
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double expf(double value) {
		return Math.pow(10, value);
	}
	
	/**
	 * dekadischer Logarithmus
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double log(double value) {
		return Math.log10(value);
	}
	
	/**
	 * natürlicher Logarithmus
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double ln(double value) {
		return Math.log(value);
	}
	
	/**
	 * Umrechnung von Winkelgrad in Winkelsekunde
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double dms(double value){
		double result = Math.floor(value);
		value -= result;
		value *= 60;
		double temp = Math.floor(value);
		value -= temp;
		result += temp/100;
		return result + value * 0.006;
	}
	
	/**
	 * Umrechnung von Winkelsekunde in Winkelgrad
	 * 
	 * @param value Operand
	 * @return Ergebnis
	 */
	private static double deg(double value){
		double result = Math.floor(value);
		value -= result;
		value *= 100;
		double temp = Math.floor(value);
		value -= temp;
		result += temp/60;
		return result + value / 36;
	}
	
	/**
	 * Diese Methode ist für die Berechnung von binären Operatoren zuständig.
	 * 
	 * @param identifier ermöglicht Identifizierung des Operators
	 * @param value1 erster Operand
	 * @param value2 zweiter Operand
	 * @return Ergebnis der Berechnung
	 * @throws MathException wird beim Teilen durch 0 geworfen
	 */
	public static double binaryOperation(GridActions identifier, double value1, double value2) throws MathException{
		switch(identifier){
		case ADDITION: 
			return addition(value1, value2);
		case SUBTRACT:
			return subtract(value1, value2);
		case MULTIPLY: 
			return multiply(value1, value2);
		case DIVIDE:
			return divide(value1, value2);
		case XPOWY:
			return xPowY(value1, value2);
		case YSQRT:
			return ySqrt(value1, value2);
		case EXP:
			return exp(value1, value2);
		case MOD:
			return mod(value1, value2);
		default:
			return -1;
		}
	}

	private static double addition(double summand1, double summand2){
		return summand1 + summand2;
	}
	
	private static double subtract(double minuend, double subtrahend) {
		return minuend - subtrahend;
	}
	
	private static double multiply(double factor1, double factor2){
		return factor1 * factor2;
	}
	
	/**
	 * Division
	 * 
	 * @param dividend
	 * @param divisor
	 * @return Ergebnis
	 * @throws MathException wird ausgelöst, falls der Divisor 0 ist
	 */
	private static double divide(double dividend, double divisor) throws MathException {
		if(divisor == 0) {
			throw new MathException("Teilen durch 0 ist nicht möglich");
		}
		return dividend / divisor;
	}
	
	/**
	 * Exponentialfunktion
	 * 
	 * @param x Radikand
	 * @param y Exponent
	 * @return Ergebnis
	 * @throws MathException wird ausgelöst, wenn der Radikand negativ ist
	 */
	private static double xPowY(double x, double y) throws MathException {
		if(y < 0) throw new MathException("Der Radikand darf nicht negativ sein.");
		return Math.pow(x, y);
	}
	
	/**
	 * y-Wurzel
	 * 
	 * @param x Basis
	 * @param y Wurzelgrad
	 * @return Ergebnis
	 * @throws MathException wird ausgelöst, falls der Wurzelgrad 0 ist
	 */
	private static double ySqrt(double x, double y) throws MathException {
		return Math.pow(x, divide(1, y));
	}
	
	/**
	 * exp-Funktion, erweitert eine Zahl x um y Stellen.
	 * 
	 * @param x Wert
	 * @param y Anzahl Stellen um die erhöht werden soll
	 * @return berechneter Wert
	 */
	private static double exp(double x, double y) {
		return x*Math.pow(10, y);
	}
	
	/**
	 * Modulo/Restberechnung
	 * 
	 * @param dividend Dividend
	 * @param divisor Divisor
	 * @return Rest
	 * @throws MathException wird geworfen, wenn der Divisor 0 ist
	 */
	private static double mod(double dividend, double divisor) throws MathException {
		if(divisor == 0) {
			throw new MathException("Teilen durch 0 ist nicht möglich");
		}
		return dividend % divisor;
	}
	
	public static MathFunction getInstance() {
        if (ownInstance == null) {
            ownInstance = new MathFunction();
        }
        return ownInstance;
    }
}
