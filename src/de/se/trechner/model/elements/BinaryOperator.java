package de.se.trechner.model.elements;

import de.se.trechner.model.ActionCmds;

public class BinaryOperator extends Operator{
	private int rank;

	public BinaryOperator(ActionCmds identifier) {
		super(getRepresentation(identifier), identifier);
		rank = getRank(identifier);
	}

	public boolean isPrioritised(BinaryOperator b) {
		return (rank >= b.rank);
	}
	
	private int getRank(ActionCmds identifier){
		switch(identifier){
		case ADDITION:
		case SUBTRACT:
			return 1;
		case MULTIPLY:
		case DIVIDE:
			return 2;
		case XPOWY:
		case YSQRT:
		case EXPF:
		case MOD:
			return 3;
		}
		return -1;
	}
	
	private static String getRepresentation(ActionCmds identifier){
		switch(identifier){
		case ADDITION:
			return "+";
		case SUBTRACT:
			return "-";
		case MULTIPLY:
			return "*";
		case DIVIDE:
			return "/";
		case XPOWY:
			return "^";
		case YSQRT:
			return " yRoot ";
		case EXPF:
			return ",e+";
		case MOD:
			return " mod ";
		}
		return null;
	}
}