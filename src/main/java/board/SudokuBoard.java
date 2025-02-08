/***
 * Class representing the 9x9 sudoku game board, which consists of:
 * 81 cells which references are also stored as:
 * 1.nine 3x3 'subBoards'
 * 2.nine rows
 * 3.nine columns
 */
package board;
import game.logic.SudokuGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static game.logic.SudokuDimensions.BOARD_SIZE;
import static game.logic.SudokuDimensions.MIN_INDEX;
import static game.logic.SudokuDimensions.MAX_INDEX;

public final class SudokuBoard  {
    private static final Logger logger =
            LoggerFactory.getLogger(SudokuBoard.class);
    private final SudokuCell[][] board = new SudokuCell[BOARD_SIZE][BOARD_SIZE];

    public SudokuBoard(final int[][] dummyBoard) {
        initBoard(this.board, dummyBoard);
    }

    public SudokuBoard(final SudokuGenerator sudokuGenerator) {

    }

    public SudokuCell[][] getBoard() {
        SudokuCell[][] boardCopy = new SudokuCell[BOARD_SIZE][BOARD_SIZE];
        initBoard(boardCopy, this.board);

        return boardCopy;
    }

    public int getCellValue(final int rowIndex, final int colIndex) {
        int result = -1;

        if (rowIndex >= MIN_INDEX && colIndex >= MIN_INDEX
             && rowIndex <= MAX_INDEX && colIndex <= MAX_INDEX) {
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

    private void initBoard(final SudokuCell[][] boardToInit,
                           final int[][] values)            {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boardToInit[i][j] = new SudokuCell(values[i][j]);
            }
        }
    }

    private void initBoard(final SudokuCell[][] boardToInit,
                           final SudokuCell[][] values)            {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boardToInit[i][j] = new SudokuCell(values[i][j].getValue());
            }
        }
    }
}
