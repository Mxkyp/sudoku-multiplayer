package game.logic;

import org.junit.jupiter.api.Test;

public class PlainSudokuGeneratorTest {

    @Test void getRandomNumber(){
        PlainSudokuGenerator sudokuGenerator = new PlainSudokuGenerator();
        sudokuGenerator.generateSudoku(SudokuGenerator.Difficulty.EASY);
    }

}
