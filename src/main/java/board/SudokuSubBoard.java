/***
 * Class representing the 3x3 sudoku 'subBoard',
 * each sudoku gameBoard consists of 9 subboards
 */
package board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;

import static game.logic.SudokuDimensions.SUB_MAX_INDEX;
import static game.logic.SudokuDimensions.SUB_MIN_INDEX;
import static game.logic.SudokuDimensions.SUB_BOARD_SIZE;


/***
 * subBoards are indexed 0-9 from left-right top-bottom
 */
public final class SudokuSubBoard  {
    private static final Logger logger =
            LoggerFactory.getLogger(SudokuSubBoard.class);
    private SudokuCell[][] board =
            new SudokuCell[SUB_BOARD_SIZE][SUB_BOARD_SIZE];

   public SudokuSubBoard(final int subBoardIndex, final SudokuCell[][] board) {
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

    public int getCellValue(final int rowIndex, final int colIndex) {
        int result = -1;

        if (rowIndex >= SUB_MIN_INDEX && colIndex >= SUB_MIN_INDEX
                && rowIndex <= SUB_MAX_INDEX && colIndex <= SUB_MAX_INDEX) {
            result = this.board[rowIndex][colIndex].getValue();
        }

        return result;
    }

}
