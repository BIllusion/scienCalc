package de.se.trechner.model;

public enum GridActions {

    // Func Grid --------------------------------------
    // B0    i|c|r|w|h
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

    // B1    i|c|r|w|h
    SIN     (1,2,0,1,1),
    COS     (1,3,0,1,1),
    TAN     (1,4,0,1,1),
    ARCSIN  (1,2,1,1,1),
    ARCCOS  (1,3,1,1,1),
    ARCTAN  (1,4,1,1,1),

    // B2    i|c|r|w|h
    EXP     (2,2,2,1,1),
    DMS     (2,3,2,1,1),
    DEG     (2,4,2,1,1),

    // B3        i|c|r|w|h
    MOD         (3,2,3,1,1),
    OBRACKET    (3,3,3,1,1),
    CBRACKET    (3,4,3,1,1),
    FACT        (3,2,4,1,1),
    PI          (3,3,4,1,1),
    SIGNCHANGE  (3,4,4,1,1),

    // Nr Grid --------------------------------------
    // B4        i|c|r|w|h
    CLEARINPUT  (4,0,0,1,1),
    DELETEALL   (4,1,0,1,1),
    DELLASTCHAR (4,2,0,1,1),

    // B5    i|c|r|w|h
    ONE     (5,0,3,1,1),
    TWO     (5,1,3,1,1),
    THREE   (5,2,3,1,1),
    FOUR    (5,0,2,1,1),
    FIVE    (5,1,2,1,1),
    SIX     (5,2,2,1,1),
    SEVEN   (5,0,1,1,1),
    EIGHT   (5,1,1,1,1),
    NINE    (5,2,1,1,1),
    ZERO    (5,1,4,1,1),
    KOMMA   (5,0,4,1,1),

    // B6        i|c|r|w|h
    DIVIDE      (6,3,0,1,1),
    MULTIPLY    (6,3,1,1,1),
    SUBTRACT    (6,3,2,1,1),
    ADDITION    (6,3,3,1,1),

    // B7        i|c|r|w|h
    EQUALS      (7,2,4,2,1);


    private final int groupID;
    private final int col;
    private final int row;
    private final int colSpan;
    private final int rowSpan;

    public static final int POTENZ_GROUP_ID = 0;
    public static final int TRIGO_GROUP_ID = 1;
    public static final int GRAD_GROUP_ID = 2;
    public static final int SPECIAL_GROUP_ID = 3;
    public static final int CLEAR_GROUP_ID = 4;
    public static final int NUMBER_GROUP_ID = 5;
    public static final int BASIC_GROUP_ID = 6;
    public static final int EQUALS_GROUP_ID = 7;

    public static final int[] FUNC_GRID_GROUP_IDS = {POTENZ_GROUP_ID, TRIGO_GROUP_ID, GRAD_GROUP_ID, SPECIAL_GROUP_ID};
    public static final int[] NR_GRID_GROUP_IDS = {CLEAR_GROUP_ID, NUMBER_GROUP_ID, BASIC_GROUP_ID, EQUALS_GROUP_ID};

    GridActions(int groupID, int col, int row, int colSpan, int rowSpan) {
        this.groupID = groupID;
        this.col = col;
        this.row = row;
        this.colSpan = colSpan;
        this.rowSpan = rowSpan;
    }

    public int getGroupID() {
        return this.groupID;
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
