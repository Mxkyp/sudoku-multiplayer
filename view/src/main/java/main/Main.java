package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Group root = new Group();
    Scene scene = new Scene(root, Color.BISQUE);

    Image icon = new Image(getClass().getResource("/images/sudoku.png").toExternalForm());
    stage.getIcons().add(icon);

    stage.setTitle(" Sudoku Multiplayer");
    stage.setScene(scene);
    stage.show();
  }
}
