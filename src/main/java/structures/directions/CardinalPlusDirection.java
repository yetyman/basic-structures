package structures.directions;

public class CardinalPlusDirection implements EvenDirection<CardinalPlusDirection> {
    private static final CardinalPlusDirection[] _values = new CardinalPlusDirection[8];
    public static final CardinalPlusDirection North = new CardinalPlusDirection(0);
    public static final CardinalPlusDirection NorthEast = new CardinalPlusDirection(1);
    public static final CardinalPlusDirection East = new CardinalPlusDirection(2);
    public static final CardinalPlusDirection SouthEast = new CardinalPlusDirection(3);
    public static final CardinalPlusDirection South = new CardinalPlusDirection(4);
    public static final CardinalPlusDirection SouthWest = new CardinalPlusDirection(5);
    public static final CardinalPlusDirection West = new CardinalPlusDirection(6);
    public static final CardinalPlusDirection NorthWest = new CardinalPlusDirection(7);

    private final int _ordinal;
    CardinalPlusDirection(int ord){
        _ordinal = ord;
        _values[ord] = this;
    }

    @Override
    public int ordinal() {
        return _ordinal;
    }

    @Override
    public CardinalPlusDirection[] values() {
        return _values;
    }
}
