package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIStack;
import Model.Values.RefValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class wH implements IStmt{
    private String varName;
    private Exp exp;

    public wH(String varName, Exp exp){
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIDictionary<Value, BufferedReader> fileTbl= state.getFileTable();
        MyIHeap<Value> heap = state.getHeap();
        if (symTbl.isDefined(varName)){
            Value v = symTbl.lookup(varName);
            if (v.getType() instanceof RefType){
                int addr = ((RefValue) v).getAddress();
                if (heap.lookup(addr)){
                    Value value = exp.eval(symTbl, heap);
                    if (value.getType().equals(((RefValue) v).getLocationType())){
                        heap.update(addr, value);
                    }
                    else
                        throw new MyException("Types do not match");
                }
                else
                    throw new MyException("Address not in heap");
            }
            else
                throw new MyException("Variable not of type ref");
        }
        else
            throw new MyException("Variable name not defined");

        return state;
    }
}
