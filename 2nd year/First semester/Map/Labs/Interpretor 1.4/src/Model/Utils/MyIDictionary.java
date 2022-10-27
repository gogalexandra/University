package Model.Utils;

import Model.Values.Value;

import java.util.Collection;
import java.util.Map;

public interface MyIDictionary<Key, Value>{
    public Collection<Value> values();
    public Collection<Key> keys();
    public Value getKey(Key k);
    Map<Key, Value> getAll();
    public boolean isEmpty();
    public void delete();
    public void add(Key k, Value v);
    public void update(Key k, Value v);
    public void remove(Key k);
    public int size();
    public Value lookup(Key id);
    public boolean isDefined(Key id);
    public MyIDictionary<Key, Value> deepCopy();
    void setContent(Map<Key, Value> content);
    Map<Key, Value> getContent();
}
