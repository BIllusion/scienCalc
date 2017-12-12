package de.se.trechner.interfaces;


import de.se.trechner.model.GridActions;
import de.se.trechner.model.ToolbarActions;

public interface FrameInterface {

    public void setBigLabel(String message);

    public void setSmallLabel(String message);

    public String getIdFromFocus();

    public ActionsInterface<ToolbarActions> getToolBar();

    public ActionsInterface<GridActions> getFuncGrid();

    public ActionsInterface<GridActions> getNrGrid();

    public boolean isBigLabelFocused();

    public void setInputFocus();

}
