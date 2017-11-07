package scienCalc.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import scienCalc.calcInterfaces.FrameInterface;
import scienCalc.calcInterfaces.GridInterface;
import scienCalc.controller.KeyStrokeListener;

public class TRechnerGUI {

    public final static int MAINPADDING = 30;
    public final static int INNERPADDING = 10;

    private AnchorPane baseAnchorPane;
    private VBox outerVBox, innerVBox;
    private HBox gridHBox;
    private FuncGrid funcGrid;
    private NrGrid nrGrid;
    private FrameInterface fi = new FrameAdapter();

    public TRechnerGUI() {

        // Base Anchor init
        baseAnchorPane = new AnchorPane();
        baseAnchorPane.setPadding(new Insets(MAINPADDING, MAINPADDING, MAINPADDING, MAINPADDING));
        baseAnchorPane.setPrefSize(1024, 768);
        baseAnchorPane.setStyle("-fx-background-color: #303030;");

        // Einteilung oben unten
        outerVBox = new VBox();
        outerVBox.setFillWidth(true);
        outerVBox.setSpacing(MAINPADDING);

        // Oberer Teil
        innerVBox = new VBox();
        innerVBox.setStyle("-fx-background-color: #FFFFFF;"); //nur debugging

        // Unterer Teil
        gridHBox = new HBox();
        gridHBox.setFillHeight(true);
        gridHBox.setSpacing(MAINPADDING);


        // Button Grids Erstellen
        funcGrid = new FuncGrid();
        nrGrid = new NrGrid();

        // GUI-Aufbau
        baseAnchorPane.setTopAnchor(outerVBox,0.0);
        baseAnchorPane.setLeftAnchor(outerVBox,0.0);
        baseAnchorPane.setRightAnchor(outerVBox,0.0);
        baseAnchorPane.setBottomAnchor(outerVBox,0.0);
        baseAnchorPane.getChildren().add(outerVBox);

        outerVBox.getChildren().addAll(innerVBox, gridHBox);

        gridHBox.getChildren().addAll(funcGrid.getGrid(), nrGrid.getGrid());



        // Resize Grids 5 zu 4
        baseAnchorPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            funcGrid.getGrid().setPrefWidth((5.0/9.0) * (baseAnchorPane.getWidth() - (MAINPADDING * 3) ));
            nrGrid.getGrid().setPrefWidth((4.0/9.0) * (baseAnchorPane.getWidth() - (MAINPADDING * 3) ));
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

    }

    private class FrameAdapter implements FrameInterface {
        @Override
        public void setBigLabel (String message){

        }

        @Override
        public void setSmallLabel (String message) {

        }

        @Override
        public GridInterface getFuncGrid() {
            return funcGrid;
        }

        @Override
        public GridInterface getNrGrid() {
            return nrGrid;
        }


    }

    public Parent asParent() {
        return baseAnchorPane;
    }
}
