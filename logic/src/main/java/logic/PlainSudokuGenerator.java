/***
 * Class which genenerates a sudoku with a unique solution
 */
package logic;

import board.SudokuBoard;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

import static constans.Dimensions.*;
//TODO:a LOT of refactoring and reStructuring
public final class PlainSudokuGenerator implements SudokuGenerator {

  @SuppressWarnings("checkstyle:MagicNumber")
  @Override
  public SudokuBoard generateSudoku(final Difficulty difficulty) {
    int[][] mockBoard = new int[BOARD_SIZE][BOARD_SIZE];
    int[][] board = {
            { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
            { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
            { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
            { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
            { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
            { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
    };
    solveBoard(board);
    return new SudokuBoard(board);
  }

  private static boolean unsolveBoard(final int[][] board, final int[][] originalBoard) {
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
      board[row][col] = 0;
      counter++;
      return unsolveBoard(board, dummyBoard, counter);
    } else {
      return false;
    }
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

  private static boolean verifyPlacement(final int[][] board,
                                         final int row, final int col,
                                         final int value) {
    return !rowContainsValue(board, row, value)
            && !colContainsValue(board, col, value)
            && !subBoardContains(board, row, col, value);
  }

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
