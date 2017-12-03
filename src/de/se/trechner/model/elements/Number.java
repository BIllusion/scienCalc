package de.se.trechner.model.elements;

/**
 * Diese Klasse stellt eine Zahl innerhalb eines Terms dar.
 * Sie erbt von Element und speichert ihren Wert als double.
 * 
 * @author wojke_n
 * @version 2017-11-28
 * @see Element
 * @see de.se.trechner.model.Term
 */
public class Number extends Element{
	private double value;

	/**
	 * Der Konstruktor kriegt einen Wert übergeben, speichert diesen 
	 * und baut daraus einen passenden String.
	 * 
	 * @param value
	 */
	public Number(double value) {
		super(getRepresentation(value));
		this.value = value;
	}

	/**
	 * Normale Getter-Methode für den Wert der Zahl.
	 * 
	 * @return den Wert der Zahl als double
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * Setzt den Wert neu und passt den String an.
	 * 
	 * @param value der neue Wert von Number
	 */
	public void setValue(double value){
		this.value = value;
		this.stringRepresentation = getRepresentation(value);
	}
	
	/**
	 * Diese Methode gibt den passenden String für einen double-Wert zurück.
	 * Dies verhindert beispielsweise, dass 2 als 2.0 dargestellt wird.
	 * 
	 * @param value der double-Wert aus den ein passender String erstellt werden soll.
	 * @return String, der die Zahl repräsentiert.
	 */
	private static String getRepresentation(double value){
		if(value == (long) value)
			return String.format("%d", (long) value);
		return String.format("%s", value);
	}
}
