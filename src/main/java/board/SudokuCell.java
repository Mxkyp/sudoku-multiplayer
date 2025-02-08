/***
 * class representing a singular Cell
 */
package board;

public final class SudokuCell {
    private int value;

    public SudokuCell(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }
}
