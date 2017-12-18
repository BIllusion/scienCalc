package de.se.trechner.interfaces;

/**
 * Definiert die Schnittstellen für den externen Zugriff auf zweigeteilte Ausgabe Komponenten.
 *
 * @author ruess_c
 * @version 2017-12-16
 */
public interface DisplayInterface {

    /**
     * Gibt den Inhalt der kleinen Ausgabezeile zurück
     *
     * @return Text der kleinen Ausgabezeile
     */
    public String getSmallMsgBox();

    /**
     * Gibt den Inhalt der großen Ausgabezeile zurück
     *
     * @return Text der großen Ausgabezeile
     */
    public String getBigMsgBox();

    /**
     * Setzt den Inhalt der kleinen Ausgabezeile auf einen Wert
     *
     * @param s neuer Text
     */
    public void setSmallMsgBox(String s);

    /**
     * Setzt den Inhalt der großen Ausgabezeile auf einen Wert
     *
     * @param s neuer Text
     */
    public void setBigMsgBox(String s);

    /**
     * Anfrage um den Fokus auf die Komponente zu setzen
     *
     */
    public void requestFocus();

}
