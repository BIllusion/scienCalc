package de.se.trechner.model.elements;

public abstract class Element {
	protected String stringRepresentation;

	public Element(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}
	
	public String toString() {
		return stringRepresentation;
	}
}