package de.se.trechner.model.elements;

/**
 * Diese Klasse ist für Klammerelemente eines Terms.
 * 
 * @author wojke_n
 * @version 2017-11-28
 * @see Element
 * @see de.se.trechner.model.Term
 */
public class Bracket extends Element{
	private boolean open;

	/**
	 * Konstruktor speichert den Zustand der Klammer 
	 * und erstellt dazu den passenden String.
	 * 
	 * @param open bei true handelt es sich um eine offene Klammer
	 */
	public Bracket(boolean open) {
		super(getRepresentation(open));
		this.open = open;
	}

	/**
	 * Getter-Methode für den Zustand der Klammer.
	 * 
	 * @return gibt an, ob die Klammer offen (true) ist.
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Diese Methode gibt den passenden String für den Zustand der Klammer zurück.
	 * 
	 * @param open gibt den Zustand der Klammer an
	 * @return String für eine offene Klammer (true) oder eine geschlossene (false)
	 */
	private static String getRepresentation(boolean open){
		if(open) return "(";
		return ")";
	}
}