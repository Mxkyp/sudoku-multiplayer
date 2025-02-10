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

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

  }


  @Override
  public SudokuBoard generateSudoku(final Difficulty difficulty) {
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    Stack<Integer> inputed = new Stack<>();
    SudokuBoard sudokuBoard = new SudokuBoard(board);

    while (sudokuBoard.verify() != Sudoku.State.CORRECT) {
      PairYX pair;

      do {
        pair = new PairYX(getRandomIndex(), getRandomIndex());
      } while (board[pair.getY()][pair.getX()] != 0);

      do {
        int randomValue = getRandomValue();
        sudokuBoard.setCell(pair.getY(), pair.getX(), randomValue);
        sudokuBoard.printBoard();
      } while (sudokuBoard.verify() == Sudoku.State.WRONG);

    }
    return sudokuBoard;
  }

  private int seperateIntoSubGrids() {
    return 1;
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
