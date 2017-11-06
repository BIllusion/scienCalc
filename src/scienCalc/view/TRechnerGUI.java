package scienCalc.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import scienCalc.calcInterface.FrameInterface;
import scienCalc.model.LangModel;

public class TRechnerGUI {

    public final static int MAINPADDING = 30;
    public final static int INNERPADDING = 10;

    private AnchorPane baseAnchorPane;
    private VBox outerVBox, innerVBox;
    private HBox gridHBox;
    private GridPane funcGridPane, nrGridPane;
    private Button btn;

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
        outerVBox.setStyle("-fx-background-color: #32504e;"); //nur debugging

        // Oberer Teil
        innerVBox = new VBox();
        innerVBox.setStyle("-fx-background-color: #FF0000;"); //nur debugging

        // Unterer Teil
        gridHBox = new HBox();
        gridHBox.setFillHeight(true);
        gridHBox.setSpacing(MAINPADDING);
        gridHBox.setStyle("-fx-background-color: #00FF00;"); //nur debugging

        // Grid Resize-Helper
        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.ALWAYS);
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);

        FunctionGrid fg = new FunctionGrid();

        // Number Grid 4 x 5
        nrGridPane = new GridPane();
        nrGridPane.setHgap(INNERPADDING);
        nrGridPane.setVgap(INNERPADDING);
        nrGridPane.getColumnConstraints().addAll(col,col,col,col);
        nrGridPane.getRowConstraints().addAll(row,row,row,row,row);


        LangModel myModel = LangModel.getInstance();
        System.out.println(myModel.getLangValue("SQR"));


        // GUI-Aufbau
        baseAnchorPane.setTopAnchor(outerVBox,0.0);
        baseAnchorPane.setLeftAnchor(outerVBox,0.0);
        baseAnchorPane.setRightAnchor(outerVBox,0.0);
        baseAnchorPane.setBottomAnchor(outerVBox,0.0);
        baseAnchorPane.getChildren().add(outerVBox);

        outerVBox.getChildren().addAll(innerVBox, gridHBox);

        gridHBox.getChildren().addAll(fg.getGrid(), nrGridPane);



        // Resize inner Elements
        baseAnchorPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            fg.getGrid().setPrefWidth((5.0/9.0) * (baseAnchorPane.getWidth() - (MAINPADDING * 3) ));
            nrGridPane.setPrefWidth(((4.0/9.0) * (baseAnchorPane.getWidth() - (MAINPADDING * 3))));
            System.out.println("HboxWidth: " + gridHBox.getWidth() + "  BaseAnchorPane width: " + baseAnchorPane.getWidth());

        });

        baseAnchorPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            gridHBox.setPrefHeight(0.7 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            innerVBox.setPrefHeight(0.3 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            System.out.println("HboxHeight: " + gridHBox.getHeight() + "  BaseAnchorPane height: " + baseAnchorPane.getHeight());
        });


    }

    private class FrameAdapter implements FrameInterface {
        @Override
        public void setBigLabel (String message){

        }

        @Override
        public void setSmallLabel (String message) {

        }
    }

    public Parent asParent() {
        return baseAnchorPane;
    }
}
