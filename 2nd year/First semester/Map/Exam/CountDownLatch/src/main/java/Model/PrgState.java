package Model;

import Model.Exceptions.MyException;
import Model.Statements.IStmt;
import Model.Utils.*;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;


public class PrgState {
    private static int count = 0;
    private int id = 0;
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIDictionary<Value, BufferedReader> fileTable;
    private MyIHeap<Value> heap;
    private MyILatch latchTable;
    private MyIList<Value> output;
    private IStmt originalProgram;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIDictionary<Value, BufferedReader> fileTable, MyIHeap<Value> heap, MyILatch latchTable, MyIList<Value> ot, IStmt prg){
        this.id = getThreadId();
        this.exeStack = stk;
        this.exeStack.push(prg);
        this.symTable = symtbl;
        this.fileTable = fileTable;
        this.heap = heap;
        this.latchTable = latchTable;
        this.output = ot;
        this.originalProgram = deepCopy(prg);//recreate the entire original prg
    }

    public PrgState(IStmt is){
        this.id = getThreadId();
        this.exeStack = new MyStack<>();
        this.exeStack.push(is);
        this.symTable = new MyDictionary<>();
        this.fileTable = new MyDictionary<>();
        this.heap = new MyHeap<>();
        this.latchTable = new MyLatch();
        this.output = new MyList<>();
        this.originalProgram = is;
    }

    public IStmt deepCopy(IStmt prg) {
        return prg;
    }

    private synchronized static int getThreadId() {
        count ++;
        return count - 1;
    }

    //getters


    public int getId() { return id; }

    public MyStack<IStmt> getStk(){
        return (MyStack<IStmt>) this.exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return this.symTable;
    }

    public MyIDictionary<Value, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public MyIHeap<Value> getHeap() {return this.heap;}

    public MyILatch getLatchTable() {return this.latchTable;}

    public MyList<Value> getOutput() {
        return (MyList<Value>) this.output;
    }

    public IStmt getOriginalProgram() {
        return this.originalProgram;
    }

    //setters

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public void setFileTable(MyIDictionary<Value, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setHeap(MyIHeap<Value> heap) {this.heap = heap;}

    public void setOutput(MyList<Value> output) {
        this.output = output;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public Boolean isNotCompleted(){ return !this.exeStack.isEmpty(); }

    public PrgState oneStep() throws MyException, IOException {
        if(exeStack.isEmpty())
            throw new MyException("Program state stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    @Override
    public String toString() {
        return "PrgState "+ id + " {" + "\n" +
                "ExeStack: " + exeStack.toString() + "\n" +
                "SymTable: " + symTable.toString() + "\n" +
                "FileTable: " + fileTable.toString() + "\n" +
                "Heap: " + heap.toString() + "\n" +
                "Semaphore: " + latchTable.toString() + "\n" +
                "Output: " + output.toString() + "\n" +
                "--------------------------------";
    }
}
