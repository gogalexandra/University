package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyStack;

public class CompStmt implements IStmt{
    private IStmt first;
    private IStmt end;

    public CompStmt(IStmt f, IStmt e) {
        this.first = f;
        this.end = e;
    }

    public CompStmt(OpenRFile f) {
    }

    public PrgState execute(PrgState state) throws MyException{
        MyStack<IStmt> stk=state.getStk();
        stk.push(end);
        stk.push(first);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return end.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + end.toString() + ")";
    }

    public IStmt getFirst() {
        return this.first;
    }

    public IStmt getSecond() {
        return this.end;
    }
}
