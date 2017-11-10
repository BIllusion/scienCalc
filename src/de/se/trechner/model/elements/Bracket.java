package de.se.trechner.model.elements;

public class Bracket extends Element{
	private boolean open;

	public Bracket(boolean open) {
		super(getRepresentation(open));
		this.open = open;
	}

	public boolean isOpen() {
		return open;
	}
	
	private static String getRepresentation(boolean open){
		if(open) return "(";
		return ")";
	}
}