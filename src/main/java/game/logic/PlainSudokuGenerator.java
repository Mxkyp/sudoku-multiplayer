/***
 * Class which genenerates a sudoku with a unique solution
 */
package game.logic;

import board.SudokuCell;

import java.util.Random;

import static game.logic.SudokuDimensions.BOARD_SIZE;
import static game.logic.SudokuDimensions.MIN_INDEX;
import static game.logic.SudokuDimensions.SUB_MAX_INDEX;

public final class PlainSudokuGenerator implements SudokuGenerator {

    @Override
    public SudokuCell[][] generateSudoku(final Difficulty difficulty) {
        int[][] sudokuBoard = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.println(sudokuBoard[i][j]);
            }
        }
        return null;
    }

    private int seperateIntoSubGrids() {
        return 1;
    }

    //MAX_INDEX + 1 because its exclusive
    private int getRandomIndex() {
        Random random = new Random();
        return random.nextInt(MIN_INDEX, SUB_MAX_INDEX + 1);
    }
}
