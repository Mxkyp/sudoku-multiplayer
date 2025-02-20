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
  private Boolean exit = null;
  @FXML
  Button yesBt;
  @FXML
  Button noBt;

  public void loadWindow() throws IOException {
    root = FXMLLoader.load(getClass().getResource("/fxml/gameExitPopUp.fxml"));
    stage = new Stage();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
    CountDownLatch countDownLatch = new CountDownLatch(1);

    yesBt.setOnMousePressed(event ->  exit = true);
    noBt.setOnMousePressed(event -> exit = false);
  }

  public Boolean confirm() {
    return exit;
  }


}
