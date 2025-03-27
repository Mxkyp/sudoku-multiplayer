module view {
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.controls;
  requires data;
  requires org.slf4j;

  exports main;
  opens main;
}