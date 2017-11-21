package de.se.trechner.model;

public abstract class MathFunction {
	public static final double PI = Math.PI;
	
	public static double unaryOperation(ActionCmds identifier, double value){
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
		}
		return -1;
	}
	
	private static double signChange(double value) {
		return -value;
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
		return Math.pow(value, 2);
	}
	
	private static double cubic(double value) {
		return Math.pow(value, 3);
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
	
	private static double ex(double value) {
		return Math.pow(Math.E, value);
	}
	
	private static double expf(double value) {
		return Math.pow(10, value);
	}
	
	private static double log(double value) {
		return Math.log10(value);
	}
	
	private static double ln(double value) {
		return Math.log(value);
	}
	
	public static double binaryOperation(ActionCmds identifier, double value1, double value2){
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
	
	private static double xPowY(double x, double y) {
		return Math.pow(x, y);
	}
	
	private static double ySqrt(double x, double y) {
		return Math.pow(x, 1/y);
	}
	
	private static double exp(double x, double y) {
		return x*Math.pow(10, y);
	}
	
	private static double mod(double x, double y) {
		return x%y;
	}
}
