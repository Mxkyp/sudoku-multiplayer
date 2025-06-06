package logic;

import board.SudokuBoard;
import org.junit.jupiter.api.Test;

public class PlainSudokuGeneratorTest {

    @Test void getRandomNumber(){
        PlainSudokuGenerator sudokuGenerator = new PlainSudokuGenerator();
        sudokuGenerator.generateSudoku();
    }

    @Test void testGenerateSudoku(){
        PlainSudokuGenerator sudokuGenerator = new PlainSudokuGenerator();
        SudokuBoard boardy = sudokuGenerator.generateSudoku();
        boardy.printBoard();
    }

}
