package de.se.trechner.model.elements;

public class UnaryOperator extends Operator{
	private String representationEnd;

	public UnaryOperator(int identifier) {
		super(getRepresentation(identifier), identifier);
		representationEnd = getRepresentationEnd(identifier);
	}
	
	public String toString(String content) {
		return stringRepresentation + content + representationEnd;
	}
	
	private String getRepresentationEnd(int identifier) {
		switch(identifier) {
		case 10:
			return ")";
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
			return ")";
		case 19:
			return "²";
		case 22:
			return ")";
		case 24:
			return ")";
		case 26:
			return "!";
		case 29:
		case 30:
			return ")";
		}
		return null;
	}
	
	private static String getRepresentation(int identifier){
		switch(identifier) {
		case 10:
			return "negate(";
		case 13:
			return "sin(";
		case 14:
			return "asin(";
		case 15:
			return "cos(";
		case 16:
			return "acos(";
		case 17:
			return "tan(";
		case 18:
			return "atan(";
		case 19:
			return "";
		case 22:
			return "sqrt(";
		case 24:
			return "reciproc(";
		case 26:
			return "";
		case 29:
			return "log(";
		case 30:
			return "log(";
		}
		return null;
	}
}