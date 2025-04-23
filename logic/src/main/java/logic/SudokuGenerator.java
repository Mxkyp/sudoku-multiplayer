package logic;


import board.SudokuBoard;

public interface SudokuGenerator {
    enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    SudokuBoard generateSudoku(Difficulty difficulty);
}
