/***
 * Class representing the 9x9 sudoku game board, which consists of:
 * 81 cells which references are also stored as:
 * 1.nine 3x3 'subBoards'
 * 2.nine rows
 * 3.nine columns
 */
package board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static constans.Dimensions.BOARD_SIZE;
import static constans.Dimensions.MIN_INDEX;
import static constans.Dimensions.MAX_INDEX;

public final class SudokuBoard implements Board {
  private static final Logger logger =
          LoggerFactory.getLogger(SudokuBoard.class);
  private final SudokuCell[][] board = new SudokuCell[BOARD_SIZE][BOARD_SIZE];

  public SudokuBoard(final int[][] dummyBoard) {
    initBoard(dummyBoard);
  }

  public void setCell(final int y, final int x, final int value) {
    this.board[y][x].setValue(value);
  }

  public SudokuCell[][] getBoard() {
    SudokuCell[][] boardCopy = new SudokuCell[BOARD_SIZE][BOARD_SIZE];

    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        boardCopy[i][j] = new SudokuCell(this.board[i][j].getValue());
      }
    }

    return boardCopy;
  }

  public int getCellValue(final int rowIndex, final int colIndex) {
    int result = -1;

    if (rowIndex >= MIN_INDEX && colIndex >= MIN_INDEX
            && rowIndex <= MAX_INDEX && colIndex <= MAX_INDEX) {
      result = board[rowIndex][colIndex].getValue();
    }

    return result;
  }

  public void printBoard() {
    final int initCapacity = 100;
    StringBuffer buffer = new StringBuffer(initCapacity);

    buffer.append("\n");
    for (SudokuCell[] row: board) {
      for (SudokuCell cell: row) {
        buffer.append(cell.getValue());
        buffer.append(" ");
      }
      buffer.append("\n");
    }

    logger.info(buffer.toString());
  }

  private void initBoard(final int[][] values)            {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        this.board[i][j] = new SudokuCell(values[i][j]);
      }
    }
  }



}
