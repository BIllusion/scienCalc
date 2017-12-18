package de.se.trechner.view;

import de.se.trechner.Main;
import de.se.trechner.interfaces.ActionsInterface;
import de.se.trechner.interfaces.DisplayInterface;
import de.se.trechner.model.CSSNodeIDs;
import de.se.trechner.model.GridActions;
import de.se.trechner.model.ToolbarActions;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.controller.KeyStrokeListener;
import javafx.scene.text.Font;

/**
 * Repräsentiert die Basis GUI-Klasse
 *
 * @author ruess_c
 * @version 2017-12-16
 * @see de.se.trechner.interfaces.ActionsInterface
 */
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

    /**
     * Baut das eigentliche Fenster auf und setzt die Position aller auf der Oberfläche zu sehenden Elemente
     *
     */
    public TRechnerGUI() {

        // Base Anchor init
        baseAnchorPane = new AnchorPane();
        baseAnchorPane.setId(CSSNodeIDs.BASEANCHOR);
        baseAnchorPane.setPadding(new Insets(MAINPADDING, MAINPADDING, MAINPADDING, MAINPADDING));
        baseAnchorPane.setPrefSize(1024, 650);

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
        display.setId(CSSNodeIDs.DISPLAY);

        innerVBox.setVgrow(display,Priority.ALWAYS);
        innerVBox.getChildren().addAll(tBar, display);


        // Unterer Teil
        gridHBox = new HBox();
        gridHBox.setId(CSSNodeIDs.GRIDHBOX);
        gridHBox.setFillHeight(true);
        gridHBox.setSpacing(MAINPADDING);


        // Button Grids Erstellen
        nrGrid = new NrGrid(fi);
        nrGrid.setId(CSSNodeIDs.NRGRID);
        funcGrid = new FuncGrid(fi);
        funcGrid.setId(CSSNodeIDs.FUNCGRID);


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

        });

        // Resize Grids zu Label 7 zu 3
        baseAnchorPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            gridHBox.setPrefHeight(0.65 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            innerVBox.setPrefHeight(0.35 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
        });



        KeyStrokeListener ks = new KeyStrokeListener(fi);
        sc.addEventFilter(KeyEvent.KEY_PRESSED, ks);
        sc.addEventFilter(KeyEvent.KEY_RELEASED, ks);

        Font.loadFont(Main.class.getResource("/resources/fonts/Lato-Regular.ttf").toExternalForm(),20);
        Font.loadFont(Main.class.getResource("/resources/fonts/Lato-Bold.ttf").toExternalForm(),20);
        Font.loadFont(Main.class.getResource("/resources/fonts/Lato-Black.ttf").toExternalForm(),20);
        Font.loadFont(Main.class.getResource("/resources/fonts/Lato-Light.ttf").toExternalForm(),20);

        Platform.runLater( () -> {
            display.requestFocus();
            funcGrid.updateFontSize();
            nrGrid.updateFontSize();
        });

    }

    private class FrameAdapter implements FrameInterface {

        /**
         * Gibt Zugriff auf die definierten Funktionen einer zweigeteilten Ausgabekomponente
         *
         * @return das Interface der zweigeteilten Ausgabekomponente
         * @see de.se.trechner.interfaces.DisplayInterface
         */
        @Override
        public DisplayInterface getDisplay () {
            return  display;
        }

        /**
         * Gibt Zugriff auf vom ActionsInterface definierte Funktionen der Toolbar
         *
         * @return das Interface um die Funktionen zu nutzen
         * @see de.se.trechner.interfaces.ActionsInterface
         * @see de.se.trechner.view.ToolBar
         */
        @Override
        public ActionsInterface<ToolbarActions> getToolBar() {
            return tBar;
        }

        /**
         * Gibt Zugriff auf vom ActionsInterface definierte Funktionen des FuncGrids
         *
         * @return das Interface um die Funktionen zu nutzen
         * @see de.se.trechner.interfaces.ActionsInterface
         * @see de.se.trechner.view.FuncGrid
         */
        @Override
        public ActionsInterface<GridActions> getFuncGrid() {
            return funcGrid;
        }

        /**
         * Gibt Zugriff auf vom ActionsInterface definierte Funktionen des NrGrids
         *
         * @return das Interface um die Funktionen zu nutzen
         * @see de.se.trechner.interfaces.ActionsInterface
         * @see de.se.trechner.view.NrGrid
         */
        @Override
        public ActionsInterface<GridActions> getNrGrid() {
            return nrGrid;
        }

        /**
         * Gibt die ID des momentan fokussierten Objekts zurück
         *
         * @return enthält die ID des momentan fokussierten Objekts
         */
        @Override
        public String getIdFromFocus() {
            return sc.focusOwnerProperty().get().getId();
        }

        /**
         * Öffnet das Hilfefenster
         *
         * @see de.se.trechner.view.HelpWindow
         */
        @Override
        public void showHelp() {
            HelpWindow.open();
        }

        /**
         * Beschreibt ob der aktuelle Fokus auf der großen Inputzeile liegt
         *
         * @return fokussiert
         */
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

    /**
     * Gibt die Basis-Scene zum Anzeigen zurück
     *
     * @return die Scene für den Stage-Container
     */
    public Scene getScene() {return sc; }
}
