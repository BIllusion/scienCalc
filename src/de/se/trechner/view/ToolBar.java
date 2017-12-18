package de.se.trechner.view;

import de.se.trechner.controller.ToolbarActionsListener;
import de.se.trechner.interfaces.ActionsInterface;
import de.se.trechner.interfaces.FrameInterface;
import de.se.trechner.model.CSSNodeIDs;
import de.se.trechner.model.LangModel;
import de.se.trechner.model.ToolbarActions;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert die Toolbar
 *
 * @author ruess_c
 * @version 2017-12-16
 * @see de.se.trechner.interfaces.ActionsInterface
 */
public class ToolBar extends AnchorPane implements ActionsInterface<ToolbarActions> {

    private List<Hyperlink> hlList = new ArrayList<Hyperlink>();

    /**
     * Baut die Komponente nach den Vorgaben aus der ToolbarActions auf.
     *
     * @param fi das Frameinterface ermöglicht den Zugriff auf die GUI
     * @see de.se.trechner.model.ToolbarActions
     */
    public ToolBar(FrameInterface fi) {
        super();

        //Setup Language-Pack & Listener
        LangModel langModel = LangModel.getInstance();
        ToolbarActionsListener tal = ToolbarActionsListener.getInstance(fi);

        // Setup Container for Click-Items
        TextFlow leftContainer = new TextFlow();
        leftContainer.setId(CSSNodeIDs.TLEFTCONTAINER);
        TextFlow rightContainer = new TextFlow();
        rightContainer.setId(CSSNodeIDs.TRIGHTCONTRAINER);

        // Setup Buttons
        int lastRotationGroup = -1;
        for (ToolbarActions ta : ToolbarActions.values()) {
            if (ta.getRotationGroup() != lastRotationGroup) {
                Hyperlink hl = new Hyperlink();
                hl.setId(ta.toString());
                hl.addEventHandler(ActionEvent.ACTION, tal);

                hl.setText(langModel.getKeyCaption(ta.toString()));
                hl.setAccessibleText(langModel.getAccessibleText(ta.toString()));
                hl.setAccessibleHelp(langModel.getAccessibleHelp(ta.toString()));

                hl.setFont(Font.font("Lato-Bold", FontWeight.BOLD, 20));
                hlList.add(hl);

                // Search associated GridActions and distribute the Hyperlinks in the Containers
                if (ta.getContainerID() == ta.TB_CONTAINER_ID_LEFT) {
                    if (!leftContainer.getChildren().isEmpty()) {
                        leftContainer.getChildren().add(getSeparator());
                    }
                    leftContainer.getChildren().add(hl);
                }

                if (ta.getContainerID() == ta.TB_CONTAINER_ID_RIGHT) {
                    if (!rightContainer.getChildren().isEmpty()) {
                        rightContainer.getChildren().add(getSeparator());
                    }
                    rightContainer.getChildren().add(hl);
                }
            }
            lastRotationGroup = ta.getRotationGroup();
        }


        this.setTopAnchor(leftContainer,0.0);
        this.setLeftAnchor(leftContainer,0.0);
        this.setRightAnchor(rightContainer,0.0);
        this.setBottomAnchor(rightContainer,0.0);

        this.getChildren().addAll(leftContainer,rightContainer);
    }

    /**
     * Gibt einen Textbasierten Separator zurück
     *
     * @return Hochstrich mit Leerzeichen
     */
    private Text getSeparator() {
        Text t = new Text(" | ");
        t.setId(CSSNodeIDs.TSEPARATOR);
        t.setFont(Font.font("Lato", FontWeight.NORMAL, 20));
        t.setFill(Paint.valueOf("#FFFFFF"));
        return t;
    }

    /**
     * Anfrage um den Fokus auf einen Hyperlink zu setzen
     *
     * @param tbac Identifizierer für den Hyperlink
     */
    @Override
    public void requestFocus(ToolbarActions tbac) {
        for (Hyperlink hl: hlList) {
            if (hl.getId().equals(tbac.toString())) {
                hl.requestFocus();
            }
        }
    }

    /**
     * Führt die mit dem Hyperlink verknüpfte Aktion aus
     *
     * @param tbac Identifizierer für den Hyperlink
     */
    @Override
    public void fireActionEvent(ToolbarActions tbac) {
        for (Hyperlink hl: hlList) {
            if (hl.getId().equals(tbac.toString())) {
                hl.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
                hl.fire();
            }
        }
    }

    /**
     * Beendet das Ausführen einer Hyperlink Aktion
     *
     * @param tbac Identifizierer für den Hyperlink
     */
    @Override
    public void releaseActionEvent(ToolbarActions tbac) {
        for (Hyperlink hl: hlList) {
            if (hl.getId().equals(tbac.toString())) {
                hl.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
            }
        }
    }
}
