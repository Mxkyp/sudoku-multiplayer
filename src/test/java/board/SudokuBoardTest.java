package board;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void testInit(){
        final int[][] sampleBoard = {
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


        SudokuBoard testBoard = new SudokuBoard(sampleBoard);

        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
               assertEquals(sampleBoard[i][j], testBoard.getCellValue(i,j));
            }
        }

    }

    @Test
    void testPrintBoard(){
        final int[][] sampleBoard = {
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