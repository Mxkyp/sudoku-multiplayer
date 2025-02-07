package game.logic;


import java.util.Random;

public final class PlainSudokuGenerator implements SudokuGenerator {

    @Override
    public int[][] generateSudoku(final Difficulty difficulty) {
        return null;
    }

    private int getRandomNumber() {
        final int min = 0;
        final int maxExclusive = 9;
        Random random = new Random();
        return random.nextInt(min, maxExclusive);
    }
}
