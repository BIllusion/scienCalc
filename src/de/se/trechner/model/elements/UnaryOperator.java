package de.se.trechner.model.elements;

import de.se.trechner.model.GridActions;

/**
 * Diese Klasse steht für die Unären Operatoren eines Terms.
 * 
 * @author wojke_n
 * @version 2017-12-09
 * @see Operator
 * @see de.se.trechner.model.Term
 */
public class UnaryOperator extends Operator{
	private String representationEnd;

	/**
	 * Konstruktor ermittelt anhand des identifiers den passenden Operator
	 * und ermittelt dazu den passenden String.
	 * 
	 * @param identifier ermöglicht die eindeutige Identifizierung des Operators 
	 */
	public UnaryOperator(GridActions identifier) {
		super(identifier);
		representationEnd = getRepresentationEnd(identifier);
	}
	
	/**
	 * Der unäre Operator benötigt eine spezielle Methode zur Stringausgabe, 
	 * da häufig sowohl vor als auch hinter den Operand der Operator dargestellt wird.
	 * 
	 * @param content Operand
	 * @return Operand mit Operator
	 */
	public String toString(String content) {
		return stringRepresentation + content + representationEnd;
	}
	
	/**
	 * Diese Methode ordnet einen Operator eine String zu,
	 * der den Teil des Operators hinter den Operanden darstellt.
	 * 
	 * @param identifier ermöglicht Identifizierung des Operators
	 * @return String, der den Teil des Operators hinter den Operanden darstellt.
	 */
	private String getRepresentationEnd(GridActions identifier) {
		switch(identifier) {
		case SIGNCHANGE:
			return ")";
		case SIN:
		case ARCSIN:
		case COS:
		case ARCCOS:
		case TAN:
		case ARCTAN:
			return ")";
		case SQR:
			return "²";
		case CUBIC:
			return "³";
		case SQRT:
			return ")";
		case RECVAL:
			return ")";
		case FACT:
			return "!";
		case EX:
			return "";
		case EXPF:
		case LOG:
		case LN:
		case DMS:
		case DEG:
			return ")";
		}
		return null;
	}
}