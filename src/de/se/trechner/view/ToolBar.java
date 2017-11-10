package de.se.trechner.view;

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

    public ToolBar() {
        super();

        hDEG = new Hyperlink("DEG");
        hFE = new Hyperlink("F-E");
        hMS = new Hyperlink("MS");
        hMR = new Hyperlink("MR");
        hMC = new Hyperlink("MC");

        hDEG.setFont(baseFont);
        hFE.setFont(baseFont);

        modeSwitch = new TextFlow(hDEG, getSeperator(), hFE);
        memFunc = new TextFlow(hMS, getSeperator(), hMR, getSeperator(), hMC);


        this.setTopAnchor(modeSwitch,0.0);
        this.setLeftAnchor(modeSwitch,0.0);

        this.setRightAnchor(memFunc,0.0);
        this.setBottomAnchor(memFunc,0.0);

        this.getChildren().addAll(modeSwitch,memFunc);
    }

    private Text getSeperator() {
        Text t = new Text(" | ");
        t.setFont(baseFont);
        return t;
    }
}
