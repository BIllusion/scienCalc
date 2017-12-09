package de.se.trechner.view;

import de.se.trechner.interfaces.DisplayInterface;
import de.se.trechner.model.CSSNodeIDs;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Display extends VBox implements DisplayInterface {
    private Label bigMsgBox;
    private Label smallMsgBox;

    public Display() {
        super();

        this.setFillWidth(true);
        this.setAlignment(Pos.CENTER_RIGHT);

        smallMsgBox = new Label();
        smallMsgBox.setId(CSSNodeIDs.SMALLMSGBOX);
        smallMsgBox.setPrefWidth(Double.MAX_VALUE);
        smallMsgBox.setAlignment(Pos.BOTTOM_RIGHT);
        smallMsgBox.setFocusTraversable(true);
        smallMsgBox.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> smallMsgBox.requestFocus());

        bigMsgBox = new Label();
        bigMsgBox.setId(CSSNodeIDs.BIGMSGBOX);
        bigMsgBox.setPrefWidth(Double.MAX_VALUE);
        bigMsgBox.setAlignment(Pos.CENTER_RIGHT);
        bigMsgBox.setFocusTraversable(true);
        bigMsgBox.setTextOverrun(OverrunStyle.LEADING_ELLIPSIS);
        bigMsgBox.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> bigMsgBox.requestFocus());

        this.getChildren().addAll(smallMsgBox, bigMsgBox);

        ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> updateLabelHeight();

        this.widthProperty().addListener(resizeListener);
        this.heightProperty().addListener(resizeListener);

    }

    private void updateLabelHeight() {
        bigMsgBox.setPrefHeight(this.getHeight()*(2.0/3.0));
        smallMsgBox.setPrefHeight(this.getHeight()*(1.0/3.0));
        updateFontSize();
    }

    private void updateFontSize() {
        if (this.getHeight() > 0.0 ) {

            // Update Font-Size on Labels
            smallMsgBox.setFont(Font.font("Lato", FontWeight.NORMAL, (this.getHeight()*(1.0/3.0)) / 2.0));
            bigMsgBox.setFont(Font.font("Lato", FontWeight.NORMAL, (this.getHeight()*(2.0/3.0)) / 2.0));
        }

    }

    public String getSmallMsgBox(){
        return smallMsgBox.getText();
    }

    public String getBigMsgBox() {
        return bigMsgBox.getText();
    }

    public void setSmallMsgBox(String s) {
        smallMsgBox.setText(s);
    }

    public void setBigMsgBox(String s) {
        bigMsgBox.setText(s);
    }

    public void setFocus() {
        bigMsgBox.requestFocus();
    }

}
