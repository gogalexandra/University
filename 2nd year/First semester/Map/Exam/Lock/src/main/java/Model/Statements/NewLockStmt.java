package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyILock;
import Model.Values.IntValue;
import Model.Values.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStmt implements IStmt {
    private String var;
    private static Lock lock = new ReentrantLock();

    public NewLockStmt(String var){
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyILock lockTable = state.getLockTable();

        if (symTbl.isDefined(var)) {
            lock.lock();
            int lockTableIndex = lockTable.getCurrentFreeAddress();
            //lockTable.findNextFreeAddress(); // - in order to find the new address for the next statement
            lockTable.put(lockTableIndex, -1);
            symTbl.update(var, new IntValue(lockTableIndex));
            lock.unlock();
        } else throw new MyException("Variable not defined");

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
        return "Lock(" + var + ")";
    }
}
