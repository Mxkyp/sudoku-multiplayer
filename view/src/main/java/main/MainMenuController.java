package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class MainMenuController {
  private static final Logger logger = LoggerFactory.getLogger(MainMenuController.class);
  private Stage stage;
  private Scene scene;
  private Parent root;

  public void playSinglePlayer(ActionEvent e) throws IOException {
    logger.debug("Pressed the play button");
    switchToGameScene(e);
  }

  public void playMultiplayer(ActionEvent e) throws IOException {
    logger.debug("Pressed the multiplayer button");
    switchToMultiplayerMenu(e);
  }


  public void switchToGameScene(ActionEvent e) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));
    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToMultiplayerMenu(ActionEvent e) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/multiplayer.fxml"));
    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
