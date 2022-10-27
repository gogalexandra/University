package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyILock;
import Model.Utils.MyIStack;
import Model.Values.IntValue;
import Model.Values.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockStmt implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();

    public UnlockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyILock lockTable = state.getLockTable();
        MyIStack<IStmt> stack = state.getStk();


        IntValue lockTableIndex = (IntValue)symTbl.lookup(var);

        if (lockTable.getLock().isDefined(lockTableIndex.getVal())) {
            lockTable.getLock().update(lockTableIndex.getVal(), -1);
        } else throw new MyException("The variable is not defined in the lock table");

        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.isDefined(var)) {
            throw new MyException("Variable is undefined");
        }

        if (!typeEnv.lookup(var).equals(new IntType())) {
            throw new MyException("Variable type is not int");
        }

        return typeEnv;
    }


    @Override
    public String toString() {
        return "unlock(" + var + ")";
    }
}
