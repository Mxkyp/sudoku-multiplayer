package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public final class Main extends Application {
  private int W_HEIGHT = 600;
  private int W_WIDTH = 720;

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
    Scene scene = new Scene(root, Color.BISQUE);
    Image icon = new Image(getClass().getResource("/images/sudoku.png").toExternalForm());
    stage.getIcons().add(icon);

    stage.setTitle(" Sudoku Multiplayer ");
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
  }
}
