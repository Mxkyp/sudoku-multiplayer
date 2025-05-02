module view {
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.controls;
  requires logic;
  requires org.slf4j;
  requires org.apache.commons.lang3;

  exports main;
  opens main;
  exports model;
  opens model;
}