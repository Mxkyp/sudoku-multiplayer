package board;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class Board {
  private static final Logger logger =
          LoggerFactory.getLogger(Board.class);

    SudokuCell[][] get(final int size,
                       final SudokuCell[][] originalBoard) {

      SudokuCell[][] boardCopy = new SudokuCell[size][size];

      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
               boardCopy[i][j] = new SudokuCell(originalBoard[i][j].getValue());
           }
       }

      return boardCopy;
  }

  int getCellValue(final int rowIndex, final int colIndex,
                           final int maxIndex, final int minIndex,
                           final SudokuCell[][] board) {
    int result = -1;

    if (rowIndex >= minIndex && colIndex >= minIndex
            && rowIndex <= maxIndex && colIndex <= maxIndex) {
      result = board[rowIndex][colIndex].getValue();
    }

    return result;
  }

  void printBoard(final SudokuCell[][] board) {
    final int initCapacity = 100;
    StringBuffer buffer = new StringBuffer(initCapacity);

    buffer.append("\n");
    for (SudokuCell[] row: board) {
      for (SudokuCell cell: row) {
        buffer.append(cell.getValue());
        buffer.append(" ");
      }
      buffer.append("\n");
    }

    logger.info(buffer.toString());
  }
}
