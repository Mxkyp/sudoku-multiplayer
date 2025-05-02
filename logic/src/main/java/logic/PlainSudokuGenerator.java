/***
 * Class which generates a sudoku with a unique solution
 */
package logic;

import board.SudokuBoard;
import board.SudokuCell;

import java.util.Arrays;
import java.util.Random;

import static constans.Dimensions.*;
public final class PlainSudokuGenerator implements SudokuGenerator {

  /***
   * Generate a unique sudoku solution, TODO: with the minimal number of clues
   * @param difficulty
   * @return a valid sudoku puzzle
   */



  @Override
  public SudokuBoard generateSudoku() {
    final int[][] board = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0 },
            {0, 0, 3, 6, 0, 0, 0, 0, 0 },
            {0, 7, 0, 0, 9, 0, 2, 0, 0 },
            {0, 5, 0, 0, 0, 7, 0, 0, 0 },
            {0, 0, 0, 0, 4, 5, 7, 0, 0 },
            {0, 0, 0, 1, 0, 0, 0, 3, 0 },
            {0, 0, 1, 0, 0, 0, 0, 6, 8 },
            {0, 0, 8, 5, 0, 0, 0, 1, 0 },
            {0, 9, 0, 0, 0, 0, 4, 0, 0 }
    };
    solveBoard(board);

    return new SudokuBoard(board);
  }

  @Override
  public SudokuBoard createPuzzleFromSolved(SudokuBoard sudoku, Difficulty difficulty) {
    int[][] board = new int[9][9];
    for(int i = 0; i < 9; i++){
      for (int j = 0; j < 9; j++) {
        board[i][j] = sudoku.getCellValue(i, j);
      }
    }
    int[][] mockBoard = new int[BOARD_SIZE][BOARD_SIZE];
    for(int i = 0; i < board.length; i++){
      mockBoard[i] = Arrays.copyOf(board[i], board.length);
    }

    int emptyCellNumber = getEmptyCellNumber(difficulty);

    int counter = 0;
    while (counter != emptyCellNumber) {
      if (unsolveBoard(board, mockBoard)) {
        counter++;
      }
    }

    return new SudokuBoard(board);
  }

  /***
   * Recursive sudoku solver
   * @param board representing the 9x9 sudoku puzzle
   * @return unique solution of the given board
   */
  private static boolean solveBoard(final int[][] board) {
    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int column = 0; column < BOARD_SIZE; column++) {
        //cell is unfilled
        if (board[row][column] == 0) {
          return tryFillingCell(board, row, column);
        }
      }
    }
    return true;
  }

  private int getEmptyCellNumber(final Difficulty difficulty) {
    int targetEmptyCells;

    if (difficulty == Difficulty.EASY) {
      targetEmptyCells = 40;
    } else if (difficulty == Difficulty.MEDIUM) {
      targetEmptyCells = 50;
    } else if (difficulty == Difficulty.HARD) {
      targetEmptyCells = 60;
    } else {
      targetEmptyCells = 64;
    }
    return targetEmptyCells;
  }

  /***
   * remove a number from the board, using brute force approach
   * @param board - the board from which to remove cells
   * @param originalBoard - the initial solved board
   * @return true if succeeded, false if removed an empty cell
   *         or created an unsolvable board
   */
  private static boolean unsolveBoard(final int[][] board,
                                      final int[][] originalBoard) {
    int row;
    int col;
    Random rand = new Random();
    int[][] dummyBoard = new int[BOARD_SIZE][BOARD_SIZE];

    row = rand.nextInt(0, BOARD_SIZE);
    col = rand.nextInt(0, BOARD_SIZE);

    for (int i = 0; i < BOARD_SIZE; i++) {
      System.arraycopy(board[i], 0, dummyBoard[i], 0, BOARD_SIZE);
    }

    dummyBoard[row][col] = 0;
    solveBoard(dummyBoard);

    if (compareArrays(originalBoard, dummyBoard)) {
      if (board[row][col] != 0) {
        board[row][col] = 0;
        return true;
      }
    }

    return false;
  }

  /**
   * attempt filling a cell properly with sudoku rules
   * @param board
   * @param row
   * @param col
   * @return
   */
  private static boolean tryFillingCell(final int[][] board,
                                        final int row, final int col) {
    boolean[] triedVal = new boolean[BOARD_SIZE + 1];
    triedVal[0] = true;
    int valueToTry;

    //try filling it with random numbers
    while ((valueToTry = getRandomValue(triedVal)) != -1) {

      //record which one you tried
      triedVal[valueToTry] = true;

      //check if it breaks the board
      if (verifyPlacement(board, row, col, valueToTry)) {
        board[row][col] = valueToTry;

        if (solveBoard(board)) {
          return true;
        } else {
          board[row][col] = 0;
        }
      }
    }
    return false;
  }

  private static boolean compareArrays(final int[][] copy,
                                       final int[][] original) {
    for (int i = 0; i < original.length; i++) {
      for (int j = 0; j < original[0].length; j++) {
        if (copy[i][j] != original[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  /***
   * get random val from 1-9 that is not tried
   * @param triedValue an array of tried values from 1-9
   * @return a valid val from 1-9 or -1 if all have been tried
   */
  private static int getRandomValue(final boolean[] triedValue) {
    if (triedAll(triedValue)) {
      return -1;
    }

    Random rand = new Random();
    int digit = 0;

    do {
      digit = rand.nextInt(1, BOARD_SIZE + 1);
    } while (triedValue[digit]);

    return digit;
  }

  /**
   * verify a board after placing a @param value in cell[row][col]
   * @param board
   * @param row
   * @param col
   * @param value
   * @return true if the new state is valid else false
   */
  private static boolean verifyPlacement(final int[][] board,
                                         final int row, final int col,
                                         final int value) {
    return !rowContainsValue(board, row, value)
            && !colContainsValue(board, col, value)
            && !subBoardContains(board, row, col, value);
  }

  /**
   * check if all values between 1-9 have been tried
   * @param triedValue a bool array where index = number and
   *                   true represents that it has been tried
   * @return
   */
  private static boolean triedAll(final boolean[] triedValue) {
    for (boolean tried: triedValue) {
      if (!tried) {
        return false;
      }
    }
    return true;
  }

  private static boolean rowContainsValue(final int[][] board,
                                          final int row,
                                          final int value) {

    for (int c = 0; c < BOARD_SIZE; c++) {
      if (board[row][c] == value) {
        return true;
      }
    }
    return false;
  }

  private static boolean colContainsValue(final int[][] board,
                                          final int column,
                                          final int value) {
    for (int r = 0; r < BOARD_SIZE; r++) {
      if (board[r][column] == value) {
        return true;
      }
    }
    return false;
  }

  private static boolean subBoardContains(final int[][] board,
                                          final int row,
                                          final int col,
                                          final int value) {

    int startRow = (row / SUB_BOARD_SIZE) * SUB_BOARD_SIZE;
    int startCol = (col / SUB_BOARD_SIZE) * SUB_BOARD_SIZE;

    for (int i = 0; i < SUB_BOARD_SIZE; i++) {
      for (int j = 0; j < SUB_BOARD_SIZE; j++) {
        if (board[startRow + i][startCol + j] == value) {
          return true;
        }
      }
    }
    return false;
  }

}
