package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyStack;

public class ForkStmt implements IStmt{
    private IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState ps) {
        return new PrgState(
                ps.getId() * 10,
                new MyStack<IStmt>(),
                ps.getSymTable().deepCopy(),
                ps.getFileTable(),
                ps.getHeap(),
                ps.getOutput(),
                this.stmt);
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        stmt.typecheck((MyDictionary<String, Type>) typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" + this.stmt.toString() + ")";
    }
}
