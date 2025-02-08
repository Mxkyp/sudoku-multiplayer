package board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        SudokuSubBoard sudokuSubBoard = new SudokuSubBoard(0, boardToReference);

        boardToReference[0][0].setValue(9);

        assertEquals(sudokuSubBoard.);

    }

}