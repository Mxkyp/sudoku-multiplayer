/***
 * Class representing the 3x3 sudoku 'subBoard',
 * each sudoku gameBoard consists of 9 subboards
 */
package board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static constans.Dimensions.SUB_BOARD_SIZE;
import static constans.Dimensions.SUB_MAX_INDEX;
import static constans.Dimensions.SUB_MIN_INDEX;


/***
 * subBoards are indexed 0-9 from left-right top-bottom
 */
public final class SudokuSubBoard extends Board {
    private static final Logger logger =
            LoggerFactory.getLogger(SudokuSubBoard.class);
    private SudokuCell[][] board =
            new SudokuCell[SUB_BOARD_SIZE][SUB_BOARD_SIZE];

   public SudokuSubBoard(final SudokuCell[][] board, final int subBoardIndex) {
        Point startPoint = computeSubBoardCoords(subBoardIndex);
        for (int i = 0; i < SUB_BOARD_SIZE; i++) {
            for (int j = 0; j < SUB_BOARD_SIZE; j++) {
                this.board[i][j] = board[startPoint.y + i][startPoint.x + j];
            }
        }
   }

   /*
    compute the left upper corner coordinates for each subBoard
    */
   private Point computeSubBoardCoords(final int subBoardIndex) {
       final int[] subBoardLeftCornerRowNr = {0, 3, 6};
       final int[] subBoardLeftCornerColNr = {0, 3, 6};

       return new Point(subBoardLeftCornerColNr[subBoardIndex % SUB_BOARD_SIZE],
                       subBoardLeftCornerRowNr[subBoardIndex / SUB_BOARD_SIZE]);
   }


  public SudokuCell[][] getBoard() {
    return super.getBoard(SUB_BOARD_SIZE, this.board);
  }

  public List<SudokuCell> getBoardAsList() {
    List<SudokuCell> list = new ArrayList<>(SUB_BOARD_SIZE * SUB_BOARD_SIZE);
    for (SudokuCell[] row: board) {
      list.addAll(Arrays.asList(row));
    }
    return Collections.unmodifiableList(list);
  }

  public int getCellValue(final int rowIndex, final int colIndex) {
    return super.getCellValue(rowIndex, colIndex,
            SUB_MAX_INDEX, SUB_MIN_INDEX, this.board);
  }

  public void printBoard() {
    super.printBoard(this.board);
  }

}
