package scienCalc.model;

import java.util.zip.CheckedOutputStream;

public enum ActionCmds {

    // Func Grid --------------------------------------
    // B1    i|c|r|w|h
    SQR     (0,0,0,1,1),
    CUBIC   (0,1,0,1,1),
    XPOWY   (0,0,1,1,1),
    RECVAL  (0,1,1,1,1),
    SQRT    (0,0,2,1,1),
    YSQRT   (0,1,2,1,1),
    EXPF    (0,0,3,1,1),
    LOG     (0,1,3,1,1),
    EX      (0,0,4,1,1),
    LN      (0,1,4,1,1),

    // B2    i|c|r|w|h
    SIN     (0,2,0,1,1),
    COS     (0,3,0,1,1),
    TAN     (0,4,0,1,1),
    ARCSIN  (0,2,1,1,1),
    ARCCOS  (0,3,1,1,1),
    ARCTAN  (0,4,1,1,1),

    // B3    i|c|r|w|h
    EXP     (0,2,2,1,1),
    DMS     (0,3,2,1,1),
    DEG     (0,4,2,1,1),

    // B4        i|c|r|w|h
    MOD         (0,2,3,1,1),
    OBRACKET    (0,3,3,1,1),
    CBRACKET    (0,4,3,1,1),
    FACT        (0,2,4,1,1),
    PI          (0,3,4,1,1),
    SIGNCHANGE  (0,4,4,1,1),

    // Nr Grid --------------------------------------
    // B1        i|c|r|w|h
    CLEARINPUT  (1,0,0,1,1),
    DELETEALL   (1,1,0,1,1),
    DELLASTCHAR (1,2,0,1,1),

    // B2    i|c|r|w|h
    ONE     (1,0,3,1,1),
    TWO     (1,1,3,1,1),
    THREE   (1,2,3,1,1),
    FOUR    (1,0,2,1,1),
    FIVE    (1,1,2,1,1),
    SIX     (1,2,2,1,1),
    SEVEN   (1,0,1,1,1),
    EIGHT   (1,1,1,1,1),
    NINE    (1,2,1,1,1),
    ZERO    (1,1,4,1,1),
    KOMMA   (1,0,4,1,1),

    // B3        i|c|r|w|h
    DIVIDE      (1,3,0,1,1),
    MULTIPLY    (1,3,1,1,1),
    SUBTRACT    (1,3,2,1,1),
    ADDITION    (1,3,3,1,1),

    // B4        i|c|r|w|h
    EQUALS      (1,2,4,2,1);


    private final int GridID;
    private final int col;
    private final int row;
    private final int colSpan;
    private final int rowSpan;

    public static final int FUNC_GRID_ID = 0;
    public static final int NR_GRID_ID = 1;

    ActionCmds(int GridID, int col, int row, int colSpan, int rowSpan) {
        this.GridID = GridID;
        this.col = col;
        this.row = row;
        this.colSpan = colSpan;
        this.rowSpan = rowSpan;
    }

    public int getGridID() {
        return this.GridID;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public int getColSpan() {
        return this.colSpan;
    }

    public int getRowSpan() {
        return this.rowSpan;
    }
}
