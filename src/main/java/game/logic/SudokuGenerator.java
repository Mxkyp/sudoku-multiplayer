package game.logic;


import board.SudokuCell;

public interface SudokuGenerator {
    enum Difficulty {
        EASY,
        MEDIUM,
        HARD;
    }

    SudokuCell[][] generateSudoku(Difficulty difficulty);
}
