package lines;

import board.SudokuBoard;
import board.SudokuCell;
import game.logic.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static constans.Dimensions.BOARD_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SudokuColumnTest {
    SudokuBoard testBoard;
    final static int[][] sampleBoard = {
            {5, 1, 1, 0, 7, 0, 0, 0, 0},
            {6, 2, 2, 1, 9, 5, 0, 0, 0},
            {0, 3, 2, 0, 0, 0, 0, 6, 0},
            {8, 4, 3, 0, 6, 0, 0, 0, 3},
            {4, 5, 4, 8, 0, 3, 0, 0, 1},
            {7, 6, 5, 0, 2, 0, 0, 0, 6},
            {0, 7, 6, 0, 0, 0, 2, 8, 0},
            {0, 8, 7, 4, 1, 9, 0, 0, 5},
            {0, 9, 9, 0, 8, 0, 0, 7, 9}
    };

    @BeforeEach void setTestBoard() {
        testBoard = new SudokuBoard(sampleBoard);
    }

    @Test void testInit() {
        SudokuCell[][] sampleBoard = testBoard.getBoard();
        SudokuColumn col = new SudokuColumn(sampleBoard, 0);

        sampleBoard[8][0].setValue(11);

        List<SudokuCell> list = col.getMembers();
        for (int i = 0; i < BOARD_SIZE; i++){
            assertEquals(sampleBoard[i][0].getValue(), list.get(i).getValue());
        }

    }

    @Test void testGetState() {
        SudokuCell[][] sampleBoard = testBoard.getBoard();
        SudokuColumn col = new SudokuColumn(sampleBoard, 0);
        assertEquals(Sudoku.State.UNKNOWN, Sudoku.verify(col));

        SudokuColumn col2 = new SudokuColumn(sampleBoard, 1);
        assertEquals(Sudoku.State.CORRECT, Sudoku.verify(col2));

        SudokuColumn col3 = new SudokuColumn(sampleBoard, 2);
        assertEquals(Sudoku.State.WRONG, Sudoku.verify(col3));
    }

}