package board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static game.logic.SudokuDimensions.BOARD_SIZE;
import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
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

    @BeforeEach void setTestBoard(){
        testBoard = new SudokuBoard(sampleBoard);
    }

    @Test void testInit(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
               assertEquals(sampleBoard[i][j], testBoard.getCellValue(i,j));
            }
        }

    }

    //get shall return a hardcopy
    @Test void testGetBoard(){
        SudokuCell[][] boardCopy = testBoard.getBoard();

        boardCopy[0][0].setValue(10);

        assertNotEquals(testBoard.getCellValue(0, 0), boardCopy[0][0].getValue());
    }

    //trying to get a value outside the board index_range returns -1
    @Test void testGetCellValue(){
        assertEquals(-1, testBoard.getCellValue(-1,-1));
        assertEquals(-1, testBoard.getCellValue(10,10));
    }

    @Test void testPrintBoard(){
        //create an expectedRepresenation using stringBuilder
        final StringBuilder buffer = new StringBuilder();
        buffer.append("\n");
        for (int[] row: sampleBoard) {
            for (int cell : row) {
                buffer.append(cell);
                buffer.append(" ");
            }
            buffer.append("\n");
        }
        final String expectedOutput = buffer.toString();

        /*
        save the result of board.printBoard() by setting a custom
        output stream
        */
        SudokuBoard testBoard = new SudokuBoard(sampleBoard);

        final OutputStream os = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(os);
        System.setOut(stream);

        testBoard.printBoard();
        String actualOutput = os.toString();
        assertTrue(actualOutput.contains(expectedOutput));
    }

}