package structures.directions;

public interface EvenDirection<E extends EvenDirection<E>> extends Direction<E>{
    default E opposite(){
        return rotate(this, this.values().length/2);
    }

}
