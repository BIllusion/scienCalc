package de.se.trechner.model;

public abstract class MathFunction {
	public static final double PI = Math.PI;
	
	public static double unaryOperation(int identifier, double value){
		switch(identifier){
		case 10:
			return signChange(value);
		case 13:
			return sin(value);
		case 14:
			return arcSin(value);
		case 15:
			return cos(value);
		case 16:
			return arcCos(value);
		case 17:
			return tan(value);
		case 18:
			return arcTan(value);
		case 19:
			return sqr(value);
		case 22:
			return sqrt(value);
		case 24:
			return recVal(value);
		case 26:
			return fact(value);
		case 29:
			return log(value);
		case 30:
			return ln(value);
		}
		return -1;
	}
	
	private static double signChange(double value) {
		return value * (-1);
	}
	
	private static double sin(double value) {
		return Math.sin(Math.toRadians(value));
	}
	
	private static double arcSin(double value) {
		return Math.toDegrees(Math.asin(value));
	}
	
	private static double cos(double value) {
		return Math.cos(Math.toRadians(value));
	}
	
	private static double arcCos(double value) {
		return Math.toDegrees(Math.acos(value));
	}
	
	private static double tan(double value) {
		return Math.tan(Math.toRadians(value));
	}
	
	private static double arcTan(double value) {
		return Math.tan(Math.toRadians(value));
	}
	
	private static double sqr(double value){
		return value * value;
	}
	
	private static double sqrt(double value) {
		return Math.sqrt(value);
	}
	
	private static double recVal(double value) {
		return 1/value;
	}
	
	private static double fact(double value) {
		long product = 1;
		for(int i=2; i <= value; i++) product *= i;
		return product;
	}
	
	private static double log(double value) {
		return Math.log10(value);
	}
	
	private static double ln(double value) {
		return Math.log(value);
	}
	
	public static double binaryOperation(int identifier, double value1, double value2){
		switch(identifier){
		case 2: 
			return addition(value1, value2);
		case 3:
			return subtract(value1, value2);
		case 4: 
			return multiply(value1, value2);
		case 5:
			return divide(value1, value2);
		}
		return -1;
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
	
	private static double divide(double dividend, double divisor) {
		return dividend / divisor;
	}
}
