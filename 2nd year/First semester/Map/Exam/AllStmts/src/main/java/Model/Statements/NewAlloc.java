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

public class NewAlloc implements IStmt{
    private String varName;
    private Exp exp;

    public NewAlloc(String varName, Exp exp){
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
            Value val = symTbl.lookup(varName);
            if (val.getType() instanceof RefType){
                Value cond = this.exp.eval(symTbl, heap);
                Type t = cond.getType();
                RefValue refVal = (RefValue) val;
                if (t.equals((refVal.getLocationType()))) {
                    location = heap.allocate(cond);
                    RefValue rf = new RefValue(location, refVal.getLocationType());
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

        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(varName);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "newAlloc(" +  varName + ' ' +
                ", " + exp +
                ')';
    }
}
