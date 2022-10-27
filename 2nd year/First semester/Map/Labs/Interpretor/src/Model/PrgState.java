package Model;

import Model.Statements.IStmt;
import Model.Utils.*;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Stack;


public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> output;
    IStmt originalProgram;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg){
        this.exeStack = stk;
        this.symTable = symtbl;
        this.output = ot;
        this.originalProgram = deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    public PrgState(IStmt is){
        this.exeStack = new MyStack<>();
        this.symTable = new MyDictionary<>();
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

    public void setOutput(MyList<Value> output) {
        this.output = output;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString() {
        return "PrgState{" + "\n" +
                "exeStack: " + exeStack.toString() + "\n" +
                "symTable: " + symTable.toString() + "\n" +
                "output: " + output.toString() + "\n" +
                "originalProgram: " + originalProgram.toString() +
                "\n--------------------------------\n";
    }
}
