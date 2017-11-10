package de.se.trechner.model.elements;

public class BinaryOperator extends Operator{
	private int rank;

	public BinaryOperator(int identifier) {
		super(getRepresentation(identifier), identifier);
		rank = getRank(identifier);
	}

	public boolean isPrioritised(BinaryOperator b) {
		return (rank >= b.rank);
	}
	
	private int getRank(int identifier){
		switch(identifier){
		case 2:
		case 3:
			return 4;
		case 4:
		case 5:
			return 5;
		}
		return -1;
	}
	
	private static String getRepresentation(int identifier){
		switch(identifier){
		case 2:
			return "+";
		case 3:
			return "-";
		case 4:
			return "*";
		case 5:
			return "/";
		}
		return null;
	}
}