package de.se.trechner.model.elements;

/**
 * Diese Klasse ist die Oberklasse für alle Elemente eines Terms. 
 * Sie dient als abstrakte Vorlage für alle Elemente und schafft Typsicherheit.
 * 
 * @author wojke_n
 * @version 2017-11-25
 * @see de.se.trechner.model.Term
 * @see de.se.trechner.model.elements
 */
public abstract class Element {
	protected String stringRepresentation;

	/**
	 * Der Konstruktor initialisiert stringRepresentation und muss deshalb von allen Unterklassen aufgerufen werden.
	 * 
	 * @param stringRepresentation Der String repräsentiert das entsprechende Element
	 */
	public Element(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}
	
	/**
	 * @return den String den das Element repräsentiert.
	 */
	public String toString() {
		return stringRepresentation;
	}
}