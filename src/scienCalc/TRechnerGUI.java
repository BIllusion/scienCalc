package scienCalc;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TRechnerGUI {

    private static final int MAINPADDING = 30;
    private static final int INNERPADDING = 10;

    private AnchorPane baseAnchorPane;
    private VBox outerVBox, innerVBox;
    private HBox gridHBox;
    private GridPane funcGridPane, nrGridPane;

    public TRechnerGUI() {

        baseAnchorPane = new AnchorPane();
        baseAnchorPane.setPadding(new Insets(MAINPADDING, MAINPADDING, MAINPADDING, MAINPADDING));
        baseAnchorPane.setStyle("-fx-background-color: #303030;");

        outerVBox = new VBox();
        outerVBox.setFillWidth(true);
        outerVBox.setSpacing(MAINPADDING);
        outerVBox.setStyle("-fx-background-color: #32504e;"); //nur debugging

        innerVBox = new VBox();
        innerVBox.setStyle("-fx-background-color: #FF0000;"); //nur debugging

        gridHBox = new HBox();
        gridHBox.setFillHeight(true);
        gridHBox.setStyle("-fx-background-color: #00FF00;"); //nur debugging

        funcGridPane = new GridPane();
        funcGridPane.setHgap(INNERPADDING);
        funcGridPane.setVgap(INNERPADDING);
        funcGridPane.setGridLinesVisible(true);
        funcGridPane.add(new Button(), 4,4);
        funcGridPane.setWidth(gridHBox.getWidth());
        System.out.println("ich tu was");
        updateGridHeight();
        updateGridWidth();

        gridHBox.getChildren().addAll(funcGridPane);

        outerVBox.getChildren().addAll(innerVBox, gridHBox);

        baseAnchorPane.setTopAnchor(outerVBox,0.0);
        baseAnchorPane.setLeftAnchor(outerVBox,0.0);
        baseAnchorPane.setRightAnchor(outerVBox,0.0);
        baseAnchorPane.setBottomAnchor(outerVBox,0.0);
        baseAnchorPane.getChildren().add(outerVBox);

        // Resize inner Elements
        baseAnchorPane.widthProperty().addListener((obs, oldVal, newVal) -> {

            System.out.println("geht");
        });

        baseAnchorPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            gridHBox.setPrefHeight(0.7*(baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            innerVBox.setPrefHeight(0.3*(baseAnchorPane.getHeight() - (MAINPADDING * 3)));
            System.out.println("geht auch");
        });
    }

    public Parent asParent() {
        return baseAnchorPane;
    }

    private void updateGridHeight() {
        funcGridPane.setPrefHeight(gridHBox.getHeight());
    }

    private void updateGridWidth() {
        funcGridPane.setPrefWidth(((5/9)*gridHBox.getWidth())-MAINPADDING);
    }
}
