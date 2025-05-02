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
  private final int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

  public SudokuBoard(final int[][] dummyBoard) {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        this.board[i][j] = dummyBoard[i][j];
      }
    }
  }

  public SudokuBoard(final SudokuBoard boardToCopy) {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        this.board[i][j] = boardToCopy.getCellValue(i, j);
      }
    }
  }

  public void setCell(final int y, final int x, final int value) {
    this.board[y][x] = value;
  }

  public int[][] getBoard() {
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        board[i][j] = this.getCellValue(i, j);
      }
    }
    return board;
  }

  public int getCellValue(final int rowIndex, final int colIndex) {
    int result = -1;

    if (rowIndex >= MIN_INDEX && colIndex >= MIN_INDEX
            && rowIndex <= MAX_INDEX && colIndex <= MAX_INDEX) {
      result = board[rowIndex][colIndex];
    }

    return result;
  }

  public void printBoard() {
    final int initCapacity = 100;
    StringBuffer buffer = new StringBuffer(initCapacity);

    buffer.append("\n");
    for (int[] row : board) {
      for (int cell : row) {
        buffer.append(cell);
        buffer.append(" ");
      }
      buffer.append("\n");
    }

    logger.info(buffer.toString());
  }


}
