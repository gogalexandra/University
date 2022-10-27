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

public class LockStmt implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();

    public LockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyILock lockTable = state.getLockTable();
        MyIStack<IStmt> stack = state.getStk();

        // we check that variableId represents a valid index in the symTbl

        IntValue lockTableIndex = (IntValue)symTbl.lookup(var);
        if (lockTable.getLock().isDefined(lockTableIndex.getVal())) {
            lock.lock();
            if (lockTable.getLock().lookup(lockTableIndex.getVal()) == -1) {
                lockTable.getLock().update(lockTableIndex.getVal(), state.getId());
            } else {
                stack.push(new LockStmt(var));
            }
            lock.unlock();
        } else throw new MyException("The variable does not have a lock index.");

        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.isDefined(var)) {
            throw new MyException("Variable " + var + " is not defined");
        }

        if (!typeEnv.lookup(var).equals(new IntType())) {
            throw new MyException("Variable type is not int");
        }
        return typeEnv;
    }

    @Override
    public String toString() {
        return "lock(" + var + ")";
    }
}
