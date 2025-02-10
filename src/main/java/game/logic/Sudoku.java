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

  public static State verify(final SudokuSubBoard subBoard) {
    final int nrOfPossibleValues = 10; //0 == unfilled, 1 to 9
    int[] occurenceCount = new int[nrOfPossibleValues];
    SudokuCell[][] board = subBoard.getBoard();

    for (SudokuCell[] row: board) {
      for (SudokuCell cell: row) {
        occurenceCount[cell.getValue()]++;
      }
    }

    int maxOccurence = 0;
    int minOccurence = Integer.MAX_VALUE;

    // starts from 1 because zero's
    // (which occurence number is at index zero)
    // represent empty cells
    for (int i = 1; i < nrOfPossibleValues; i++) {
      if (occurenceCount[i] > maxOccurence) {
        maxOccurence = occurenceCount[i];
      } else if (occurenceCount[i] < minOccurence) {
        minOccurence = occurenceCount[i];
      }
    }

    State computedState;
    if (maxOccurence > 1) {
      computedState = State.WRONG;
    } else if (maxOccurence != 1 || minOccurence != 1) {
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

    int maxOccurence = 0;
    int minOccurence = Integer.MAX_VALUE;

    // starts from 1 because zero's
    // (which occurence number is at index zero)
    // represent empty cells
    for (int i = 1; i < nrOfPossibleValues; i++) {
      if (occurenceCount[i] > maxOccurence) {
        maxOccurence = occurenceCount[i];
      } else if (occurenceCount[i] < minOccurence) {
        minOccurence = occurenceCount[i];
      }
    }

    State computedState;
    if (maxOccurence > 1) {
      computedState = State.WRONG;
    } else if (maxOccurence != 1 || minOccurence != 1) {
      computedState = State.UNKNOWN;
    } else {
      computedState = State.CORRECT;
    }

    return computedState;
  }

}
