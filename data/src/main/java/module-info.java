module data {
  requires java.desktop;
  requires org.slf4j;

  exports constans to view, logic;
  exports board to logic;
}