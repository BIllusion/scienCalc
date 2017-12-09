package de.se.trechner.view;

import de.se.trechner.interfaces.ActionsInterface;
import de.se.trechner.model.GridActions;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.controller.GridActionsListener;
import de.se.trechner.model.LangModel;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class FuncGrid extends GridPane implements ActionsInterface<GridActions> {

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
        GridActionsListener acl = GridActionsListener.getInstance(fi);

        // Setup Buttons
        for (GridActions ac: GridActions.values()) {

            // Search associated GridActions
            if (IntStream.of(GridActions.FUNC_GRID_GROUP_IDS).anyMatch(x -> x == ac.getGroupID())) {

                // Create Button & Styles
                Button btn = new Button();
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setMaxHeight(Double.MAX_VALUE);

                // Add Captions, Text & Listener
                btn.setId(ac.toString());
                btn.setText(langModel.getKeyCaption(ac.toString()));
                btn.setAccessibleText(langModel.getAccessibleText(ac.toString()));
                btn.setAccessibleHelp(langModel.getAccessibleHelp(ac.toString()));
                btn.addEventHandler(ActionEvent.ACTION,acl);

                //Add Button to Grid & Save for later use
                this.add(btn,ac.getCol(), ac.getRow(), ac.getColSpan(),ac.getRowSpan());
                buttonList.add(btn);
            }
        }

        // Resize Listener for Text Scaling
        this.widthProperty().addListener(resizeListener);
        this.heightProperty().addListener(resizeListener);
    }

    ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> updateFontSize();

    public void updateFontSize() {
        // Get smaller Side length
        Button sampleButton = buttonList.get(0);
        Double sideLength = Math.min(sampleButton.getWidth(), sampleButton.getHeight());
        if (sideLength != 0 ) {
            // Calculate Font-Size
            Double fontSize = sideLength / 4;

            // Update Font-Size on all Buttons
            for (Button btn : buttonList) {
                btn.setFont(Font.font("Lato", FontWeight.NORMAL, fontSize));
            }
        }
    }

    @Override
    public void requestFocus(GridActions ga) {
        for (Button btn: buttonList) {
            if (btn.getId().equals(ga.toString())) {
                btn.requestFocus();
            }
        }
    }


    @Override
    public void fireActionEvent(GridActions ga) {
        for (Button btn: buttonList) {
            if (btn.getId().equals(ga.toString())) {
                btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
                btn.fire();
            }
        }
    }

    @Override
    public void releaseActionEvent(GridActions ga) {
        for (Button btn: buttonList) {
            if (btn.getId().equals(ga.toString())) {
                btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
            }
        }
    }
}
