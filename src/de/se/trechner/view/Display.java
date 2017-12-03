package de.se.trechner.view;

import de.se.trechner.controller.TextListener;
import de.se.trechner.interfaces.DisplayInterface;
import de.se.trechner.interfaces.FrameInterface;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Display extends VBox implements DisplayInterface {
    private Label bigMsgBox;
    private Label smallMsgBox;
    private TextField bigBox;

    public Display(Double height, FrameInterface fi) {
        super();
        this.setId("Display");
        this.setPrefHeight(height);
        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setFillWidth(true);
        this.setAlignment(Pos.CENTER_RIGHT);

        smallMsgBox = new Label();
        smallMsgBox.setText("Hallo Welt");
       // smallMsgBox.setStyle("-fx-background-color: #00FF00;"); //nur debugging
        smallMsgBox.setPrefHeight(height*(1.0/3.0));
        smallMsgBox.setPrefWidth(Double.MAX_VALUE);
        smallMsgBox.setAlignment(Pos.BOTTOM_RIGHT);
        smallMsgBox.setFocusTraversable(true);
        smallMsgBox.getStylesheets().add("resources/css/DisplayStyles.css");

        bigBox = new TextField();
        bigBox.setAlignment(Pos.BOTTOM_RIGHT);
        bigBox.setPrefHeight(height*(2.0/3.0));
        bigBox.textProperty().addListener(TextListener.getInstance(fi));


        bigMsgBox = new Label();
        bigMsgBox.setId("BigMsgBox");
        bigMsgBox.setText("Hallo Welt Hallo Welt Ha");
        //bigMsgBox.setStyle("-fx-background-color: #FF0000;"); //nur debugging
        bigMsgBox.setPrefHeight(height*(2.0/3.0));
        bigMsgBox.setPrefWidth(Double.MAX_VALUE);
        bigMsgBox.setAlignment(Pos.CENTER_RIGHT);
        bigMsgBox.setFocusTraversable(true);
        bigMsgBox.getStylesheets().add("resources/css/DisplayStyles.css");
        bigMsgBox.setTextOverrun(OverrunStyle.LEADING_ELLIPSIS);

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
        	Font f1 = new Font("Lato", (this.getHeight()*(1.0/3.0)) / 2.0);
            Font f2 = new Font("Lato", (this.getHeight()*(2.0/3.0)) / 2.0);

            // Update Font-Size on Labels
            smallMsgBox.setFont(f1);
            bigMsgBox.setFont(f2);
            bigBox.setFont(f2);
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
