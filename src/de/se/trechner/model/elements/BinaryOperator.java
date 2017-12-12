package de.se.trechner.model.elements;

import de.se.trechner.model.GridActions;

/**
 * Diese Klasse steht für die binären Operatoren eines Terms.
 * 
 * @author wojke_n
 * @version 2017-12-09
 * @see Operator
 * @see de.se.trechner.model.Term
 */
public class BinaryOperator extends Operator{
	private int rank;

	/**
	 * Konstruktor ermittelt anhand des identifiers den passenden Operator
	 * und ermittelt dazu den passenden Rang.
	 * 
	 * @param identifier ermöglicht die eindeutige Identifizierung des Operators
	 */
	public BinaryOperator(GridActions identifier) {
		super(identifier);
		rank = getRank(identifier);
	}

	/**
	 * Diese Methode vergleicht zwei binäre Operatoren, um festzustellen, welcher die höhere Priorität hat.
	 * 
	 * @param b binärer Operator mit dem verglichen werden soll.
	 * @return true, wenn dieser Operator eine höhere oder gleich hohe Priorität hat, wie der Übergebene
	 */
	public boolean isPrioritised(BinaryOperator b) {
		return (rank >= b.rank);
	}
	
	/**
	 * Diese Methode ordnet einen Operator einen Rang zu.
	 * 
	 * @param identifier ermöglicht die Identifizierung des Operators
	 * @return int-Wert, der die Priorität des Operators darstellt, hohe Zahl-> hohe Priorität
	 */
	private int getRank(GridActions identifier){
		switch(identifier){
		case ADDITION:
		case SUBTRACT:
			return 1;
		case MULTIPLY:
		case DIVIDE:
			return 2;
		case XPOWY:
		case YSQRT:
		case EXP:
		case MOD:
			return 3;
		default:
			return -1;
		}
	}
}