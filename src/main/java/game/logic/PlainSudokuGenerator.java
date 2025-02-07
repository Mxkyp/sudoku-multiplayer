package game.logic;


import java.util.Random;

public final class PlainSudokuGenerator
        extends SudokuDimensions implements SudokuGenerator {

    @Override
    public int[][] generateSudoku(final Difficulty difficulty) {
        int[][] sudokuBoard = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.println(sudokuBoard[i][j]);
            }
        }
        return null;
    }

    private int seperateIntoSubGrids()

    private int getRandomIndex() {
        Random random = new Random();
        return random.nextInt(MIN_INDEX, MAX_INDEX + 1); //+1 because its exclusive
    }
}
