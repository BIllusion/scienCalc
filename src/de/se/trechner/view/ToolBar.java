package de.se.trechner.view;

import de.se.trechner.controller.ToolbarCmdListener;
import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.LangModel;
import de.se.trechner.model.ToolbarActionCmds;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;


public class ToolBar extends AnchorPane {

    private TextFlow modeSwitch;
    private TextFlow memFunc;

    private List<Hyperlink> hlList = new ArrayList<Hyperlink>();
    private Hyperlink hDEG, hFE, hMS, hMR, hMC;

    private Font baseFont = new Font("Lato", 16);
    private FrameInterface fi;

    public ToolBar(FrameInterface fi) {
        super();

        this.fi = fi;
        this.setStyle("-fx-background-color: #303030;" +
                "    -fx-border-width: 0 0 8px;" +
                "    -fx-border-color: #006d68;");
        this.getStylesheets().add("resources/css/ToolbarStyles.css");

        //Setup Language-Pack & Listener
        LangModel langModel = LangModel.getInstance();
        ToolbarCmdListener tcl = ToolbarCmdListener.getInstance(fi);

        // Setup Container for Click-Items
        TextFlow leftContainer = new TextFlow();
        TextFlow rightContainer = new TextFlow();

        // Setup Buttons
        for (ToolbarActionCmds tac: ToolbarActionCmds.values()) {
            Hyperlink hl = new Hyperlink();
            hl.setId(tac.toString());
            hl.addEventHandler(ActionEvent.ACTION, tcl);

            hl.setFont(baseFont);
            hl.setAccessibleText(langModel.getAccessibleText(tac.toString()));
            hl.setText(langModel.getKeyCaption(tac.toString()));
            hlList.add(hl);

            // Search associated ActionCmds
            if (tac.getContainerID() == tac.TB_CONTAINER_ID_LEFT) {
                if (!leftContainer.getChildren().isEmpty()) {
                    leftContainer.getChildren().add(getSeperator());
                }
                leftContainer.getChildren().add(hl);
            }

            if (tac.getContainerID() == tac.TB_CONTAINER_ID_RIGHT) {
                if (!rightContainer.getChildren().isEmpty()) {
                    rightContainer.getChildren().add(getSeperator());
                }
                rightContainer.getChildren().add(hl);
            }
        }

        this.setTopAnchor(leftContainer,0.0);
        this.setLeftAnchor(leftContainer,0.0);

        this.setRightAnchor(rightContainer,0.0);
        this.setBottomAnchor(rightContainer,0.0);

        this.getChildren().addAll(leftContainer,rightContainer);
    }

    private Text getSeperator() {
        Text t = new Text(" | ");
        t.setFont(baseFont);
        return t;
    }
}
