package de.se.trechner.model.elements;

import de.se.trechner.model.GridActions;

/**
 * Diese abstrakte Klasse erbt von Element und fasst die gemeinsamen 
 * Eigenschaften des Unäroperators und des Binäroperators zusammen.
 * 
 * @author wojke_n
 * @version 2017-12-09
 * @see Element
 * @see UnaryOperator
 * @see BinaryOperator
 */
public abstract class Operator extends Element {
	private GridActions identifier;

	/**
	 * Konstruktor erhält identifier, speichert diesen und 
	 * bestimmt damit den passenden String.
	 *
	 * @param identifier ermöglicht die Identifizierung des Operators
	 */
	public Operator(GridActions identifier) {
		super(getRepresentation(identifier));
		this.identifier = identifier;
	}

	/**
	 * Einfache Getter-Methode für den identifier.
	 * 
	 * @return identifier ermöglicht die eindeutige Identifizierung des Operators.
	 */
	public GridActions getIdentifier() {
		return identifier;
	}
	
	/**
	 * Ordnet dem Identifier des Operators den passenden String zu.
	 * 
	 * @param identifier ermöglicht Identifizierung des Operators
	 * @return String, der den Operator repräsentiert.
	 */
	private static String getRepresentation(GridActions identifier){
		switch(identifier) {
		case SIGNCHANGE:
			return "negate(";
		case SIN:
			return "sin(";
		case ARCSIN:
			return "asin(";
		case COS:
			return "cos(";
		case ARCCOS:
			return "acos(";
		case TAN:
			return "tan(";
		case ARCTAN:
			return "atan(";
		case SQR:
		case CUBIC:
			return "";
		case SQRT:
			return "sqrt(";
		case RECVAL:
			return "reciproc(";
		case FACT:
			return "";
		case EX:
			return "e^";
		case EXPF:
			return "10^(";
		case LOG:
			return "log(";
		case LN:
			return "ln(";
		case DMS:
			return "dms(";
		case DEG:
			return "deg(";
			// Binäre Operatoren:
		case ADDITION:
			return "+";
		case SUBTRACT:
			return "-";
		case MULTIPLY:
			return "*";
		case DIVIDE:
			return "/";
		case XPOWY:
			return "^";
		case YSQRT:
			return " yRoot ";
		case EXP:
			return ", e+";
		case MOD:
			return " mod ";
		default:
			return null;
		}
	}
}