package board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SudokuCellTest {
    @Test
    void testSet(){
        final int testValue = 5;
        SudokuCell cell = new SudokuCell(testValue);

        assertEquals(testValue, cell.getValue());
    }

}