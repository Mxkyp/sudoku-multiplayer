package lines;

import board.SudokuCell;

import java.util.Arrays;

public class SudokuRow extends SudokuLine {
    public SudokuRow(final SudokuCell[][] board, final int rowY) {
        super.setMembers(Arrays.asList(board[rowY]));
    }

}
