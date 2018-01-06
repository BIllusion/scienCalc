package de.se.trechner.view;

import de.se.trechner.interfaces.DisplayInterface;
import de.se.trechner.model.CSSNodeIDs;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.AccessibleAttribute;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Repräsentiert eine zweigeteilte Ausgabe Komponente
 *
 * @author ruess_c
 * @version 2017-12-16
 * @see de.se.trechner.interfaces.DisplayInterface
 */
public class Display extends VBox implements DisplayInterface {
    private Label bigMsgBox;
    private Label smallMsgBox;

    /**
     * Baut die Komponente auf und definiert die Position der Elemente
     *
     */
    public Display() {
        super();

        // Setup Container
        this.setFillWidth(true);
        this.setAlignment(Pos.CENTER_RIGHT);

        // Setup kleine obere Ausgabezeile
        smallMsgBox = new Label();
        smallMsgBox.setId(CSSNodeIDs.SMALLMSGBOX);
        smallMsgBox.setPrefWidth(Double.MAX_VALUE);
        smallMsgBox.setAlignment(Pos.BOTTOM_RIGHT);
        smallMsgBox.setFocusTraversable(true);
        smallMsgBox.setAccessibleHelp("Rechnungsverlauf");
        smallMsgBox.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> smallMsgBox.requestFocus());

        // Setup große untere Ausgabezeile
        bigMsgBox = new Label();
        bigMsgBox.setId(CSSNodeIDs.BIGMSGBOX);
        bigMsgBox.setPrefWidth(Double.MAX_VALUE);
        bigMsgBox.setAlignment(Pos.CENTER_RIGHT);
        bigMsgBox.setFocusTraversable(true);
        bigMsgBox.setAccessibleHelp("Eingabe & Ergebniszeile");
        bigMsgBox.setTextOverrun(OverrunStyle.LEADING_ELLIPSIS);
        bigMsgBox.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> bigMsgBox.requestFocus());

        this.getChildren().addAll(smallMsgBox, bigMsgBox);

        // Listener für Scaling
        ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> updateLabelHeight();

        this.widthProperty().addListener(resizeListener);
        this.heightProperty().addListener(resizeListener);

    }

    /**
     * Errechnet und setzt die Höhe der beiden Ausgabezeilen im Verhältnis 2 zu 1 relativ zur Höhe der gesamten Komponente
     *
     */
    private void updateLabelHeight() {
        bigMsgBox.setPrefHeight(this.getHeight()*(2.0/3.0));
        smallMsgBox.setPrefHeight(this.getHeight()*(1.0/3.0));
        updateFontSize();
    }

    /**
     * Berechnet die neue Textgröße basiernd auf die der Größe der Ausgabezeilen
     *
     */
    private void updateFontSize() {
        if (this.getHeight() > 0.0 ) {

            // Update Font-Size on Labels
            smallMsgBox.setFont(Font.font("Lato", FontWeight.NORMAL, (this.getHeight()*(1.0/3.0)) / 2.0));
            bigMsgBox.setFont(Font.font("Lato", FontWeight.NORMAL, (this.getHeight()*(2.0/3.0)) / 2.0));
        }

    }

    /**
     * Gibt den Inhalt der kleinen Ausgabezeile zurück
     *
     * @return Text der kleinen Ausgabezeile
     */
    @Override
    public String getSmallMsgBox(){
        return smallMsgBox.getText();
    }

    /**
     * Gibt den Inhalt der großen Ausgabezeile zurück
     *
     * @return Text der großen Ausgabezeile
     */
    @Override
    public String getBigMsgBox() {
        return bigMsgBox.getText();
    }

    /**
     * Setzt den Inhalt der kleinen Ausgabezeile auf einen Wert
     *
     * @param s neuer Text
     */
    @Override
    public void setSmallMsgBox(String s) {
        smallMsgBox.setText(s);
        smallMsgBox.notifyAccessibleAttributeChanged(AccessibleAttribute.TEXT);
    }

    /**
     * Setzt den Inhalt der großen Ausgabezeile auf einen Wert
     *
     * @param s neuer Text
     */
    @Override
    public void setBigMsgBox(String s) {
        bigMsgBox.setText(s);
        bigMsgBox.notifyAccessibleAttributeChanged(AccessibleAttribute.TEXT);
    }

    /**
     * Anfrage um den Fokus auf die Komponente zu setzen
     *
     */
    @Override
    public void requestFocus() {
        bigMsgBox.requestFocus();
    }

}
