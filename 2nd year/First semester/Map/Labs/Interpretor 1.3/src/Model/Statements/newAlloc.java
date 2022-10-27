package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIStack;
import Model.Values.IntValue;
import Model.Values.RefValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class newAlloc implements IStmt{
    private String varName;
    private Exp exp;

    public newAlloc(String varName, Exp exp){
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIDictionary<Value, BufferedReader> fileTbl= state.getFileTable();
        MyIHeap<Value> heap = state.getHeap();
        int location;
        if (symTbl.isDefined(varName)){
            Value v = symTbl.lookup(varName);
            if (v.getType() instanceof RefType){
                Value val = this.exp.eval(symTbl, heap);
                Type t = val.getType();
                if (t.equals(((RefValue)v).getLocationType())) {
                    location = heap.allocate(val);
                    RefValue rf = new RefValue(location, ((RefValue) v).getLocationType());
                    symTbl.update(varName, rf);
                }
                else
                    throw new MyException("Types not equal");
            }
            else
                throw new MyException("Variable not of type ref");
        }
        else
            throw new MyException("Variable name not defined");

        return state;
    }

    @Override
    public String toString() {
        return "newAlloc{" +
                "varName='" + varName + ' ' +
                ", exp=" + exp +
                '}';
    }
}
