package structures.directions;

public class LinearDirection implements EvenDirection<LinearDirection> {
    private static final LinearDirection[] _values = new LinearDirection[2];
    public static final LinearDirection Backwards = new LinearDirection(0);
    public static final LinearDirection Forwards = new LinearDirection(1);

    private final int _ordinal;
    LinearDirection(int ord){
        _ordinal = ord;
        _values[ord] = this;
    }

    @Override
    public int ordinal() {
        return _ordinal;
    }

    @Override
    public LinearDirection[] values() {
        return _values;
    }

}
