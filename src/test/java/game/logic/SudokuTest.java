package game.logic;

import board.SudokuBoard;
import board.SudokuCell;
import board.SudokuSubBoard;
import lines.SudokuColumn;
import lines.SudokuRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test void testVerifyColumn() {
    SudokuCell[][] sampleBoard = testBoard.getBoard();
    SudokuColumn col = new SudokuColumn(sampleBoard, 0);
    assertEquals(Sudoku.State.UNKNOWN, Sudoku.verify(col));

    SudokuColumn col2 = new SudokuColumn(sampleBoard, 1);
    assertEquals(Sudoku.State.CORRECT, Sudoku.verify(col2));

    SudokuColumn col3 = new SudokuColumn(sampleBoard, 2);
    assertEquals(Sudoku.State.WRONG, Sudoku.verify(col3));
  }


  @Test void testVerifyRow() {
    SudokuCell[][] sampleBoard = testBoard.getBoard();
    SudokuRow row = new SudokuRow(sampleBoard, 6);
    assertEquals(Sudoku.State.UNKNOWN, Sudoku.verify(row));

    SudokuRow row2 = new SudokuRow(sampleBoard, 2);
    assertEquals(Sudoku.State.CORRECT, Sudoku.verify(row2));

    SudokuRow row3 = new SudokuRow(sampleBoard, 1);
    assertEquals(Sudoku.State.WRONG, Sudoku.verify(row3));
  }

  @Test void testVerifySubBoard(){
    SudokuCell[][] sampleBoard = testBoard.getBoard();

    SudokuSubBoard subBoard = new SudokuSubBoard(5,sampleBoard);
    assertEquals(Sudoku.State.UNKNOWN, Sudoku.verify(subBoard));

    SudokuSubBoard subBoard2 = new SudokuSubBoard(8,sampleBoard);
    assertEquals(Sudoku.State.CORRECT, Sudoku.verify(subBoard2));

    SudokuSubBoard subBoard3 = new SudokuSubBoard(0,sampleBoard);
    assertEquals(Sudoku.State.WRONG, Sudoku.verify(subBoard3));
  }
}