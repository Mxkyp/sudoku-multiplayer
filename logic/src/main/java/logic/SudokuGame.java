/***
 * A utility class used to verify
 * lists of SudokuCells against the rules of the game
 * if for they have one of each number from 1-9 inclusive
 */
package logic;

import board.SudokuBoard;
import org.apache.commons.lang3.NotImplementedException;

public final class SudokuGame {
  private SudokuBoard solved;
  private SudokuBoard startingPuzzle;

  public SudokuGame(final SudokuGenerator generator) {
    this.solved = generator.generateSudoku();
    startingPuzzle = generator.createPuzzleFromSolved(solved, SudokuGenerator.Difficulty.EASY);
  }

  public int[][] getSolved() {
    return startingPuzzle.getIntRepresentation();
  }

  public State checkState(final SudokuBoard board) {
    throw new NotImplementedException();
  }

  public enum State {
    WRONG,
    UNKNOWN,
    CORRECT
  }

}
