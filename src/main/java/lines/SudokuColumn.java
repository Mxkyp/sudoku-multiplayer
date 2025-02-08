package lines;

import board.SudokuCell;

import java.util.Arrays;

public class SudokuColumn extends SudokuLine {

    public SudokuColumn(final SudokuCell[][] board, final int columnX) {
        int rows = board.length;
        SudokuCell[] col = new SudokuCell[rows];

        for (int i = 0; i < rows; i++) {
            col[i] = board[i][columnX];
        }

        super.setMembers(Arrays.asList(col));
    }

    public returnColumnState()

}
