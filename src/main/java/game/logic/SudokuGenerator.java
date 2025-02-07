package game.logic;


public interface SudokuGenerator {
    enum Difficulty {
        EASY,
        MEDIUM,
        HARD;
    }

    int[][] generateSudoku(Difficulty difficulty);
}
