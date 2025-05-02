package board;

public interface Board {

  void setCell(int y, int x, int value);

  int[][] getBoard();

  int getCellValue(int rowIndex, int colIndex);

  void printBoard();
}
