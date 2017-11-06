package scienCalc;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TRechnerGUI {

    private static final int MAINPADDING = 30;

    private AnchorPane baseAnchorPane;
    private VBox outerVBox, innerVBox;
    private HBox gridHBox;
    private GridPane funcGridPane, nrGridPane;

    public TRechnerGUI() {

        baseAnchorPane = new AnchorPane();
        baseAnchorPane.setPadding(new Insets(MAINPADDING, MAINPADDING, MAINPADDING, MAINPADDING));
        baseAnchorPane.setStyle("-fx-background-color: #303030;");
        baseAnchorPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("geht");
        });
        baseAnchorPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("geht auch");
        });

        outerVBox = new VBox();
        outerVBox.setFillWidth(true);
        outerVBox.setSpacing(MAINPADDING);
        outerVBox.setStyle("-fx-background-color: #32504e;"); //nur debugging

        innerVBox = new VBox();

        gridHBox = new HBox();
        gridHBox.setFillHeight(true);

        baseAnchorPane.setTopAnchor(outerVBox,0.0);
        baseAnchorPane.setLeftAnchor(outerVBox,0.0);
        baseAnchorPane.setRightAnchor(outerVBox,0.0);
        baseAnchorPane.setBottomAnchor(outerVBox,0.0);
        baseAnchorPane.getChildren().add(outerVBox);
    }

    public Parent asParent() {
        return baseAnchorPane;
    }
}
