package scienCalc.view;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import scienCalc.calcInterfaces.GridInterface;
import scienCalc.controller.ActionCmdListener;
import scienCalc.model.ActionCmds;
import scienCalc.model.LangModel;

import java.util.ArrayList;
import java.util.List;


public class FuncGrid implements GridInterface {

    private GridPane funcGridPane;
    private List<Button> buttonList = new ArrayList<Button>();


    public FuncGrid()  {
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
        funcGridPane.getColumnConstraints().addAll(col,col,col,col, col);
        funcGridPane.getRowConstraints().addAll(row,row,row,row,row);

        //Setup Language-Pack & Listener
        LangModel langModel = LangModel.getInstance();
        ActionCmdListener acl = new ActionCmdListener();

        // Setup Buttons
        for (ActionCmds ac: ActionCmds.values()) {

            // Search associated ActionCmds
            if (ac.getGridID() == ac.FUNC_GRID_ID) {

                // Create Button & Styles
                Button btn = new Button();
                btn.getStylesheets().add("scienCalc/view/css/funcGridStyles.css");
                btn.setFont(new Font("Lato", 16));
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setMaxHeight(Double.MAX_VALUE);

                // Add Captions, Text & Listener
                btn.setId(ac.toString());
                btn.setText(langModel.getKeyCaption(ac.toString()));
                btn.setAccessibleText(langModel.getAccessibleText(ac.toString()));
                btn.addEventHandler(ActionEvent.ACTION,acl);

                //Add Button to Grid & Save for later use
                funcGridPane.add(btn,ac.getCol(), ac.getRow(), ac.getColSpan(),ac.getRowSpan());
                buttonList.add(btn);
            }
        }

        updateFontSize();

        // Resize Listener for Text Scaling
        funcGridPane.widthProperty().addListener(resizeListener);
        funcGridPane.heightProperty().addListener(resizeListener);
    }

    ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> updateFontSize();

    public GridPane getGrid() {
        return funcGridPane;
    }

    private void updateFontSize() {
        // Get smaller Side length
        Button sampleButton = buttonList.get(0);
        Double sideLength = Math.min(sampleButton.getWidth(), sampleButton.getHeight());
        if (sideLength != 0 ) {
            // Calculate Font-Size
            Double fontSize = sideLength / 4;
            Font f = new Font("Lato", fontSize);
            // Update Font-Size on all Buttons
            for (Button btn : buttonList) {
                // btn.setStyle("-fx-font-size: "+Math.round(100.0 * fontSize) / 100.0+"px;");
                btn.setFont(f);
            }
        }
    }

    @Override
    public void setButtonFocus(ActionCmds ac) {
        for (Button btn: buttonList) {
            if (btn.getId().equals(ac.toString())) {
                btn.requestFocus();
            }
        }
    }
}
