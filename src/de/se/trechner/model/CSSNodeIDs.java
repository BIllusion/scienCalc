package de.se.trechner.model;


public enum CSSNodeIDs {

    // Basis Komponenten
    BASEANCHORPANE,     // Basis Pane auf der alles liegt.
        OUTERVBOX,          // Unterteilung in oben und unten
            INNERVBOX,          // Oberer Teil: Beinhaltet Display und Toolbar
                TOOLBAR,            // Beinhaltet Hyperlinks mit Mode-Switch und Memory Functions (AnchorPane)
                    TLEFTCONTAINER,
                    TRIGHTCONTRAINER,
                    TSEPERATOR,
                DISPLAY,            // Beinhaltet die 2 Ausgabelabels
                    SMALLMSGBOX,
                    BIGMSGBOX,
            GRIDHBOX,       // Hält beide Tabellen in sich
                FUNCGRID,   // Tabelle mit Funktionen (Gridpane)
                NRGRID,     // Zahlenblock und Basis-Operationen (GridPane)
}
