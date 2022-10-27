package Model.Utils;

import java.util.Stack;

public interface MyIStack<T> {
    public Stack<T> getStack();
    public void push(T elem);
    public T pop();
    public T peek();
    public void delete();
    public boolean isEmpty();
    public String toString();
}
