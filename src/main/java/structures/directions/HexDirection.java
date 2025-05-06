package structures.directions;

public class HexDirection implements EvenDirection<HexDirection> {
    private static final HexDirection[] _values = new HexDirection[6];
    public static final HexDirection North = new HexDirection(0);
    public static final HexDirection NorthEast = new HexDirection(1);
    public static final HexDirection SouthEast = new HexDirection(2);
    public static final HexDirection South = new HexDirection(3);
    public static final HexDirection SouthWest = new HexDirection(4);
    public static final HexDirection NorthWest = new HexDirection(5);

    private final int _ordinal;
    HexDirection(int ord){
        _ordinal = ord;
        _values[ord] = this;
    }

    @Override
    public int ordinal() {
        return _ordinal;
    }

    @Override
    public HexDirection[] values() {
        return _values;
    }

}
