package de.se.trechner.model;

import java.util.ArrayList;

import de.se.trechner.model.elements.BinaryOperator;
import de.se.trechner.model.elements.Bracket;
import de.se.trechner.model.elements.Element;
import de.se.trechner.model.elements.Number;
import de.se.trechner.model.elements.UnaryOperator;

/**
 * Diese Klasse enthält den beim Taschrechner eingegebenen Term.
 * Die Elemente des Terms sind in einer ArrayList gespeichert.
 * 
 * @author wojke_n
 * @version 2017-11-28
 * @see Element
 */
public class Term {
	private ArrayList<Element> elements;
	private int openBracketsCounter;
	private int outputIndex;

	/**
	 * Konstruktor der lediglich initialize() ausführt.
	 * 
	 * @see #initialize()
	 */
	public Term() {
		initialize();
	}
	
	/**
	 * Diese Methode erzeugt eine neue ArrayList<Element>.
	 * Außerdem setzt sie den Klammernzähler auf 0.
	 */
	public void initialize() {
		elements = new ArrayList<Element>();
		openBracketsCounter = 0;
	}
	
	/**
	 * Diese Methode gibt den String für den Term aus.
	 * 
	 * @return ein String, der den Term repräsentiert.
	 */
	public String toString(){
		String output = "";
		Element e;
		for(outputIndex = elements.size()-1; outputIndex >= 0; outputIndex--){
			e = elements.get(outputIndex);
			if(e instanceof UnaryOperator){
				outputIndex--;
				output = ((UnaryOperator) e).toString(getContentFor()) + output;
				outputIndex++;
			}else{
				output = e.toString() + output;
			}
		}
		return output;
	}
	
	/**
	 * Diese Methode gibt den Inhalt einer unären Operation als String zurück.
	 * Da bei unären Operatoren ein oder mehrere Elemente umschloßen werden,
	 * ist bei unären Operatoren die rekursive Durchführung dieser Methode notwendig.
	 * 
	 * @return ein String, der den Inhalt einer unären Operation enthält.
	 */
	private String getContentFor() {
		String output = "";
		int openBracket = -1;
		Element e = elements.get(outputIndex);
		outputIndex--;
		if(e instanceof Number) {
			return e.toString();
		}
		if(e instanceof UnaryOperator) {
			return ((UnaryOperator) e).toString(getContentFor());
		}
		output = e.toString();
		for(; openBracket != 0; outputIndex--) {
			e = elements.get(outputIndex);
			if(e instanceof UnaryOperator) {
				outputIndex--;
				output = ((UnaryOperator) e).toString(getContentFor()) + output;
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
	
	/**
	 * Berechnet das Ergebnis des Terms.
	 * 
	 * @return das Ergebnis des Terms
	 * @throws Exception wird ausgelöst, wenn durch 0 geteilt wird.
	 */
	public double solve() throws Exception {
		return solve(elements, 0);
	}
	
	/**
	 * Berechnet das Ergebnis des Terms ab den startIndex.
	 * 
	 * @param term den Term der berechnet werden soll.
	 * @param startIndex ab dieser stelle wird gerechnet
	 * @return das Ergebnis der Berechnung
	 * @throws Exception wird ausgelöst, wenn durch 0 geteilt wird.
	 */
	public double solve(ArrayList<Element> term, int startIndex) throws Exception{
		int index = 0;
		for(int i=startIndex; i<term.size(); i++){
			if(term.get(i) instanceof Bracket){
				if(((Bracket) term.get(i)).isOpen()) index = i+1;
				else{
					solve(term, index, i-1);
					term.remove(index - 1);
					term.remove(index);
					i = startIndex-1;
				}
			}
		}
		
		solve(term, startIndex, term.size()-1);
		
		return ((Number) term.get(startIndex)).getValue();
	}
	
	/**
	 * Berechnet den Teil des Terms zwischen index1 und index2. 
	 * Wird hauptsächlich genutzt, um den Inhalt von Klammern zu bestimmen.
	 * 
	 * @param term den Term der berechnet werden soll.
	 * @param index1 Anfangsindex
	 * @param index2 Endindex
	 * @throws Exception wird ausgelöst, wenn durch 0 geteilt wird.
	 */
	private void solve(ArrayList<Element> term, int index1, int index2) throws Exception{
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
		int index = -1; // markiert den Index der bin�ren Operation mit dem h�chsten Rang
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
	
	/**
	 * Fügt den Term eine Zahl hinzu.
	 * 
	 * @param num die Zahl, die hinzugefügt werden soll
	 */
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
	
	/**
	 * Fügt dem Term einen unären Operator hinzu.
	 * Außerdem wird auch direkt das Ergebnis der unären Operation zurückgegeben.
	 * 
	 * @param identifier dient der Identifizierung des Operators.
	 * @return ein double-Wert, der das Ergebnis der unären Operation entspricht.
	 * @throws Exception 
	 */
	public double addUnaryOperator(ActionCmds identifier) throws Exception{
		if(elements.isEmpty()) elements.add(new Number(0));
		Element lastElement = elements.get(elements.size()-1);
		if((lastElement instanceof Bracket && ((Bracket) lastElement).isOpen()) 
				|| lastElement instanceof BinaryOperator) {
			elements.add(new Number(0));
		}
		elements.add(new UnaryOperator(identifier));
		return preSolve();
	}
	
	/**
	 * Berechnet eine unäre Operation.
	 * 
	 * @return das Ergebnis der Berechnung
	 * @throws Exception
	 */
	private double preSolve() throws Exception {
		int bracketCounter = 0;
		ArrayList<Element> clone = getClone();
		Element e;
		for(int i = clone.size()-2; i>=0 ; i--) {
			e = clone.get(i);
			if(e instanceof Number && bracketCounter == 0) {
				return solve(clone, i);
			}
			if(e instanceof Bracket) {
				if(((Bracket) e).isOpen()) {
					bracketCounter++;
					if(bracketCounter == 0) return solve(clone, i);
				}else {
					bracketCounter--;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Fügt den Term einen binären Operator hinzu.
	 * 
	 * @param identifier dient der Identifizierung des Operators.
	 */
	public void addBinaryOperator(ActionCmds identifier){
		if(elements.isEmpty()) elements.add(new Number(0));
		else {
			Element lastElement = elements.get(elements.size()-1);
			if(lastElement instanceof Bracket && ((Bracket) lastElement).isOpen()) {
				elements.add(new Number(0));
			}else {
				if(lastElement instanceof BinaryOperator) {
					elements.remove(lastElement);
				}
			}
		}
		elements.add(new BinaryOperator(identifier));
	}
	
	/**
	 * Fügt den Term eine Klammer hinzu.
	 * 
	 * @param open true, wenn die neue Klammer eine offene ist.
	 * @return true, wenn das Hinzufügen geklappt hat.
	 */
	public boolean addBracket(boolean open){
		if(!elements.isEmpty()) {
			Element lastElement = elements.get(elements.size()-1);
			if(open) {
				if(lastElement instanceof UnaryOperator) return false;
				if(lastElement instanceof Bracket && !((Bracket) lastElement).isOpen()) return false;
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
	
	/**
	 * Diese Methode erschafft einen Clon der Arrayliste und gibt diese zurück.
	 * 
	 * @return ein Clon der ArrayListe dieses Terms.
	 */
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