package Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements MyIHeap<T> {
    private int nextAddress;
    private Map<Integer, T> heap;

    public MyHeap(Map<Integer, T> heap, int nextAddress) {
        this.nextAddress = nextAddress;
        this.heap = heap;
    }

    public MyHeap() {
        this.nextAddress = 1;
        this.heap = new HashMap();
    }

    public void setContent(Map<Integer, T> heap){
        this.heap = heap;
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.heap;
    }

    @Override
    public int allocate(T val) {
        this.heap.put(this.nextAddress, val);
        int adr_copy = this.nextAddress;
        this.nextAddress++;
        return adr_copy;
    }

    @Override
    public T getAddr(int addr) {
        return this.heap.get(addr);
    }

    @Override
    public void putAddr(int addr, T val) {
        this.heap.put(addr, val);
    }

    @Override
    public T deallocate(int addr) {
        return this.heap.remove(addr);
    }

    @Override
    public boolean lookup(int addr) {
        return this.heap.containsKey(addr);
    }

    @Override
    public void update(int addr, T val) {
        this.heap.replace(addr, val);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        boolean firstTime = true;

        for (Map.Entry<Integer, T> entry : this.heap.entrySet()) {
            if (!firstTime) {
                s.append("\n");
            }

            s.append(entry.getKey().toString()).append(" -> ").append(entry.getValue().toString());
            firstTime = false;
        }

        return s.toString();
    }
}
