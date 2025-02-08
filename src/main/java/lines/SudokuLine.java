package lines;

import board.SudokuCell;

import java.util.Collections;
import java.util.List;

public abstract class SudokuLine {
    private List<SudokuCell> members;

    /**
     * @return an unmodifiable list of the line's members
     */
    public List<SudokuCell> getMembers() {
        return Collections.unmodifiableList(members);
    }

    /***
     * @param members: a list of references to cell in that line
     */
    public void setMembers(final List<SudokuCell> members) {
        this.members = members;
    }


}
