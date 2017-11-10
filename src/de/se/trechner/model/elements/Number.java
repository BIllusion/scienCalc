package de.se.trechner.model.elements;

public class Number extends Element{
	private double value;

	public Number(double value) {
		super(getRepresentation(value));
		this.value = value;
	}

	public double getValue() {
		return value;
	}
	
	public void setValue(double value){
		this.value = value;
		this.stringRepresentation = getRepresentation(value);
	}
	
	private static String getRepresentation(double value){
		if(value == (long) value)
			return String.format("%d", (long) value);
		return String.format("%s", value);
	}
}
