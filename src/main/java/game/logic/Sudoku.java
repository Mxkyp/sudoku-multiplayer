/***
 * A utility class used to verify discrete parts of the SudokuGameBoard
 * (subBoards, rows, and columns) against the rules of the game
 * if for ex. a row has numbers from 1-9
 */
package game.logic;

import board.SudokuCell;
import board.SudokuSubBoard;
import lines.SudokuLine;

public final class Sudoku {

  private Sudoku() {
    throw new UnsupportedOperationException("This is a utility class "
            + "and cannot be instantiated");
  }

  public enum State {
    WRONG,
    UNKNOWN,
    CORRECT;
  }

  private static final class MinMaxPair {
    private final int max;
    private final int min;

    MinMaxPair(final int max, final int min) {
      this.max = max;
      this.min = min;
    }

    public int getMax() {
      return max;
    }

    public int getMin() {
      return min;
    }
  }

  public static State verify(final SudokuSubBoard subBoard) {
    final int nrOfPossibleValues = 10; //0 == unfilled, 1 to 9
    int[] occurenceCount = new int[nrOfPossibleValues];
    SudokuCell[][] board = subBoard.getBoard();

    for (SudokuCell[] row: board) {
      for (SudokuCell cell: row) {
        occurenceCount[cell.getValue()]++;
      }
    }

    final MinMaxPair occurrence =
            getMinMaxOccurences(occurenceCount, nrOfPossibleValues);

    State computedState;
    if (occurrence.getMax() > 1) {
      computedState = State.WRONG;
    } else if (occurrence.getMax() != 1 || occurrence.getMin() != 1) {
      computedState = State.UNKNOWN;
    } else {
      computedState = State.CORRECT;
    }

    return computedState;
  }

  /***
   * returns whether the line is a valid
   * (has only one of each digit from 1-9)
   */
  public static State verify(final SudokuLine inputLine) {
    final int nrOfPossibleValues = 10; //0 == unfilled, 1 to 9
    int[] occurenceCount = new int[nrOfPossibleValues];

    for (SudokuCell cell: inputLine.getMembers()) {
      occurenceCount[cell.getValue()]++;
    }

    final MinMaxPair occurrence =
            getMinMaxOccurences(occurenceCount, nrOfPossibleValues);

    State computedState;
    if (occurrence.getMax() > 1) {
      computedState = State.WRONG;
    } else if (occurrence.getMax() != 1 || occurrence.getMin() != 1) {
      computedState = State.UNKNOWN;
    } else {
      computedState = State.CORRECT;
    }

    return computedState;

  }

  private static MinMaxPair getMinMaxOccurences(final int[] occurences,
                                                final int nrOfPossibleValues) {
    int max = 0;
    int min = Integer.MAX_VALUE;

    /*
      starts from one
      because the number stored at 0 index
      is the number of cells unfilled
     */
    for (int i = 1; i < nrOfPossibleValues; i++) {
      if (occurences[i] > max) {
        max = occurences[i];
      } else if (occurences[i] < min) {
        min = occurences[i];
      }
    }

    return new MinMaxPair(max, min);
  }

}
