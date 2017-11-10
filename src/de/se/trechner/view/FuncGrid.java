package de.se.trechner.view;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.interfaces.GridInterface;
import de.se.trechner.controller.ActionCmdListener;
import de.se.trechner.model.ActionCmds;
import de.se.trechner.model.LangModel;

import java.util.ArrayList;
import java.util.List;


public class FuncGrid extends GridPane implements GridInterface {

    private List<Button> buttonList = new ArrayList<Button>();

    public FuncGrid(FrameInterface fi)  {
        super();

        // Grid Resize-Helper
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(20);
        col.setHgrow(Priority.ALWAYS);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(20);
        row.setVgrow(Priority.ALWAYS);

        // Setup 5x5 Grid

        this.setHgap(TRechnerGUI.INNERPADDING);
        this.setVgap(TRechnerGUI.INNERPADDING);
        this.getColumnConstraints().addAll(col,col,col,col, col);
        this.getRowConstraints().addAll(row,row,row,row,row);

        //Setup Language-Pack & Listener
        LangModel langModel = LangModel.getInstance();
        ActionCmdListener acl = ActionCmdListener.getInstance(fi);

        // Setup Buttons
        for (ActionCmds ac: ActionCmds.values()) {

            // Search associated ActionCmds
            if (ac.getGridID() == ac.FUNC_GRID_ID) {

                // Create Button & Styles
                Button btn = new Button();
                btn.getStylesheets().add("resources/css/funcGridStyles.css");
                btn.setFont(new Font("Lato", 16));
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setMaxHeight(Double.MAX_VALUE);

                // Add Captions, Text & Listener
                btn.setId(ac.toString());
                btn.setText(langModel.getKeyCaption(ac.toString()));
                btn.setAccessibleText(langModel.getAccessibleText(ac.toString()));
                btn.addEventHandler(ActionEvent.ACTION,acl);

                //Add Button to Grid & Save for later use
                this.add(btn,ac.getCol(), ac.getRow(), ac.getColSpan(),ac.getRowSpan());
                buttonList.add(btn);
            }
        }

        updateFontSize();

        // Resize Listener for Text Scaling
        this.widthProperty().addListener(resizeListener);
        this.heightProperty().addListener(resizeListener);
    }

    ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> updateFontSize();

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

    @Override
    public void fireButton(ActionCmds ac) {
        for (Button btn: buttonList) {
            if (btn.getId().equals(ac.toString())) {
                btn.requestFocus();
                btn.fire();
            }
        }
    }
}
