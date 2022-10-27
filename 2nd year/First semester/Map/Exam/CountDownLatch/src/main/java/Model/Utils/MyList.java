package Model.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{

    private List<T> list;

    public MyList(){
        this.list = new ArrayList<T>();
    }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public T pop() {
        return list.get(list.size() - 1);
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public void delete() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(T elem : this.list)
            s.append(elem.toString()).append(" ");
        return s.toString();
    }

    @Override
    public List<T> getAll() {
        return this.list;
    }
}
