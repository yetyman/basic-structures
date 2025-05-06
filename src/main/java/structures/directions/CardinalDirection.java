package structures.directions;

public class CardinalDirection implements EvenDirection<CardinalDirection> {
    private static final CardinalDirection[] _values = new CardinalDirection[4];
    public static final CardinalDirection North = new CardinalDirection(0);
    public static final CardinalDirection East = new CardinalDirection(1);
    public static final CardinalDirection South = new CardinalDirection(2);
    public static final CardinalDirection West = new CardinalDirection(3);

    private final int _ordinal;
    CardinalDirection(int ord){
        _ordinal = ord;
        _values[ord] = this;
    }

    @Override
    public int ordinal() {
        return _ordinal;
    }

    @Override
    public CardinalDirection[] values() {
        return _values;
    }

}
