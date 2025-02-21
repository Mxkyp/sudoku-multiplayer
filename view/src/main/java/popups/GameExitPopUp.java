package popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public final class GameExitPopUp {
  private Stage stage;
  private Scene scene;
  private Parent root;
  private boolean exit;
  @FXML
  Button yesBt;
  @FXML
  Button noBt;

  public boolean loadWindow() throws IOException, InterruptedException {
    root = FXMLLoader.load(getClass().getResource("/fxml/gameExitPopUp.fxml"));
    stage = new Stage();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
    yesBt.setOnMousePressed(event ->  exit = true);
    noBt.setOnMousePressed(event -> exit = false);

    while(!exit){
      stage.wait(500);
    }

    stage.close();
    return exit;
  }

  public Boolean confirm() {
    return exit;
  }


}
