package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
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
    public String toString() {
        return "wH(" + varName + ", " + exp.toString() +
                ')';
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIDictionary<Value, BufferedReader> fileTbl= state.getFileTable();
        MyIHeap<Value> heap = state.getHeap();
        if (symTbl.isDefined(varName)){
            Value val = symTbl.lookup(varName);
            if (val.getType() instanceof RefType){
                int addr = ((RefValue) val).getAddress();
                if (heap.lookup(addr)){
                    Value evalExp = exp.eval(symTbl, heap);
                    if (evalExp.getType().equals(((RefValue) val).getLocationType())){
                        heap.update(addr, evalExp);
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

        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(varName);
        Type typeExp = exp.typecheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp))) {
            return typeEnv;
        } else throw new MyException("The sides have different types");
    }
}
