package Model.Utils;

import java.util.Map;

public interface MyIHeap<T>{

    int allocate(T val);
    T getAddr(int addr);
    void putAddr(int addr, T val);
    T deallocate(int addr);
    boolean lookup(int addr);
    void update(int addr, T val);
    void setContent(Map<Integer, T> heap);
    Map<Integer, T> getContent();
}