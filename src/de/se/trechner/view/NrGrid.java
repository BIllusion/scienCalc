package de.se.trechner.view;

import de.se.trechner.interfaces.ActionsInterface;
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
import de.se.trechner.model.GridActions;
import de.se.trechner.model.LangModel;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class NrGrid extends GridPane implements ActionsInterface<GridActions> {

    private List<Button> buttonList = new ArrayList<Button>();


    public NrGrid (FrameInterface fi) {
        super();
        // Grid Resize-Helper
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(25);
        col.setHgrow(Priority.ALWAYS);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(20);
        row.setVgrow(Priority.ALWAYS);

        // Setup 4x5 Grid
        this.setHgap(TRechnerGUI.INNERPADDING);
        this.setVgap(TRechnerGUI.INNERPADDING);
        this.getColumnConstraints().addAll(col,col,col,col);
        this.getRowConstraints().addAll(row,row,row,row,row);

        //Setup Language-Pack & Listener
        LangModel langModel = LangModel.getInstance();
        GridActionsListener gal = GridActionsListener.getInstance(fi);

        // Setup Buttons
        for (GridActions ga: GridActions.values()) {

            // Search associated GridActions
            if (IntStream.of(GridActions.NR_GRID_GROUP_IDS).anyMatch(x -> x == ga.getGroupID())) {

                // Create Button & Styles
                Button btn = new Button();
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setMaxHeight(Double.MAX_VALUE);

                // Add Captions, Text & Listener
                btn.setId(ga.toString());
                btn.setText(langModel.getKeyCaption(ga.toString()));
                btn.setAccessibleText(langModel.getAccessibleText(ga.toString()));
                btn.setAccessibleHelp(langModel.getAccessibleHelp(ga.toString()));
                btn.addEventHandler(ActionEvent.ACTION, gal);

                //Add Button to Grid & Save for later use
                this.add(btn,ga.getCol(), ga.getRow(), ga.getColSpan(),ga.getRowSpan());
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
                if( GridActions.valueOf(btn.getId()).getGroupID() == GridActions.NUMBER_GROUP_ID ) {
                    btn.setFont(Font.font("Lato", FontWeight.BLACK, fontSize*1.75));
                } else {
                    btn.setFont(Font.font("Lato", FontWeight.NORMAL, fontSize));
                }
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
