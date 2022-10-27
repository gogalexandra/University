package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Values.IntValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt{
    private Exp exp;
    private String var_name;

    public readFile(Exp exp, String var_name){
        this.exp = exp;
        this.var_name = var_name;
    }

    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIDictionary<Value, BufferedReader> fileTbl= state.getFileTable();
        if (symTbl.isDefined(var_name)){
            Value v = symTbl.lookup(var_name);
            if (v.getType() instanceof RefType){
                Value val = this.exp.eval(symTbl, state.getHeap());
                Type t = val.getType();
                if (t.equals(new StringType())) {
                    if (fileTbl.isDefined(val)) {
                        BufferedReader br = fileTbl.lookup(val);
                        String line = "";
                        line = br.readLine();
                        int nr = 0;
                        Value newVal = new IntValue();
                        if (line != null){
                            nr = Integer.parseInt(line);
                            newVal = new IntValue(nr);
                        }
                        symTbl.add(var_name, newVal);
                    }
                    else
                        throw new MyException("File does not exist");
                }
                else
                    throw new MyException("Expression not of string type");
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
        Type typeVar = typeEnv.lookup(var_name);
        Type typeExp = exp.typecheck(typeEnv);
        if (typeVar.equals(new IntType()) && typeExp.equals(new StringType())) {
            return typeEnv;
        } else throw new MyException("Invalid variable name or expression type.");
    }

    @Override
    public String toString() {return "readFileStmt(" + this.exp.toString() + ", " + this.var_name + ")";}

}
