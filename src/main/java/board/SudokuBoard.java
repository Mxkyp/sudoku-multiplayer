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

import static constans.Dimensions.BOARD_SIZE;
import static constans.Dimensions.MIN_INDEX;
import static constans.Dimensions.MAX_INDEX;

public final class SudokuBoard extends Board {
  private static final Logger logger =
          LoggerFactory.getLogger(SudokuBoard.class);
  private final SudokuCell[][] board = new SudokuCell[BOARD_SIZE][BOARD_SIZE];

  public SudokuBoard(final int[][] dummyBoard) {
    initBoard(this.board, dummyBoard);
  }

  public SudokuBoard(final SudokuGenerator sudokuGenerator) {

  }

  public SudokuCell[][] getBoard() {
    return super.getBoard(BOARD_SIZE, this.board);
  }

  public int getCellValue(final int rowIndex, final int colIndex) {
    return super.getCellValue(rowIndex, colIndex,
            MAX_INDEX, MIN_INDEX, this.board);
  }

  public void printBoard() {
    super.printBoard(this.board);
  }

  private void initBoard(final SudokuCell[][] boardToInit,
                         final int[][] values)            {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        boardToInit[i][j] = new SudokuCell(values[i][j]);
      }
    }
  }

}
