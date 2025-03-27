package logic;

import board.SudokuBoard;
import org.junit.jupiter.api.BeforeEach;

class SudokuTest {
  SudokuBoard testBoard;
  final static int[][] sampleBoard = {
          {5, 1, 1, 0, 7, 0, 0, 0, 0},
          {6, 2, 2, 1, 9, 5, 0, 0, 0},
          {9, 3, 2, 1, 4, 5, 7, 6, 8},
          {8, 4, 3, 0, 6, 0, 0, 0, 3},
          {4, 5, 4, 8, 0, 3, 0, 0, 1},
          {7, 6, 5, 0, 2, 0, 0, 0, 6},
          {0, 7, 6, 0, 0, 0, 1, 2, 3},
          {0, 8, 7, 4, 1, 9, 4, 5, 6},
          {0, 9, 9, 0, 8, 0, 7, 8, 9}
  };

  @BeforeEach void setTestBoard() {
    testBoard = new SudokuBoard(sampleBoard);
  }

}