package main;

import board.SudokuBoard;
import game.logic.PlainSudokuGenerator;
import game.logic.SudokuGenerator;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static constans.Dimensions.BOARD_SIZE;

public final class GameController implements Initializable {
  private static final Logger logger
          = LoggerFactory.getLogger(GameController.class);

  private Stage stage;
  private Scene scene;
  private Parent root;

  /*
    sudokuBoard and individual cell
    text fields gui representation
   */
  @FXML
  public GridPane sudokuPane;
  public Text[][] textNode = new Text[BOARD_SIZE][BOARD_SIZE];

  private final SudokuBoard sudokuBoard = new PlainSudokuGenerator().generateSudoku(SudokuGenerator.Difficulty.EASY);


  /***
   * Show a popup to alert a user before deleting game and going back to main menu
   * @param e - clicking the go back button in game
   * @throws IOException couldn't load the fxml
   */
  public void switchToMainMenu(final MouseEvent e) throws IOException {
    Alert alert = getLeaveAlert();

    if (alert.showAndWait().get() == ButtonType.OK) {
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
      stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
  }

  private Alert getLeaveAlert() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    alert.setTitle("leave to main menu");
    alert.setHeaderText("Discard all your progress and return to main menu");
    alert.setContentText("Are you sure?");

    return alert;
  }

  /***
   * find the clicked sudokuCell, and update its value
   * @param e mouse click
   */
  private void clickCell(final MouseEvent e) {
    /*
    each text field has an id "rXcY"
    where X is its rowNr and Y colNr
    so the index of rowNr in a string is 1 (X)
    and index of colNr in a string is 3 (Y)
     */
    final int colIndex = 3;
    final int rowIndex = 1;

    Text text = (Text) e.getTarget();
    animateClick(text);

    String id = text.getId();
    int colNr = id.charAt(colIndex) - '0'; // good old C tricks
    int rowNr = id.charAt(rowIndex) - '0';

    if (text.getText().isEmpty()) {
      updateEmptyCell(e, text, rowNr, colNr);
    } else {
      updateCell(e, text, rowNr, colNr);
    }

    logger.debug("Clicked Cell {} {} {}", colNr, rowNr, e.getButton());
  }

  /***
   * add a slight highlight animation after clicking a cell
   * @param text - a reference to the text to be animated
   */
  private void animateClick(final Text text) {
    FadeTransition translate = new FadeTransition(Duration.millis(400), text);
    translate.setToValue(0.7);

    FadeTransition translateBack = new FadeTransition(Duration.millis(400), text);
    translateBack.setToValue(1);

    translate.setOnFinished(event -> translateBack.play());
    translate.play();
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
  private void updateEmptyCell(final MouseEvent event, final Text cell, final int rowNr, final int colNr) {

    final MouseButton buttonPressed = event.getButton();
    if (buttonPressed == MouseButton.SECONDARY) {
      cell.setText("9");
      sudokuBoard.setCell(rowNr, colNr, 9);
    } else {
      cell.setText("1");
      sudokuBoard.setCell(rowNr, colNr, 1);
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
  private void updateCell(final MouseEvent event, final Text cell, final int rowNr, final int colNr) {
    int number = Integer.parseInt(cell.getText());
    final MouseButton buttonPressed = event.getButton();

    int newNumber = 0;

    if (buttonPressed == MouseButton.SECONDARY) {
      newNumber = (--number) % (BOARD_SIZE + 1);
    } else {
      newNumber = (++number) % (BOARD_SIZE + 1);
    }

    sudokuBoard.setCell(rowNr, colNr, newNumber);

    if (newNumber == 0) {
      textNode[rowNr][colNr].setText("");
    } else {
      textNode[rowNr][colNr].setText(Integer.toString(newNumber));
    }

  }

  /***
   *  initialize and connect the sudokuBoard with its representation
   * @param location
   * The location used to resolve relative paths for the root object, or
   * {@code null} if the location is not known.
   *
   * @param resources
   * The resources used to localize the root object, or {@code null} if
   * the root object was not localized.
   */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    addItemsToGridPane();
  }

  private void addItemsToGridPane() {
    for (int r = 0; r < BOARD_SIZE; r++) {
      for (int c = 0; c < BOARD_SIZE; c++) {
        setTextNode(r, c);
        sudokuPane.add(textNode[r][c], r, c);
      }
    }
  }

  /***
   * set text Node to either<br>1.empty sudoku cell<br>2.default unmodifiable filled one
   * @param row
   * @param col
   */
  private void setTextNode(final int row, final int col) {
    final String initValue = Integer.toString(sudokuBoard.getCellValue(row, col));

    if (!initValue.equals("0")) {
      setDefaultCell(row, col, initValue);
    } else {
      setEmptyCell(row, col);
    }

    setCommonAttributes(row, col);
  }

  private void setDefaultCell(final int row, final int col, final String initValue) {
    textNode[row][col] = new Text(initValue);
    textNode[row][col].setFont(Font.font("DejaVu Sans"));
  }

  private void setEmptyCell(final int row, final int col) {
    textNode[row][col] = new Text();
    textNode[row][col].setOnMouseClicked(this::clickCell);
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
