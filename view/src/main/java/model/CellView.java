package model;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public final class CellView {
  private final MouseButton buttonPressed;
  private final Text text;
  private final int rowNr;
  private final int colNr;

  public MouseButton getButtonPressed() {
    return buttonPressed;
  }

  public String getText() {
    return text.getText();
  }

  public void setText(final String str){
    text.setText(str);
  }

  public int getRowNr() {
    return rowNr;
  }

  public int getColNr() {
    return colNr;
  }

  public CellView(final Text text, final int rowNr, final int colNr, final MouseEvent buttonPressed) {
    this.text = text;
    this.rowNr = rowNr;
    this.colNr = colNr;
    this.buttonPressed = buttonPressed.getButton();
  }

}
