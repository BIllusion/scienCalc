package de.se.trechner.interfaces;

import de.se.trechner.model.ActionCmds;

public interface GridInterface {

    public void setButtonFocus(ActionCmds ac);

    public void fireButtonEvent(ActionCmds ac);

    public void releaseButtonEvent(ActionCmds ac);

    public void fireOnFocus();
}
