package de.se.trechner.model.elements;

public abstract class Operator extends Element {
	private int identifier;

	public Operator(String stringRepresentation, int identifier) {
		super(stringRepresentation);
		this.identifier = identifier;
	}

	public int getIdentifier() {
		return identifier;
	}
}