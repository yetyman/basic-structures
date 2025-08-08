package structures.basic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


//for essentially all the same purposes as Enums
// except additional items can be added past runtime and inheritance can be leveraged
public abstract class EnumeratedList<T extends EnumeratedList<T>> implements Serializable, Comparable<T> {

    public final int ordinal;
    public final String name;

    private static final Map<Class<?>, List<? super EnumeratedList<?>>> valuesMap = new ConcurrentHashMap<>();
    private final List<T> values;

    public EnumeratedList() {
        //noinspection unchecked
        values = (List<T>)(Object)valuesMap.computeIfAbsent(this.getClass(), c->new CopyOnWriteArrayList<>());//for speed and concurrent safety;)

        //noinspection unchecked
        values.add((T) this);

        //noinspection SuspiciousMethodCalls
        ordinal = values.indexOf(this);//not using values.size for concurrency concerns

        // Get the name from the calling context
        name = findFieldName();
    }

    private String findFieldName() {
        try {
            // Get the class that's declaring the static field
            Class<?> declaringClass = getClass();

            // Find the field being initialized
            for (Field field : declaringClass.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) &&
                        Modifier.isFinal(field.getModifiers())) {
                    field.setAccessible(true);
                    if (field.get(null) == this) {//how can this call work in the constructor? Java initialization sequence
                        return field.getName();
                    }
                }
            }
        } catch (Exception e) {
            return "UNKNOWN";
        }
        return "UNKNOWN";
    }

    public String name() {
        return name;
    }
    public int ordinal(){
        return this.ordinal;
    }

    public static <T extends EnumeratedList<T>> List<T> values(Class<T> enumClass) {
        //noinspection unchecked
        return (List<T>)(Object)valuesMap.get(enumClass);
    }

    public List<T> values(){
        return values;
    }

    public int compareTo(T o) {
        return this.ordinal()-o.ordinal();
    }

}
