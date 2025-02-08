package board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static game.logic.SudokuDimensions.SUB_BOARD_SIZE;
import static org.junit.jupiter.api.Assertions.*;

class SudokuSubBoardTest {
    SudokuBoard testBoard;
    final static int[][] sampleBoard = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    @BeforeEach void setTestBoard() {
        testBoard = new SudokuBoard(sampleBoard);
    }

    @Test void testInit(){
        SudokuCell[][] boardToReference = testBoard.getBoard();

        //subboard should hold references, all changes in the original board should be present
        SudokuSubBoard sudokuSubBoard = new SudokuSubBoard(0, boardToReference);

        boardToReference[0][0].setValue(9);
        for(int i = 0; i < SUB_BOARD_SIZE; i++) {
            for (int j = 0; j < SUB_BOARD_SIZE; j++) {
                assertEquals(boardToReference[i][j].getValue(), sudokuSubBoard.getCellValue(i, j));
            }
        }

        SudokuSubBoard sudokuSubBoard2 = new SudokuSubBoard(1, boardToReference);
        boardToReference[0][3].setValue(10);
        for(int i = 0; i < SUB_BOARD_SIZE; i++) {
            for (int j = 0; j < SUB_BOARD_SIZE; j++) {
                assertEquals(boardToReference[i][3 + j].getValue(), sudokuSubBoard2.getCellValue(i, j));
            }
        }
    }


}