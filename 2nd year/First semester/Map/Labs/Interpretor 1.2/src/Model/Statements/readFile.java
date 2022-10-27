package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Values.IntValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
            if (v.getType().equals(new IntType())){
                Value val = this.exp.eval(symTbl);
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
                throw new MyException("Variable not of type int");
        }
        else
            throw new MyException("Variable name not defined");

        return state;
    }
    @Override
    public String toString() {return "readFileStmt(" + this.exp.toString() + ", " + this.var_name + ")";}

}
