/***
 * Class which genenerates a sudoku with a unique solution
 */
package game.logic;

import board.SudokuBoard;

import java.util.Random;
import java.util.Stack;

import static constans.Dimensions.MAX_INDEX;
import static constans.Dimensions.BOARD_SIZE;
import static constans.Dimensions.MIN_INDEX;
import static constans.Dimensions.SUB_BOARD_SIZE;

public final class PlainSudokuGenerator implements SudokuGenerator {
  private final class PairYX {
    private final int x;
    private final int y;

    PairYX(final int y, final int x) {
      this.x = x;
      this.y = y;
    }

    PairYX(final PairYX toCopy) {
      this.x = toCopy.getX();
      this.y = toCopy.getY();
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    @Override
    public boolean equals(final Object obj) {
      boolean result = false;
      if (obj == null)  {
          throw new NullPointerException();
      } else if (obj.getClass() == this.getClass()) {
        PairYX other = (PairYX) obj;
        if (other.getY() == this.getY() && other.getX() == this.getX()) {
          result = true;
        }
      }
      return result;
    }


  }


  @Override
  public SudokuBoard generateSudoku(final Difficulty difficulty) {
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    SudokuBoard sudokuBoard = new SudokuBoard(board);
    Stack<PairYX> pointsBlocked = new Stack<>();
    Stack<PairYX> roadTaken = new Stack<>();
    boolean deadEnd = false;

    while (sudokuBoard.verify() != Sudoku.State.CORRECT) {
      PairYX pair;

      do {
        pair = new PairYX(getRandomIndex(), getRandomIndex());
      } while (sudokuBoard.getCellValue(pair.getY(), pair.getX()) != 0
              || pointsBlocked.contains(pair));

      if (!foundASolution(pair, sudokuBoard)) {
        pointsBlocked.push(new PairYX(pair));
      }

      else if (!pointsBlocked.isEmpty()) {
        pointsBlocked.pop();
        deadEnd = false;
      } else if (!deadEnd) {
        roadTaken.push(new PairYX(pair));
      }

      sudokuBoard.printBoard();
    }
    return sudokuBoard;
  }

  private boolean foundASolution(final PairYX pair,
                                 final SudokuBoard sudokuBoard) {
    final int magicNr = 10;
    boolean[] values = new boolean[magicNr];
    values[0] = true;
    boolean result = true;

    do {
      if (check(values)) {
        sudokuBoard.setCell(pair.getY(), pair.getX(), 0);
        result = false;
      }

      int randomValue = getRandomValue();
      values[randomValue] = true;
      sudokuBoard.setCell(pair.getY(), pair.getX(), randomValue);
    } while (sudokuBoard.verify() == Sudoku.State.WRONG);

    return result;
  }

  private int seperateIntoSubGrids() {
    return 1;
  }

  private boolean check(final boolean[] checked) {
    for (boolean val: checked) {
      if (!val) {
        return false;
      }
    }
    return true;
  }

  //MAX_INDEX + 1 because its exclusive
  private int getRandomIndex() {
    Random random = new Random();
    return random.nextInt(MIN_INDEX, MAX_INDEX + 1);
  }

  private int getRandomValue() {
    final int min = 1;
    final int maxExclusive = 10;
    Random random = new Random();
    return random.nextInt(min, maxExclusive);
  }

  private int determineSubBoardIndex(final int y, final int x) {
    return SUB_BOARD_SIZE * (y / SUB_BOARD_SIZE) + (x / SUB_BOARD_SIZE);
  }
}
