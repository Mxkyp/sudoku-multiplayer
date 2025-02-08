/***
 * Class representing the 3x3 sudoku 'subBoard',
 * each sudoku gameBoard consists of 9 subboards
 */
package board;


import java.awt.Point;

import static game.logic.SudokuDimensions.SUB_BOARD_SIZE;

/***
 * subBoards are indexed 0-9 from left-right top-bottom
 */
public final class SudokuSubBoard  {
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
}
