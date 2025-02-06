package board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SudokuBoard {
    public static final int BOARD_SIZE = 9;
    private final Logger logger = LoggerFactory.getLogger(SudokuBoard.class);
    SudokuCell[][] board = new SudokuCell[BOARD_SIZE][BOARD_SIZE];

    public SudokuBoard(final int[][] dummyBoard) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new SudokuCell(dummyBoard[i][j]);
            }
        }
    }

    public SudokuCell[][] getBoard() {
        return board;
    }

    public void setBoard(final SudokuCell[][] board) {
        this.board = board;
    }

    public int getCellValue(final int rowIndex, final int colIndex) {
        int result = -1;
        final int minIndex = 0;
        final int maxIndex = 8;

        if (rowIndex >= minIndex && colIndex >= minIndex
             && rowIndex <= maxIndex && colIndex <= maxIndex) {
            result = this.board[rowIndex][colIndex].getValue();
        }

        return result;
    }

    public void printBoard() {
        final int initCapacity = 100;
        StringBuffer buffer = new StringBuffer(initCapacity);

        buffer.append("\n");
        for (SudokuCell[] row: this.board) {
            for (SudokuCell cell: row) {
                buffer.append(cell.getValue());
                buffer.append(" ");
            }
            buffer.append("\n");
        }

        logger.info(buffer.toString());
    }
}
