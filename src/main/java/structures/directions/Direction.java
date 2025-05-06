package structures.directions;

import java.io.Serializable;

public interface Direction<E extends Direction<E>> extends Comparable<E>, Serializable {

    default E rotate(Direction<E> dir, int add){
        var directions = dir.values();
        return directions[(dir.ordinal() + add)%directions.length];
    }

    int ordinal();

    E[] values();

    default int compareTo(E o) {
        return ordinal()-o.ordinal();
    }
}
