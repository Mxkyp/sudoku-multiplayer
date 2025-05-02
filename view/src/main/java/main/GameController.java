package main;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.SudokuGame;
import model.BoardView;
import model.CellView;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class GameController implements Initializable {
  private static final Logger logger
          = LoggerFactory.getLogger(GameController.class);

  private Stage stage;
  private Scene scene;
  @FXML
  private GridPane sudokuPane;
  private BoardView boardView;
  private SudokuGame game = new SudokuGame(new logic.PlainSudokuGenerator());

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    boardView = new BoardView(sudokuPane);
    boardView.addItemsToGridPane(this::clickCell, game.getPuzzle());
  }

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
    Text text = (Text) e.getTarget();
    animateClick(text);
    String id = text.getId();

    int[] rowColNr = parseForRowColNr(id);
    int rowNr = rowColNr[0];
    int colNr = rowColNr[1];

    if (text.getText().isEmpty()) {
      boardView.updateEmptyCell(new CellView(text, rowNr, colNr, e));
    } else {
      boardView.updateCell(new CellView(text, rowNr, colNr, e));
    }

    logger.debug("Clicked Cell {} {} {}", colNr, rowNr, e.getButton());
  }

  private int[] parseForRowColNr(final String id) {
    /*
    each text field has an id "rXcY"
    where X is its rowNr and Y colNr
    so the index of rowNr in a string is 1 (X)
    and index of colNr in a string is 3 (Y)
     */
    final int colIndex = 3;
    final int rowIndex = 1;

    int colNr = id.charAt(colIndex) - '0'; // good old C tricks
    int rowNr = id.charAt(rowIndex) - '0';

    return new int[]{rowNr, colNr};
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


}
