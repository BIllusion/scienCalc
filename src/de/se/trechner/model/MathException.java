package de.se.trechner.model;

/**
 * Diese Exception soll ausgeführt werden, wenn der Benutzer versucht, einen Term zu berechnen, 
 * der nicht vorgesehen ist, bspw. das Teilen durch 0 oder eine Wurzel mit negativen Radikand
 * 
 * @author wojke_n
 * @version 2017-12-10
 * @see MathFunction
 */
public class MathException extends Exception{
	private static final long serialVersionUID = 1L;

	public MathException(String s) {
		super(s);
	}
}