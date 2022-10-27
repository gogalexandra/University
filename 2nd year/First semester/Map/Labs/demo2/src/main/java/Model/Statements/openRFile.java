package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStmt{
    private Exp exp;

    public openRFile(Exp valueExp){
        this.exp =valueExp;
    }

    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIDictionary<Value, BufferedReader> fileTbl= state.getFileTable();
        Value val = this.exp.eval(symTbl, state.getHeap());
        Type t = val.getType();
        if (t.equals(new StringType())) {
            if (!fileTbl.isDefined(val)) {
                FileReader in = new FileReader(val.toString());
                BufferedReader reader = new BufferedReader(in);
                fileTbl.add(val, reader);
            }
            else
                throw new MyException("File already does exist");
        }
        else
            throw new MyException("Expression not of string type");
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = this.exp.typecheck(typeEnv);
        if (typeExp.equals(new StringType())) {
            return typeEnv;
        } else throw new MyException("The expression is not of type string.");
    }

    @Override
    public String toString() {
        return "open file(" + this.exp.toString() + ")";
    }
}
