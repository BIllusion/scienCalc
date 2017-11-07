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


public class NrGrid implements GridInterface {

    private GridPane NrGridPane;
    private List<Button> buttonList = new ArrayList<Button>();

    public NrGrid () {
        // Grid Resize-Helper
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(25);
        col.setHgrow(Priority.ALWAYS);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(20);
        row.setVgrow(Priority.ALWAYS);

        // Setup 4x5 Grid
        NrGridPane = new GridPane();
        NrGridPane.setHgap(TRechnerGUI.INNERPADDING);
        NrGridPane.setVgap(TRechnerGUI.INNERPADDING);
        NrGridPane.getColumnConstraints().addAll(col,col,col,col);
        NrGridPane.getRowConstraints().addAll(row,row,row,row,row);

        //Setup Language-Pack & Listener
        LangModel langModel = LangModel.getInstance();
        ActionCmdListener acl = new ActionCmdListener();

        // Setup Buttons
        for (ActionCmds ac: ActionCmds.values()) {

        // Search associated ActionCmds
        if (ac.getGridID() == ac.NR_GRID_ID) {

            // Create Button & Styles
            Button btn = new Button();
            btn.getStylesheets().add("scienCalc/view/css/nrGridStyles.css");
            btn.setFont(new Font("Lato", 16));
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setMaxHeight(Double.MAX_VALUE);

            // Add Captions, Text & Listener
            btn.setId(ac.toString());
            btn.setText(langModel.getKeyCaption(ac.toString()));
            btn.setAccessibleText(langModel.getAccessibleText(ac.toString()));
            btn.addEventHandler(ActionEvent.ACTION,acl);

            //Add Button to Grid & Save for later use
            NrGridPane.add(btn,ac.getCol(), ac.getRow(), ac.getColSpan(),ac.getRowSpan());
            buttonList.add(btn);
            }
        }


        updateFontSize();

        // Resize Listener for Text Scaling
        NrGridPane.widthProperty().addListener(resizeListener);
        NrGridPane.heightProperty().addListener(resizeListener);
    }

    ChangeListener<Number> resizeListener = (observable, oldValue, newValue) ->
            updateFontSize();

    public GridPane getGrid() {
        return NrGridPane;
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
