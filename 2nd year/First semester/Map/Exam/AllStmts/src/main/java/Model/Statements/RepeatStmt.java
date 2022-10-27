package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Expressions.NotExp;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIStack;

import java.io.IOException;

public class RepeatStmt implements IStmt{
    private IStmt stmt;
    private Exp cond;

    public RepeatStmt(IStmt statement, Exp expression) {
        this.stmt = statement;
        this.cond = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> executionStack = state.getStk();
        IStmt act = new CompStmt(this.stmt, new WhileStmt(new NotExp(this.cond), this.stmt));
        executionStack.push(act);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    public String toString() {
        return "repeat{ \n " + this.stmt.toString() + "\n}until " + this.cond.toString();
    }
}
