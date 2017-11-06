package scienCalc.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import scienCalc.model.FuncGridConstants;
import scienCalc.controller.FuncGridListener;

import java.util.ArrayList;
import java.util.List;


public class FunctionGrid {

    private GridPane funcGridPane;
    private List<Button> buttonList = new ArrayList<Button>();

    private FuncGridListener fGListener = new FuncGridListener();

    public FunctionGrid()  {
        // Grid Resize-Helper
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(20);
        col.setHgrow(Priority.ALWAYS);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(20);
        row.setVgrow(Priority.ALWAYS);

        // Setup 5x5 Grid
        funcGridPane = new GridPane();
        funcGridPane.setHgap(TRechnerGUI.INNERPADDING);
        funcGridPane.setVgap(TRechnerGUI.INNERPADDING);
        funcGridPane.setGridLinesVisible(true);
        funcGridPane.getColumnConstraints().addAll(col,col,col,col,col);
        funcGridPane.getRowConstraints().addAll(row,row,row,row,row);

        // Setup Buttons
        for (int i = 0; i < 5*5; i++) {
            Button btn = new Button();
            btn.getStylesheets().add("scienCalc/view/css/baseButton.css");
            btn.setId((FuncGridConstants.values()[i]).toString());
            btn.setText((FuncGridConstants.values()[i]).toString());
            btn.addEventHandler(ActionEvent.ACTION,fGListener);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setMaxHeight(Double.MAX_VALUE);
            buttonList.add(btn);
        }

        // Add Buttons to Grid
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                funcGridPane.add(buttonList.get(i*5+j),j,i);
            }
        }
    }

    public GridPane getGrid() {
        return funcGridPane;
    }
}
