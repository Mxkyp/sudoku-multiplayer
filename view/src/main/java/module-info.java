module view {
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.controls;
  requires core;
  requires org.slf4j;

  exports main;
  opens main;
}