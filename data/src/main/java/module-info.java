module data {
  requires java.desktop;
  requires org.slf4j;

  exports constans to view;
  exports board;
  exports game.logic;
}