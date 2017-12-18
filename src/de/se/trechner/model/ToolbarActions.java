package de.se.trechner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Sammlung aller Button Definitionen für die Toolbar.
 * Jeder Eintrag definiert einen Button mit einer ContainerID (zugehörigkeit Links oder Rechts)
 * und einer Rotationsgruppe. Elemente einer Rotationsgruppe werden nacheinander durchgewechselt
 *
 * @author ruess_c
 * @version 2017-12-16
 * @see de.se.trechner.view.ToolBar
 */
public enum ToolbarActions {
    // TAB-Leiste --------------------------------------
    // Bereich 1
    TDEG  (0,0),
    TRAD (0,0),
    TGRAD  (0,0),
    TFE   (0,1),
    THELP (0,2),

    // Bereich 2
    TMS (1,0),
    TMR (1,1),
    TMC (1,2);

    private final int containerID;
    private final int rotationGroup;


    public static final int TB_CONTAINER_ID_LEFT = 0;
    public static final int TB_CONTAINER_ID_RIGHT = 1;
    /**
     * Sammlung aller Button Definitionen innerhalb der Grids.
     * Jeder Eintrag definiert einen Button mit einer Gruppennummer, Spalte, Reihe, Spaltenausbreitung und Reihenausbreitung
     *
     * @param containerID Zugehörigkeit zur linken Seite oder zu rechten
     * @param rotationGroup Zugehörigkeit zu anderen Elementen
     */
    ToolbarActions(int containerID, int rotationGroup) {
        this.containerID = containerID;
        this.rotationGroup = rotationGroup;
    }

    /**
     * Gibt die ContainerID des Elements zurück
     *
     * @return ContainerID
     */
    public int getContainerID() {
        return this.containerID;
    }

    /**
     * Gibt die Rotationsgruppe des Elements zurück
     *
     * @return Rotationsgruppe
     */
    public int getRotationGroup() {
        return this.rotationGroup;
    }

    /**
     * Gibt das nächste Element zurück, das sich in der selben Rotationsgruppe und Container
     * befindet wie das Element selbst.
     *
     * @return Nächstes Element
     */
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
