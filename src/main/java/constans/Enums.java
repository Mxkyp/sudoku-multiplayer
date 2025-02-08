package constans;

public final class Enums {
    private Enums() {
        throw new UnsupportedOperationException("This is a utility class "
                + "and cannot be instantiated");
    }

    public enum State {
        WRONG,
        UNKNOWN,
        CORRECT;
    }
}
