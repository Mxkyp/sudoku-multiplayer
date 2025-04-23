/***
 * A utility class used to verify
 * lists of SudokuCells against the rules of the game
 * if for they have one of each number from 1-9 inclusive
 */
package logic;

import board.SudokuBoard;

public final class SudokuGame {
  private SudokuBoard board;

  public SudokuGame(final SudokuGenerator generator) {
    this.board = generator.generateSudoku(SudokuGenerator.Difficulty.EASY);
    this.board.printBoard();
  }

  public void setCell(final int rowNr, final int colNr, final int val) {
    board.setCell(rowNr, colNr, val);
  }

  public int getCellValue(final int row, final int col) {
    return board.getCellValue(row, col);
  }

  public enum State {
    WRONG,
    UNKNOWN,
    CORRECT;
  }

}
