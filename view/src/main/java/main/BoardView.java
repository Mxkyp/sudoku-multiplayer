package main;

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

public class BoardView {

  final int BOARD_SIZE = 9; //TODO: THIS!
  /*
    sudokuBoard and individual cell
    text fields gui representation
   */
  @FXML
  private GridPane sudokuPane;
  public Text[][] textNode = new Text[BOARD_SIZE][BOARD_SIZE];
  private final SudokuGame sudokuGame = new SudokuGame(new logic.PlainSudokuGenerator());

  BoardView(final GridPane sudokuPane) {
    this.sudokuPane = sudokuPane;
  }

  /***
   * method that sets a value to a empty cell based on
   * which mouse button was pressed<br>
   * left mouse click - 1<br>
   * right moust click - 9<br>
   * @param event - mouse click
   * @param cell - the cell clicked
   * @param rowNr - its rowNr
   * @param colNr - its colNr
   */
  public void updateEmptyCell(final MouseEvent event, final Text cell, final int rowNr, final int colNr) {

    final MouseButton buttonPressed = event.getButton();
    if (buttonPressed == MouseButton.SECONDARY) {
      cell.setText("9");
      sudokuGame.setCell(rowNr, colNr, 9);
    } else {
      cell.setText("1");
      sudokuGame.setCell(rowNr, colNr, 1);
    }
  }

  /***
   method that updates a cell number based on
   * which mouse button was pressed<br>
   * left mouse click - increment<br>
   * right moust click - decrement
   * @param event - mouse click
   * @param cell - the cell clicked
   * @param rowNr - its rowNr
   * @param colNr - its colNr
   */
  public void updateCell(final MouseEvent event, final Text cell, final int rowNr, final int colNr) {
    int number = Integer.parseInt(cell.getText());
    final MouseButton buttonPressed = event.getButton();

    int newNumber = 0;

    if (buttonPressed == MouseButton.SECONDARY) {
      newNumber = (--number) % (BOARD_SIZE + 1);
    } else {
      newNumber = (++number) % (BOARD_SIZE + 1);
    }

    sudokuGame.setCell(rowNr, colNr, newNumber);

    if (newNumber == 0) {
      textNode[rowNr][colNr].setText("");
    } else {
      textNode[rowNr][colNr].setText(Integer.toString(newNumber));
    }

  }

  public void addItemsToGridPane(final EventHandler<MouseEvent> eventHandler) {
    for (int r = 0; r < BOARD_SIZE; r++) {
      for (int c = 0; c < BOARD_SIZE; c++) {
        setTextNode(r, c, eventHandler);
        sudokuPane.add(textNode[r][c], c, r);
      }
    }
  }

  /***
   * set text Node to either<br>1.empty sudoku cell<br>2.default unmodifiable filled one
   * @param row
   * @param col
   */
  private void setTextNode(final int row, final int col,  final EventHandler<MouseEvent> eventHandler) {
    final String initValue = Integer.toString(sudokuGame.getCellValue(row, col));

    if (!initValue.equals("0")) {
      setDefaultCell(row, col, initValue);
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
