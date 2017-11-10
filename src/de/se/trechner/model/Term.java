package de.se.trechner.model;

import java.util.ArrayList;

import de.se.trechner.model.elements.BinaryOperator;
import de.se.trechner.model.elements.Bracket;
import de.se.trechner.model.elements.Element;
import de.se.trechner.model.elements.Number;
import de.se.trechner.model.elements.UnaryOperator;

public class Term {
	private ArrayList<Element> elements;
	private int openBracketsCounter;
	private int outputIndex;

	public Term() {
		initialize();
	}
	
	public void initialize() {
		elements = new ArrayList<Element>();
		openBracketsCounter = 0;
	}
	
	public String toString(){
		String output = "";
		Element e;
		for(outputIndex = elements.size()-1; outputIndex >= 0; outputIndex--){
			e = elements.get(outputIndex);
			if(e instanceof UnaryOperator){
				outputIndex--;
				output = ((UnaryOperator) e).toString(getContentFor((UnaryOperator) e)) + output;
				outputIndex++;
			}else{
				output = e.toString() + output;
			}
		}
		if(output.equals("")) return "";
		return output;
	}
	
	private String getContentFor(UnaryOperator uo) {
		String output = "";
		int openBracket = -1;
		Element e = elements.get(outputIndex);
		outputIndex--;
		if(e instanceof Number) {
			return e.toString();
		}
		if(e instanceof UnaryOperator) {
			return uo.toString(getContentFor((UnaryOperator) e));
		}
		output = e.toString();
		for(; openBracket != 0; outputIndex--) {
			e = elements.get(outputIndex);
			if(e instanceof UnaryOperator) {
				outputIndex--;
				output = ((UnaryOperator) e).toString(getContentFor((UnaryOperator) e)) + output;
				outputIndex++;
			}else {
				if(e instanceof Bracket) {
					if(((Bracket) e).isOpen()) {
						openBracket++;
					}else {
						openBracket--;
					}
				}
				output = e.toString() + output;
			}
		}
		return output;
	}
	
	public double solve(){
		ArrayList<Element> clone = getClone();
		int index = 0;
		for(int i=0; i<clone.size(); i++){
			if(clone.get(i) instanceof Bracket){
				if(((Bracket) clone.get(i)).isOpen()) index = i+1;
				else{
					solve(clone, index, i-1);
					clone.remove(index - 1);
					clone.remove(index);
					i = -1;
				}
			}
		}
		
		solve(clone, 0, clone.size()-1);
		
		return ((Number) clone.get(0)).getValue();
	}
	
	private void solve(ArrayList<Element> term, int index1, int index2){
		Number n, n2;
		UnaryOperator u;
		for(int i=1; index1+i <= index2; i++){
			if(term.get(index1 + i) instanceof UnaryOperator){
				u = (UnaryOperator) term.get(index1+i);
				n = (Number) term.get(index1+i-1);
				n.setValue(MathFunction.unaryOperation(u.getIdentifier(), n.getValue()));
				term.remove(index1+i);
				index2--;
				i--;
			}
		}
		int index = -1; // markiert den Index der binären Operation mit dem höchsten Rang
		BinaryOperator b = null;
		for(int i=1; index1 != index2; i += 2){
			if(index == -1){
				index = index1+i;
				b = (BinaryOperator) term.get(index);
			}else{
				if(index1+i > index2) {
					n = (Number) term.get(index-1);
					n2 = (Number) term.get(index+1);
					n.setValue(MathFunction.binaryOperation(b.getIdentifier(), n.getValue(), n2.getValue()));
					term.remove(index);
					term.remove(index);
					index2 -= 2;
					i = -1;
					index = -1;
				}else {
					if(b.isPrioritised((BinaryOperator) term.get(index1+i))){
						n = (Number) term.get(index-1);
						n2 = (Number) term.get(index+1);
						n.setValue(MathFunction.binaryOperation(b.getIdentifier(), n.getValue(), n2.getValue()));
						term.remove(index);
						term.remove(index);
						index2 -= 2;
						i = -1;
						index = -1;
					}else{
						index = index1+i;
						b = (BinaryOperator) term.get(index);
					}
				}
			}
		}
	}
	
	public void addNumber(double num) {
		if(!elements.isEmpty()) {
			Element lastElement = elements.get(elements.size()-1);
			if((lastElement instanceof Bracket && !((Bracket) lastElement).isOpen()) 
					|| lastElement instanceof UnaryOperator) {
				initialize();
			}
		}
		elements.add(new Number(num));
	}
	
	public void addUnaryOperator(int identifier){
		if(elements.isEmpty()) elements.add(new Number(0));
		Element lastElement = elements.get(elements.size()-1);
		if((lastElement instanceof Bracket && ((Bracket) lastElement).isOpen()) 
				|| lastElement instanceof BinaryOperator) {
			elements.add(new Number(0));
		}
		elements.add(new UnaryOperator(identifier));
	}
	
	public void addBinaryOperator(int identifier){
		if(elements.isEmpty()) elements.add(new Number(0));
		else {
			Element lastElement = elements.get(elements.size()-1);
			if(lastElement instanceof Bracket && ((Bracket) lastElement).isOpen()) {
				elements.add(new Number(0));
			}
		}
		elements.add(new BinaryOperator(identifier));
	}
	
	public boolean addBracket(boolean open){
		if(!elements.isEmpty()) {
			Element lastElement = elements.get(elements.size()-1);
			if(open) {
				if(lastElement instanceof UnaryOperator) return false;
				if(!((Bracket) lastElement).isOpen()) return false;
				if(lastElement instanceof Number) {
					elements.remove(elements.size()-1);
				}
			}else {
				if(lastElement instanceof Bracket && ((Bracket) lastElement).isOpen()) {
					elements.remove(elements.size()-1);
					return false;
				}
				if(lastElement instanceof BinaryOperator) {
					elements.remove(elements.size()-1);
				}
			}
		}
		if(open){
			elements.add(new Bracket(open));
			openBracketsCounter++;
		}else{
			if(openBracketsCounter == 0){
				return false;
			}
			elements.add(new Bracket(open));
			openBracketsCounter--;
		}
		return true;
	}
	
	private ArrayList<Element> getClone(){
		ArrayList<Element> clone = new ArrayList<Element>();
		for(Element e: elements){
			if(e instanceof Number) 
				clone.add(new Number(((Number) e).getValue()));
			else {
				if(e instanceof UnaryOperator)
					clone.add(new UnaryOperator(((UnaryOperator) e).getIdentifier()));
				else {
					if(e instanceof BinaryOperator)
						clone.add(new BinaryOperator(((BinaryOperator) e).getIdentifier()));
					else
						clone.add(new Bracket(((Bracket) e).isOpen()));
				}
			}
		}
		return clone;
	}
}