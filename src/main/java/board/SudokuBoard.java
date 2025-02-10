/***
 * Class representing the 9x9 sudoku game board, which consists of:
 * 81 cells which references are also stored as:
 * 1.nine 3x3 'subBoards'
 * 2.nine rows
 * 3.nine columns
 */
package board;
import game.logic.Sudoku;
import game.logic.SudokuGenerator;
import lines.SudokuColumn;
import lines.SudokuRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static constans.Dimensions.BOARD_SIZE;
import static constans.Dimensions.MIN_INDEX;
import static constans.Dimensions.MAX_INDEX;

public final class SudokuBoard extends Board {
  private static final Logger logger =
          LoggerFactory.getLogger(SudokuBoard.class);

  private final SudokuCell[][] board = new SudokuCell[BOARD_SIZE][BOARD_SIZE];
  private final SudokuRow[] rows = new SudokuRow[BOARD_SIZE];
  private final SudokuColumn[] cols = new SudokuColumn[BOARD_SIZE];
  private final SudokuSubBoard[] subBoards = new SudokuSubBoard[BOARD_SIZE];

  public SudokuBoard(final int[][] dummyBoard) {
    initBoard(dummyBoard);

    for (int i = 0; i < BOARD_SIZE; i++) {
      rows[i] = new SudokuRow(board, i);
      cols[i] = new SudokuColumn(board, i);
      subBoards[i] = new SudokuSubBoard(board, i);
    }
  }

  public SudokuBoard(final SudokuGenerator sudokuGenerator) {
  }

  public void setCell(final int y, final int x, final int value) {
    this.board[y][x].setValue(value);
  }

  public Sudoku.State verify() {
    boolean unknownStatePresent = false;
    Sudoku.State resultState = Sudoku.State.UNKNOWN;

    for (int i = 0; i < BOARD_SIZE; i++) {

      Sudoku.State rowState = Sudoku.verify(rows[i].getMembers());
      Sudoku.State colState = Sudoku.verify(cols[i].getMembers());
      Sudoku.State subBoardState = Sudoku.verify(subBoards[i].getBoardAsList());

      if (rowState == Sudoku.State.WRONG
          || colState == Sudoku.State.WRONG
          || subBoardState == Sudoku.State.WRONG) {
        resultState = Sudoku.State.WRONG;
        break;
      } else if (rowState == Sudoku.State.UNKNOWN
                || colState == Sudoku.State.UNKNOWN
                || subBoardState == Sudoku.State.UNKNOWN) {
        unknownStatePresent = true;
      }
    }

    if (!unknownStatePresent && resultState != Sudoku.State.WRONG) {
      resultState = Sudoku.State.CORRECT;
    }
    return resultState;

  }

  public SudokuCell[][] getBoard() {
    return super.getBoard(BOARD_SIZE, this.board);
  }

  public int getCellValue(final int rowIndex, final int colIndex) {
    return super.getCellValue(rowIndex, colIndex,
            MAX_INDEX, MIN_INDEX, this.board);
  }

  public void printBoard() {
    super.printBoard(this.board);
  }

  private void initBoard(final int[][] values)            {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        this.board[i][j] = new SudokuCell(values[i][j]);
      }
    }
  }



}
