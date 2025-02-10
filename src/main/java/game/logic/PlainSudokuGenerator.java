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

  @Override
  public SudokuBoard generateSudoku(final Difficulty difficulty) {
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    Stack<Integer> inputed = new Stack<>();
    SudokuBoard sudokuBoard = new SudokuBoard(board);

    while (sudokuBoard.verify() != Sudoku.State.CORRECT) {
      int y;
      int x;

      do {
        y = getRandomIndex();
        x = getRandomIndex();
      } while (board[y][x] != 0);

      int randomValue = getRandomValue();


    }
    return null;
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
