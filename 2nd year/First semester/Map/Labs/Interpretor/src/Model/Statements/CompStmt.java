package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Utils.MyStack;

public class CompStmt implements IStmt{
    private IStmt first;
    private IStmt end;

    public CompStmt(IStmt f, IStmt e) {
        this.first = f;
        this.end = e;
    }

    public PrgState execute(PrgState state) throws MyException{
        MyStack<IStmt> stk=state.getStk();
        stk.push(end);
        stk.push(first);
        return state;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + end.toString() + ")";
    }
}
