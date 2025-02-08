package lines;

import board.SudokuCell;
import constans.Enums;

import java.util.Collections;
import java.util.List;
import constans.Enums.State;

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


    /***
     * returns whether the line is a valid
     * (has only one of each digit from 1-9)
     */
    public State getState() {
        final int nrOfPossibleValues = 10; //0 == unfilled, 1 to 9
        int[] occurenceCount = new int[nrOfPossibleValues];

        for (SudokuCell cell: members) {
            occurenceCount[cell.getValue()]++;
        }

        int maxOccurence = 0;
        int minOccurence = Integer.MAX_VALUE;

        // starts from 1 because zero's
        // (which occurence number is at index zero)
        // represent empty cells
        for (int i = 1; i < nrOfPossibleValues; i++) {
            if (occurenceCount[i] > maxOccurence) {
                maxOccurence = occurenceCount[i];
            } else if (occurenceCount[i] < minOccurence) {
                minOccurence = occurenceCount[i];
            }
        }

        Enums.State computedState;
        if (maxOccurence > 1) {
            computedState = Enums.State.WRONG;
        } else if (maxOccurence != 1 || minOccurence != 1) {
            computedState = Enums.State.UNKNOWN;
        } else {
            computedState = Enums.State.CORRECT;
        }

        return computedState;
    }
}
