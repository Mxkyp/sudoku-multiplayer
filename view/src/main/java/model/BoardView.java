package model;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.SudokuGame;

public final class BoardView {

  final int BOARD_SIZE = 9; //TODO: THIS!
  @FXML
  private GridPane sudokuPane;
  private Text[][] textNode = new Text[BOARD_SIZE][BOARD_SIZE];

  public BoardView(final GridPane sudokuPane) {
    this.sudokuPane = sudokuPane;
  }

  public void updateEmptyCell(final CellView cell) {
    if (cell.getButtonPressed() == MouseButton.SECONDARY) {
      cell.setText("9");
    } else {
      cell.setText("1");
    }
  }

  public void updateCell(final CellView cell) {
    int number = Integer.parseInt(cell.getText());
    final MouseButton buttonPressed = cell.getButtonPressed();

    int newNumber = 0;
    final int rowNr = cell.getRowNr();
    final int colNr = cell.getColNr();

    if (buttonPressed == MouseButton.SECONDARY) {
      newNumber = (--number) % (BOARD_SIZE + 1);
    } else {
      newNumber = (++number) % (BOARD_SIZE + 1);
    }

    if (newNumber == 0) {
      textNode[rowNr][colNr].setText("");
    } else {
      textNode[rowNr][colNr].setText(Integer.toString(newNumber));
    }

  }

  public void addItemsToGridPane(final EventHandler<MouseEvent> eventHandler, final SudokuGame sudokuGame) {
    for (int r = 0; r < BOARD_SIZE; r++) {
      for (int c = 0; c < BOARD_SIZE; c++) {
        String initText = Integer.toString(sudokuGame.getCellValue(r, c));
        setTextNode(r, c, eventHandler, initText);
        sudokuPane.add(textNode[r][c], c, r);
      }
    }
  }

  private void setTextNode(final int row, final int col,  final EventHandler<MouseEvent> eventHandler, final String initText) {
    if (!initText.equals("0")) {
      setDefaultCell(row, col, initText);
    } else {
      setEmptyCell(row, col, eventHandler);
    }

    setCommonAttributes(row, col);
  }

  private void setDefaultCell(final int row, final int col, final String initValue) {
    textNode[row][col] = new Text(initValue);
    textNode[row][col].setFont(Font.font("DejaVu Sans"));
  }

  private void setEmptyCell(final int row, final int col, final EventHandler<MouseEvent> eventHandler) {
    textNode[row][col] = new Text();
    textNode[row][col].setOnMouseClicked(eventHandler);
    textNode[row][col].setFont(Font.font("DejaVu Sans ExtraLight"));
    textNode[row][col].setFill(Color.DARKSLATEGRAY);
  }

  /***
   * set text Node common visual attributes and give them unique id "rXcY"
   * @param row - X number used in nodes id
   * @param col - Y number used in nodes id
   */
  private void setCommonAttributes(final int row, final int col) {
    final double wWidth = 50.0;
    final int fontSize = 24;
    textNode[row][col].setFont(Font.font(textNode[row][col].getFont().getFamily(), fontSize));
    textNode[row][col].setWrappingWidth(wWidth);
    textNode[row][col].minHeight(wWidth);
    textNode[row][col].setTextAlignment(TextAlignment.CENTER);
    textNode[row][col].setId("r" + row + "c" + col);
  }

}
