package de.se.trechner.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.interfaces.GridInterface;
import de.se.trechner.controller.KeyStrokeListener;


public class TRechnerGUI {

    public final static int MAINPADDING = 30;
    public final static int INNERPADDING = 10;

    private AnchorPane baseAnchorPane;
    private VBox outerVBox, innerVBox;
    private HBox gridHBox;
    private ToolBar tBar;
    private Display display;
    private FuncGrid funcGrid;
    private NrGrid nrGrid;
    private FrameInterface fi = new FrameAdapter();
    private Scene sc;

    public TRechnerGUI() {

        // Base Anchor init
        baseAnchorPane = new AnchorPane();
        baseAnchorPane.setPadding(new Insets(MAINPADDING, MAINPADDING, MAINPADDING, MAINPADDING));
        baseAnchorPane.setPrefSize(1024, 768);
        baseAnchorPane.setStyle("-fx-background-color: #303030;");
        sc = new Scene(baseAnchorPane);

        // Einteilung oben unten
        outerVBox = new VBox();
        outerVBox.setFillWidth(true);
        outerVBox.setSpacing(MAINPADDING);

        // Oberer Teil
        innerVBox = new VBox();
        innerVBox.setStyle("-fx-background-color: #FFFFFF;"); //nur debugging

        tBar = new ToolBar(fi);
        display = new Display(100.0,fi);

        innerVBox.setVgrow(display,Priority.ALWAYS);
        innerVBox.getChildren().addAll(tBar, display);


        // Unterer Teil
        gridHBox = new HBox();
        gridHBox.setFillHeight(true);
        gridHBox.setSpacing(MAINPADDING);


        // Button Grids Erstellen
        funcGrid = new FuncGrid(fi);
        nrGrid = new NrGrid(fi);

        // GUI-Aufbau
        baseAnchorPane.setTopAnchor(outerVBox,0.0);
        baseAnchorPane.setLeftAnchor(outerVBox,0.0);
        baseAnchorPane.setRightAnchor(outerVBox,0.0);
        baseAnchorPane.setBottomAnchor(outerVBox,0.0);
        baseAnchorPane.getChildren().add(outerVBox);

        outerVBox.getChildren().addAll(innerVBox, gridHBox);

        gridHBox.getChildren().addAll(funcGrid, nrGrid);


        // Resize Grids 5 zu 4
        baseAnchorPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            funcGrid.setPrefWidth((5.0/9.0) * (baseAnchorPane.getWidth() - (MAINPADDING * 3) ));
            nrGrid.setPrefWidth((4.0/9.0) * (baseAnchorPane.getWidth() - (MAINPADDING * 3) ));
            System.out.println("HboxWidth: " + gridHBox.getWidth() + "  BaseAnchorPane width: " + baseAnchorPane.getWidth());

        });

        // Resize Grids zu Label 7 zu 3
        baseAnchorPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            gridHBox.setPrefHeight(0.7 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            innerVBox.setPrefHeight(0.3 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            System.out.println("HboxHeight: " + gridHBox.getHeight() + "  BaseAnchorPane height: " + baseAnchorPane.getHeight());
        });

        KeyStrokeListener ks = new KeyStrokeListener(fi);
        baseAnchorPane.setOnKeyPressed(ks);
        baseAnchorPane.setOnKeyReleased(ks);

        Platform.runLater( () -> {
            display.setFocus();
        });


    }

    private class FrameAdapter implements FrameInterface {
        @Override
        public void setBigLabel (String message){
            display.setBigMsgBox(message);
        }

        @Override
        public void setSmallLabel (String message) {
            display.setSmallMsgBox(message);
        }

        @Override
        public GridInterface getFuncGrid() {
            return funcGrid;
        }

        @Override
        public GridInterface getNrGrid() {
            return nrGrid;
        }

        @Override
        public Button getFocusedButton() {
            if (sc.focusOwnerProperty().get() instanceof Button) {
                return (Button) sc.focusOwnerProperty().get();
            } else {
                return null;
            }
        }

        @Override
        public boolean isBigLabelFocused() {
            if (sc.focusOwnerProperty().get() instanceof Label ) {
                if((sc.focusOwnerProperty().get()).getId().equals("BigMsgBox")) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }


    }

    public Scene getScene() {return sc; }
}
