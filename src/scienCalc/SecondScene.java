package scienCalc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class SecondScene {

    private AnchorPane anchorPane;
    private GridPane gridPane;
    private Button btn;

    private EventHandler<ActionEvent> lis;

    public SecondScene() {
        anchorPane = new AnchorPane();

        btn = new Button();
        btn.setText("Hello World");
        btn.setId("my ID is 1");
        lis = new alternativListener(1);
        btn.addEventHandler(ActionEvent.ACTION,lis);

        gridPane = new GridPane();
        gridPane.add(new Button(), 3, 2 );
        gridPane.add(new Button(), 3, 3 );
        gridPane.add(btn, 3, 4 );
        gridPane.getColumnConstraints().add(new ColumnConstraints(100)); // column 1 is 100 wide
        gridPane.getColumnConstraints().add(new ColumnConstraints(100)); // column 2 is 200 wide
        gridPane.getRowConstraints().add(new RowConstraints(150));
        gridPane.setGridLinesVisible(true);
       // gridPane.addColumn(0);
        //gridPane.addRow(0);

        anchorPane.setTopAnchor(gridPane,0.0);
        anchorPane.setLeftAnchor(gridPane, 0.0);
        anchorPane.setRightAnchor(gridPane, 0.0);

        anchorPane.getChildren().addAll(gridPane);
    }

    public Parent asParent() {
        return anchorPane;
    }
}
