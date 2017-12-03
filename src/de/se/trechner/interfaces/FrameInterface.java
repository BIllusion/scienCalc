package de.se.trechner.interfaces;

import javafx.scene.control.Button;

public interface FrameInterface {

    public void setBigLabel(String message);

    public void setSmallLabel(String message);

    public GridInterface getFuncGrid();

    public GridInterface getNrGrid();

    public Button getFocusedButton();

    public boolean isBigLabelFocused();

}
