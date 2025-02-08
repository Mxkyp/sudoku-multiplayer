package lines;

import board.SudokuBoard;
import board.SudokuCell;
import constans.Enums.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static constans.Dimensions.BOARD_SIZE;

class SudokuRowTest {
    SudokuBoard testBoard;
    final static int[][] sampleBoard = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {1, 2, 3, 3, 4, 5, 6, 7, 8},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    @BeforeEach
    void setTestBoard() {
        testBoard = new SudokuBoard(sampleBoard);
    }

    @Test void testInit() {
        SudokuCell[][] sampleBoard = testBoard.getBoard();
        SudokuRow row = new SudokuRow(sampleBoard, 0);

        sampleBoard[0][8].setValue(11);

        List<SudokuCell> list = row.getMembers();
        for (int i = 0; i < BOARD_SIZE; i++){
            assertEquals(sampleBoard[0][i].getValue(), list.get(i).getValue());
        }

    }

    @Test void testGetState() {
        SudokuCell[][] sampleBoard = testBoard.getBoard();
        SudokuRow row = new SudokuRow(sampleBoard, 0);
        assertEquals(State.UNKNOWN, row.getState());

        SudokuRow row2 = new SudokuRow(sampleBoard, 1);
        assertEquals(State.CORRECT, row2.getState());

        SudokuRow row3 = new SudokuRow(sampleBoard, 2);
        assertEquals(State.WRONG, row3.getState());
    }
}