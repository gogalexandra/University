package Model;

import Model.Statements.IStmt;
import Model.Utils.*;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Stack;


public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIDictionary<Value, BufferedReader> fileTable;
    MyIList<Value> output;
    IStmt originalProgram;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIDictionary<Value, BufferedReader> fileTable, MyIList<Value> ot, IStmt prg){
        this.exeStack = stk;
        this.symTable = symtbl;
        this.fileTable = fileTable;
        this.output = ot;
        this.originalProgram = deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    public PrgState(IStmt is){
        this.exeStack = new MyStack<>();
        this.symTable = new MyDictionary<>();
        this.fileTable = new MyDictionary<>();
        this.output = new MyList<>();
        this.originalProgram = is;
        this.exeStack.push(originalProgram);

    }

    public IStmt deepCopy(IStmt prg) {
        return prg;
    }

    //getters

    public MyStack<IStmt> getStk(){
        return (MyStack<IStmt>) this.exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return this.symTable;
    }

    public MyIDictionary<Value, BufferedReader> getFileTable() {
        return this.fileTable;
    }

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

    public void setOutput(MyList<Value> output) {
        this.output = output;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString() {
        return "PrgState{" + "\n" +
                "ExeStack: " + exeStack.toString() + "\n" +
                "SymTable: " + symTable.toString() + "\n" +
                "FileTable: " + fileTable.toString() + "\n" +
                "Output: " + output.toString() + "\n" +
                "\n--------------------------------\n";
    }
}
