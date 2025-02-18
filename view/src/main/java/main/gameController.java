package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class gameController implements Initializable {
  private static final Logger logger = LoggerFactory.getLogger(gameController.class);

  private Stage stage;
  private Scene scene;
  private Parent root;
  @FXML
  public GridPane sudokuPane;

  public void switchToMainMenu(MouseEvent e) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void clickCell(MouseEvent e) throws IOException {
    logger.debug("Clicked Cell");
  }

  private void addItemsToGridPane() {
    for (int i = 0; i < 9; i++) {
      Text textNode = new Text(Integer.toString(i));
      textNode.setFont(Font.font("DejaVu Sans ExtraLight",24));
      textNode.setTextAlignment(TextAlignment.CENTER);
      for (int j = 0; j < 9; j++) {
        textNode.setId("r" + i + "c" + j);
        sudokuPane.add(textNode, i, j);
      }
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    addItemsToGridPane();
  }
}
