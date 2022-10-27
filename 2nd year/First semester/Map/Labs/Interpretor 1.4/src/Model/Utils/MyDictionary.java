package Model.Utils;

import com.sun.jdi.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<Key, Value> implements MyIDictionary<Key, Value>{

    private Map<Key, Value> dict;

    public MyDictionary(){
        this.dict = new HashMap<Key, Value>();
    }
    @Override
    public Collection<Value> values() {
        return this.dict.values();
    }

    @Override
    public Collection<Key> keys() {
        return this.dict.keySet();
    }

    @Override
    public Value getKey(Key k) {
        return this.dict.get(k);
    }

    @Override
    public Map<Key, Value> getAll() {
        return this.dict;
    }

    @Override
    public boolean isEmpty() {
        return this.dict.isEmpty();
    }

    @Override
    public void delete() {
        this.dict.clear();
    }

    @Override
    public void add(Key k, Value v) {
       this.dict.put(k, v);
    }

    @Override
    public void update(Key k, Value v) {
        this.dict.replace(k, v);
    }

    @Override
    public void remove(Key k) {
        this.dict.remove(k);
    }

    @Override
    public int size() {
        return dict.size();
    }

    @Override
    public Value lookup(Key id) {
        return this.dict.get(id);
    }

    @Override
    public boolean isDefined(Key id) {
        return this.dict.containsKey(id);
    }

    @Override
    public void setContent(Map<Key, Value> content) {
        this.dict.clear();
        this.dict.putAll(content);
    }

    @Override
    public Map<Key, Value>getContent() {
        return this.dict;
    }

    @Override
    public MyIDictionary<Key, Value> deepCopy() {
        MyDictionary<Key, Value> dictCopy = new MyDictionary<Key, Value>();
        dictCopy.setContent(this.getContent());

        return dictCopy;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Key key : dict.keySet())
            s.append(key.toString()).append(" -> ").append(dict.get(key).toString()).append("; ");
        return s.toString();
    }
}
