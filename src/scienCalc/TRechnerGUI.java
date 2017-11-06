package scienCalc;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class TRechnerGUI {

    private static final int MAINPADDING = 30;
    private static final int INNERPADDING = 10;

    private AnchorPane baseAnchorPane;
    private VBox outerVBox, innerVBox;
    private HBox gridHBox;
    private GridPane funcGridPane, nrGridPane;
    private Button btn;

    public TRechnerGUI() {

        // Base Anchor init
        baseAnchorPane = new AnchorPane();
        baseAnchorPane.setPadding(new Insets(MAINPADDING, MAINPADDING, MAINPADDING, MAINPADDING));
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

        // Funktions Grid 5 x 5
        funcGridPane = new GridPane();
        funcGridPane.setHgap(INNERPADDING);
        funcGridPane.setVgap(INNERPADDING);
        funcGridPane.setGridLinesVisible(true);
        funcGridPane.getColumnConstraints().addAll(col,col,col,col,col);
        funcGridPane.getRowConstraints().addAll(row,row,row,row,row);

        // Number Grid 4 x 5
        nrGridPane = new GridPane();
        nrGridPane.setHgap(INNERPADDING);
        nrGridPane.setVgap(INNERPADDING);
        nrGridPane.setGridLinesVisible(true);
        nrGridPane.getColumnConstraints().addAll(col,col,col,col);
        nrGridPane.getRowConstraints().addAll(row,row,row,row,row);

        btn = new Button();
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setMaxHeight(Double.MAX_VALUE);
        btn.setText("Hallo");
        btn.setFont(Font.font(125));
        funcGridPane.setFillWidth(btn, true);
        funcGridPane.setFillHeight(btn, true);


        funcGridPane.add(btn, 2,2);


        // GUI-Aufbau
        baseAnchorPane.setTopAnchor(outerVBox,0.0);
        baseAnchorPane.setLeftAnchor(outerVBox,0.0);
        baseAnchorPane.setRightAnchor(outerVBox,0.0);
        baseAnchorPane.setBottomAnchor(outerVBox,0.0);
        baseAnchorPane.getChildren().add(outerVBox);

        outerVBox.getChildren().addAll(innerVBox, gridHBox);

        gridHBox.getChildren().addAll(funcGridPane, nrGridPane);



        // Resize inner Elements
        baseAnchorPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            funcGridPane.setPrefWidth((5.0/9.0) * (baseAnchorPane.getWidth() - (MAINPADDING * 3) ));
            nrGridPane.setPrefWidth(((4.0/9.0) * (baseAnchorPane.getWidth() - (MAINPADDING * 3))));
            System.out.println("HboxWidth: " + gridHBox.getWidth() + "  BaseAnchorPane width: " + baseAnchorPane.getWidth());

        });

        baseAnchorPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            gridHBox.setPrefHeight(0.7 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            innerVBox.setPrefHeight(0.3 * (baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            System.out.println("HboxHeight: " + gridHBox.getHeight() + "  BaseAnchorPane height: " + baseAnchorPane.getHeight());
        });
    }

    public Parent asParent() {
        return baseAnchorPane;
    }
}
