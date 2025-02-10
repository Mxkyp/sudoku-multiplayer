/***
 * A utility class used to verify
 * lists of SudokuCells against the rules of the game
 * if for they have one of each number from 1-9 inclusive
 */
package game.logic;

import board.SudokuCell;

import java.util.List;

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

  /***
   * returns whether the list of elements is valid in terms of sudoku
   * (has only one of each digit from 1-9)
   */
  public static State verify(final List<SudokuCell> members) {
    final int nrOfPossibleValues = 10; //0 == unfilled, 1 to 9
    int[] occurenceCount = new int[nrOfPossibleValues];

    for (SudokuCell cell: members) {
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
