package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class closeRFile implements IStmt{
    private Exp exp;

    public closeRFile(Exp valueExp){
        this.exp =valueExp;
    }

    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIDictionary<Value, BufferedReader> fileTbl= state.getFileTable();
        Value val = this.exp.eval(symTbl, state.getHeap());
        Type t = val.getType();
        if (t.equals(new StringType())) {
            if (fileTbl.isDefined(val)) {
                BufferedReader bf = fileTbl.lookup(val);
                bf.close();
                fileTbl.remove(val);
            }
            else
                throw new MyException("File does not exist");
        }
        else
            throw new MyException("Expression not of string type");
        return state;
    }
    @Override
    public String toString() {
        return "close file(" + this.exp.toString() + ")";
    }
}
