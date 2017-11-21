package de.se.trechner.model.elements;

import de.se.trechner.model.ActionCmds;

public abstract class Operator extends Element {
	private ActionCmds a;

	public Operator(String stringRepresentation, ActionCmds a) {
		super(stringRepresentation);
		this.a = a;
	}

	public ActionCmds getIdentifier() {
		return a;
	}
}