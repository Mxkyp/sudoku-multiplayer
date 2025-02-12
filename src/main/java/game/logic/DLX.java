package game.logic;

import board.SudokuBoard;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

import static constans.Dimensions.BOARD_SIZE;

public class DLX {
  private final Map<Piece, Constraint[]> pieceToConstrains
          = new HashMap<>(1000);
  private final Map<Constraint[], Piece> constrainsToPiece
          = new HashMap<>(1000);

  private final class Piece {
    private final int x;
    private final int y;
    private final int digit;


    private Piece(final int x, final int y, final int digit) {
      this.x = x;
      this.y = y;
      this.digit = digit;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public int getDigit() {
      return digit;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Piece piece = (Piece) o;

      return new EqualsBuilder().append(x, piece.x).append(y, piece.y)
                 .append(digit, piece.digit).isEquals();
    }

    @Override
    public int hashCode() {
      final int initOdd = 17;
      final int multiplier = 37;
      return new HashCodeBuilder(initOdd, multiplier).append(x).append(y).
                  append(digit).toHashCode();
    }
  }

  private abstract class Constraint {
     private final String name;
     private final Integer digit;
     private final Integer row;
     private final Integer column;

    Constraint(final String name, final Integer digit,
               final Integer row, final Integer column) {
      this.name = name;
      this.digit = digit;
      this.row = row;
      this.column = column;
    }

    public String getName() {
      return name;
    }

    public Integer getDigit() {
      return digit;
    }

    public Integer getRow() {
      return row;
    }

    public Integer getColumn() {
      return column;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Constraint that = (Constraint) o;

      return new EqualsBuilder().append(name, that.name).
                  append(digit, that.digit).
                  append(row, that.row).append(column, that.column).isEquals();
    }

    @Override
    public int hashCode() {
      final int initOdd = 37;
      final int multiplier = 31;
      return new HashCodeBuilder(initOdd, multiplier).append(name).
                  append(digit).append(row).append(column).toHashCode();
    }
  }

  private final class BoardConstraint extends Constraint {
    BoardConstraint(final Integer row, final Integer column) {
      super("board", null, row, column);
    }

  }

  private final class RowConstraint extends Constraint {
    RowConstraint(final int row, final int digit) {
      super("row", digit, row, null);
    }
  }

  private final class ColumnConstraint extends Constraint {
    ColumnConstraint(final int column, final int digit) {
      super("column", digit, null, column);
    }
  }

  private final class SubBoardConstraint extends Constraint {
    SubBoardConstraint(final String name, final Integer digit,
               final Integer row, final Integer column) {
      super("subBoard", digit, row, column);
    }
  }

  public SudokuBoard createSudoku(){

  }

  private void getConstrains() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        for (int digit = 1; digit <= BOARD_SIZE; digit++) {

        }
      }
    }
  }

}
