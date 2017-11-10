package scienCalc.view;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import scienCalc.Main;


public class Display extends VBox {
    private Label bigMsgBox;
    private Label smallMsgBox;
    private Font testFont;

    public Display(Double height) {
        super();


        Font.loadFont(Main.class.getResource("SpectralSC-Regular.ttf").toExternalForm(),20);

        this.setPrefHeight(height);
        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setFillWidth(true);
        this.setAlignment(Pos.CENTER_RIGHT);

        smallMsgBox = new Label();
        smallMsgBox.setText("Hallo Welt");
       // smallMsgBox.setStyle("-fx-background-color: #00FF00;"); //nur debugging
        smallMsgBox.setPrefHeight(height*(1.0/3.0));
        smallMsgBox.setAlignment(Pos.BOTTOM_RIGHT);

        bigMsgBox = new Label();
        bigMsgBox.setText("Hallo Welt Hallo Welt Ha");
        testFont = new Font("Spectral SC", 80);
        bigMsgBox.setFont(testFont);
       // bigMsgBox.setStyle("-fx-font-family: \"TRON\";"); //nur debugging
       // bigMsgBox.setStyle("-fx-font-size: 80px;"); //nur debugging
        //bigMsgBox.setStyle("-fx-background-color: #FF0000;"); //nur debugging
        bigMsgBox.setPrefHeight(height*(2.0/3.0));

        this.getChildren().addAll(smallMsgBox, bigMsgBox);

        ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> updateLabelHeight();

        this.widthProperty().addListener(resizeListener);
        this.heightProperty().addListener(resizeListener);

    }

    private void updateLabelHeight() {
        bigMsgBox.setPrefHeight(this.getHeight()*(2.0/3.0));
        smallMsgBox.setPrefHeight(this.getHeight()*(1.0/3.0));
        //updateFontSize();
    }

    private void updateFontSize() {
        if (this.getHeight() > 0.0 ) {
            Font f1 = new Font("Lato", smallMsgBox.getHeight() / 2.0);
            Font f2 = new Font("Lato", bigMsgBox.getHeight() / 2.0);

            // Update Font-Size on Labels
            smallMsgBox.setFont(f1);
            bigMsgBox.setFont(f2);
        }

    }

    public void setBigMsg(String msg) {
        bigMsgBox.setText(msg);
    }

    public void setSmallMsg(String msg) {
        smallMsgBox.setText(msg);
    }

}
