package de.se.trechner.model;

import java.util.ArrayList;
import java.util.List;

public enum ToolbarActions {
    // TAB-Leiste --------------------------------------
    // Bereich 1
    TDEG  (0,0),
    TRAD (0,0),
    TGRAD  (0,0),
    TFE   (0,1),

    // Bereich 2
    TMS (1,0),
    TMR (1,1),
    TMC (1,2);

    private final int containerID;
    private final int rotationGroup;


    public static final int TB_CONTAINER_ID_LEFT = 0;
    public static final int TB_CONTAINER_ID_RIGHT = 1;

    ToolbarActions(int containerID, int rotationGroup) {
        this.containerID = containerID;
        this.rotationGroup = rotationGroup;
    }

    public int getContainerID() {
        return this.containerID;
    }

    public int getRotationGroup() {
        return this.rotationGroup;
    }

    public ToolbarActions getNextGroupItem() {
        // Variablen Init
        int positionInGroup = -1;

        // Hält alle Elemente der Group
        List<ToolbarActions> groupItems = new ArrayList<ToolbarActions>();
        for (ToolbarActions tac: ToolbarActions.values()) {
            if (this.rotationGroup == tac.rotationGroup && this.getContainerID() == tac.containerID) {
                groupItems.add(tac);
                if (this == tac) {
                    positionInGroup = groupItems.size()-1;
                }
            }
        }
        return groupItems.get((positionInGroup + 1) % groupItems.size());
    }

}
