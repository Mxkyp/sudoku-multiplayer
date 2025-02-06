package board;

public final class SudokuBoard {
    private static final int BOARD_SIZE = 9;
    SudokuCell[][] board = new SudokuCell[BOARD_SIZE][BOARD_SIZE];

    public SudokuBoard(final int[][] dummyBoard) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j].setValue(dummyBoard[i][j]);
            }
        }
    }

    public SudokuCell[][] getBoard() {
        return board;
    }

    public void setBoard(final SudokuCell[][] board) {
        this.board = board;
    }
}
