package board;

public interface Board {

  void setCell(int y, int x, int value);

  Cell[][] getBoard();

  int getCellValue(int rowIndex, int colIndex);

  void printBoard();
}
