package game.logic;

public abstract class SudokuDimensions {
    //number of rows and columns in the main board
    public static final int BOARD_SIZE = 9;
    public static final int NR_OF_SUBBOARDS = 9;

    public static final int MIN_INDEX = 0;

    public static final int MAX_INDEX = 8;

    //number of rows and columns in the sub boards
    // (each boards has 9 3x3 subBoards)
    public static final int SUB_BOARD_SIZE = 3;

}
