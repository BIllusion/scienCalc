package de.se.trechner.model;

public enum ToolbarActionCmds {
    // TAB-Leiste --------------------------------------
    // Bereich 1
    TDEG  (0),
    TFE   (0),

    // Bereich 2
    TMS (1),
    TMR (1),
    TMC (1);

    private final int ContainerID;


    public static final int TB_CONTAINER_ID_LEFT = 0;
    public static final int TB_CONTAINER_ID_RIGHT = 1;

    ToolbarActionCmds(int ContainerID) {
        this.ContainerID = ContainerID;
    }

    public int getContainerID() {
        return this.ContainerID;
    }

}
