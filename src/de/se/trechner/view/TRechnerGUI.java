package de.se.trechner.view;

import de.se.trechner.Main;
import de.se.trechner.interfaces.ActionsInterface;
import de.se.trechner.model.CSSNodeIDs;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.controller.KeyStrokeListener;
import javafx.scene.text.Font;


public class TRechnerGUI {

    public final static int MAINPADDING = 30;
    public final static int INNERPADDING = 10;

    private Scene sc;
    private AnchorPane baseAnchorPane;
    private VBox outerVBox, innerVBox;
    private HBox gridHBox;
    private ToolBar tBar;
    private Display display;
    private FuncGrid funcGrid;
    private NrGrid nrGrid;
    private FrameInterface fi = new FrameAdapter();

    public TRechnerGUI() {

        // Base Anchor init
        baseAnchorPane = new AnchorPane();
        baseAnchorPane.setId(CSSNodeIDs.BASEANCHOR);
        baseAnchorPane.setPadding(new Insets(MAINPADDING, MAINPADDING, MAINPADDING, MAINPADDING));
        baseAnchorPane.setPrefSize(1024, 768);

        // Scene für Stage-Building
        sc = new Scene(baseAnchorPane);
        sc.getStylesheets().add("resources/css/TRechnerStyles.css");

        // Einteilung oben unten
        outerVBox = new VBox();
        outerVBox.setId(CSSNodeIDs.OUTERVBOX);
        outerVBox.setFillWidth(true);
        outerVBox.setSpacing(MAINPADDING);

        // Oberer Teil
        innerVBox = new VBox();
        innerVBox.setId(CSSNodeIDs.INNERVBOX);


        // Toolbar
        tBar = new ToolBar(fi);
        tBar.setId(CSSNodeIDs.TOOLBAR);
        display = new Display();

        innerVBox.setVgrow(display,Priority.ALWAYS);
        innerVBox.getChildren().addAll(tBar, display);


        // Unterer Teil
        gridHBox = new HBox();
        gridHBox.setFillHeight(true);
        gridHBox.setSpacing(MAINPADDING);


        // Button Grids Erstellen
        funcGrid = new FuncGrid(fi);
        funcGrid.setId(CSSNodeIDs.FUNCGRID);
        nrGrid = new NrGrid(fi);
        nrGrid.setId(CSSNodeIDs.NRGRID);

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
            gridHBox.setPrefHeight(0.65 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            innerVBox.setPrefHeight(0.35 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            System.out.println("HboxHeight: " + gridHBox.getHeight() + "  BaseAnchorPane height: " + baseAnchorPane.getHeight());
        });



        KeyStrokeListener ks = new KeyStrokeListener(fi);
        sc.addEventFilter(KeyEvent.KEY_PRESSED, ks);
        sc.addEventFilter(KeyEvent.KEY_RELEASED, ks);

        Font.loadFont(Main.class.getResource("/resources/fonts/Lato-Regular.ttf").toExternalForm(),20);
        Font.loadFont(Main.class.getResource("/resources/fonts/Lato-Bold.ttf").toExternalForm(),20);
        Font.loadFont(Main.class.getResource("/resources/fonts/Lato-Black.ttf").toExternalForm(),20);
        Font.loadFont(Main.class.getResource("/resources/fonts/Lato-Light.ttf").toExternalForm(),20);

        Platform.runLater( () -> {
            display.setFocus();
            funcGrid.updateFontSize();
            nrGrid.updateFontSize();
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
        public ActionsInterface getToolBar() {
            return tBar;
        }

        @Override
        public ActionsInterface getFuncGrid() {
            return funcGrid;
        }

        @Override
        public ActionsInterface getNrGrid() {
            return nrGrid;
        }

        @Override
        public String getIdFromFocus() {
            return sc.focusOwnerProperty().get().getId();
        }

        @Override
        public boolean isBigLabelFocused() {
            if (sc.focusOwnerProperty().get() instanceof Label ) {
                if((sc.focusOwnerProperty().get()).getId().equals(CSSNodeIDs.BIGMSGBOX)) {
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
