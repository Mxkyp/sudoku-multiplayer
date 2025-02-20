package main;

import board.SudokuBoard;
import game.logic.PlainSudokuGenerator;
import game.logic.SudokuGenerator;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
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

  /*

   */
  private SudokuBoard sudokuBoard = new PlainSudokuGenerator().generateSudoku(SudokuGenerator.Difficulty.EASY);

  public void switchToMainMenu(final MouseEvent e) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void clickCell(final MouseEvent e) {
    final int colIndex = 3;
    final int rowIndex = 1;

    Text temp = (Text) e.getTarget();
    String id = temp.getId();
    int colNr = id.charAt(colIndex) - '0'; // good old C tricks
    int rowNr = id.charAt(rowIndex) - '0';

    if (temp.getText().isEmpty()) {
      temp.setText("1");
      sudokuBoard.setCell(rowNr, colNr, 1);
    } else {
      int number = Integer.parseInt(temp.getText());
      final int newNumber = (++number) % (BOARD_SIZE + 1);

      sudokuBoard.setCell(rowNr, colNr, newNumber);
      textNode[rowNr][colNr].setText(Integer.toString(newNumber));
    }
    logger.debug("Clicked Cell {} {}", colNr, rowNr);
  }

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


  private void setTextNode(final int row, final int col) {
    final double wWidth = 50.0;
    final int fontSize = 24;
    final String initValue = Integer.toString(sudokuBoard.getCell(row, col));

    if (!initValue.equals("0")) {
    textNode[row][col] = new Text(initValue);
    } else {
      textNode[row][col] = new Text();
    }

    textNode[row][col].setWrappingWidth(wWidth);
    textNode[row][col].minHeight(wWidth);
    textNode[row][col].setFont(Font.font("DejaVu Sans ExtraLight", fontSize));
    textNode[row][col].setTextAlignment(TextAlignment.CENTER);
    textNode[row][col].setId("r" + row + "c" + col);
    textNode[row][col].setOnMouseClicked(this::clickCell);
  }

}
