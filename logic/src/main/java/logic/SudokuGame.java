/***
 * A utility class used to verify
 * lists of SudokuCells against the rules of the game
 * if for they have one of each number from 1-9 inclusive
 */
package logic;

import board.SudokuBoard;

public final class SudokuGame {
  private SudokuBoard board;

  public enum State {
    WRONG,
    UNKNOWN,
    CORRECT;
  }

}
