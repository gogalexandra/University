package Model.Utils;

import Model.Statements.CompStmt;
import Model.Statements.IStmt;

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

    public String inOrderTraversal(IStmt stmt) {
        if (stmt instanceof CompStmt) {
            String returnString = "";

            CompStmt newStmt = (CompStmt) stmt;
            IStmt left = newStmt.getFirst();
            IStmt right = newStmt.getSecond();

            if (left instanceof CompStmt) {
                returnString += inOrderTraversal(left);
            }
            else {
                returnString += left.toString() + "\n";
            }

            if (right instanceof CompStmt) {
                returnString += inOrderTraversal(right);
            }
            else {
                returnString += right.toString() + "\n";
            }

            return returnString;
        }
        else {
            return "\n"+ stmt.toString()+"\n";
        }
    }

    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();

        for (T elem : this.st) {
            if (elem instanceof IStmt){
                stringRep.append(inOrderTraversal((IStmt) elem));
            }
            else {
                stringRep.append(elem.toString());
            }
        }
        return stringRep.toString();
    }
}
