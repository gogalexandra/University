package Model.Utils;

import java.util.List;

public interface MyIList<T>{
    void add(T elem);
    T pop();
    T get(int index);
    void delete();
    boolean isEmpty();
    String toString();

    List<T> getAll();
}
