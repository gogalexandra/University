package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Expressions.RelationalExp;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Values.BoolValue;
import Model.Values.Value;
import javafx.beans.binding.BooleanExpression;

import java.io.BufferedReader;
import java.io.IOException;

public class ForStmt implements IStmt{
    private String var;
    private Exp init;
    private Exp cond;
    private Exp increment;
    private IStmt stmt;

    public ForStmt(String var, Exp init, Exp cond, Exp increment, IStmt iStmt){
        this.var = var;
        this.init = init;
        this.cond = cond;
        this.increment = increment;
        this.stmt = iStmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIDictionary<Value, BufferedReader> fileTbl= state.getFileTable();
        MyIStack<IStmt> executionStack = state.getStk();
        IStmt newStatement = new CompStmt(new AssignStmt(var, init), new WhileStmt(new RelationalExp(new VarExp("v"), cond, "<"),
                new CompStmt(stmt, new AssignStmt(var, increment))));
        executionStack.push(newStatement);
        state.setExeStack(executionStack);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp1 = init.typecheck(typeEnv);
        if (typeExp1.equals(new IntType())) {
            stmt.typecheck((MyDictionary<String, Type>) typeEnv.deepCopy());
            Type typeExp2 = cond.typecheck(typeEnv);
            if (typeExp2.equals(new IntType())) {
                stmt.typecheck((MyDictionary<String, Type>) typeEnv.deepCopy());
                Type typeExp3 = increment.typecheck(typeEnv);
                if (typeExp3.equals(new IntType())) {
                    stmt.typecheck((MyDictionary<String, Type>) typeEnv.deepCopy());
                    return typeEnv;
                } else throw new MyException("The Expression is not int");
            } else throw new MyException("The Expression is not int");
        } else throw new MyException("The Expression is not int");
    }

    @Override
    public String toString(){
        return "for( " + var + "=" + init.toString() + "; " + var + "<" + cond.toString() + "; " + var + "=" + increment.toString() + " ) \n " + stmt.toString();
    }
}
