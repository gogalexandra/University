package Model.Utils;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> st;

    public MyStack() { this.st = new Stack<T>(); }

    public MyStack(Stack<T> st) {
        this.st = st;
    }

    @Override
    public Stack<T> getStack() {
        return this.st;
    }

    @Override
    public void push(T elem) {
        st.push(elem);
    }

    @Override
    public T pop() {
        return this.st.pop();
    }

    @Override
    public T peek() {
        return this.st.peek();
    }

    @Override
    public void delete() {
        this.st.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.st.empty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(T elem : this.st)
            s.append(elem.toString()).append(" ");
        return s.toString();
    }
}
