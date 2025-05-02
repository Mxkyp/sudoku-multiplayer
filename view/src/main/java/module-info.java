module view {
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.controls;
  requires logic;
  requires org.slf4j;

  exports main;
  opens main;
  exports model;
  opens model;
}