package de.se.trechner.model.elements;

import de.se.trechner.model.ActionCmds;

public class UnaryOperator extends Operator{
	private String representationEnd;

	public UnaryOperator(ActionCmds identifier) {
		super(getRepresentation(identifier), identifier);
		representationEnd = getRepresentationEnd(identifier);
	}
	
	public String toString(String content) {
		return stringRepresentation + content + representationEnd;
	}
	
	private String getRepresentationEnd(ActionCmds identifier) {
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
			return ")";
		}
		return null;
	}
	
	private static String getRepresentation(ActionCmds identifier){
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
		}
		return null;
	}
}