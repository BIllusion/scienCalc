package scienCalc.view;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import scienCalc.controller.FuncGridListener;
import scienCalc.model.FuncGridConstants;
import scienCalc.model.LangModel;
import scienCalc.model.NrGridConstants;

import java.util.ArrayList;
import java.util.List;

public class NrGrid {
    private GridPane NrGridPane;
    private List<Button> buttonList = new ArrayList<Button>();

    private FuncGridListener fGListener = new FuncGridListener();

    public NrGrid()  {
        // Grid Resize-Helper
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(25);
        col.setHgrow(Priority.ALWAYS);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(20);
        row.setVgrow(Priority.ALWAYS);

        // Setup 5x5 Grid
        NrGridPane = new GridPane();
        NrGridPane.setHgap(TRechnerGUI.INNERPADDING);
        NrGridPane.setVgap(TRechnerGUI.INNERPADDING);
        NrGridPane.getColumnConstraints().addAll(col,col,col,col);
        NrGridPane.getRowConstraints().addAll(row,row,row,row,row);

        //Setup Language-Pack
        LangModel langModel = LangModel.getInstance();
        // System.out.println(myModel.getLangValue("SQR"));

        // Setup Buttons
        for (int i = 0; i < 4*5; i++) {
            Button btn = new Button();
            btn.getStylesheets().add("scienCalc/view/css/nrGridStyles.css");
            //btn.requestFocus();
            btn.setId((NrGridConstants.values()[i]).toString());
            btn.setText(langModel.getKeyCaption((NrGridConstants.values()[i]).toString()));
           // btn.setAccessibleText(langModel.getAccessibleText((NrGridConstants.values()[i]).toString()));
            btn.setFont(new Font("Lato", 16));
            //btn.addEventHandler(ActionEvent.ACTION,fGListener);
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setMaxHeight(Double.MAX_VALUE);
            buttonList.add(btn);
        }

        // Add Buttons to Grid
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                NrGridPane.add(buttonList.get(i*4+j),j,i);
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
}
