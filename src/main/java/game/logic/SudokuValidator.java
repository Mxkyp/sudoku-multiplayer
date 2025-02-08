package game.logic;

import board.SudokuCell;
import constans.Enums;

public final class SudokuValidator {
    private SudokuValidator() {
        throw new UnsupportedOperationException("This is a utility class "
                + "and cannot be instantiated");
    }


    public static Enums.State getState() {
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
