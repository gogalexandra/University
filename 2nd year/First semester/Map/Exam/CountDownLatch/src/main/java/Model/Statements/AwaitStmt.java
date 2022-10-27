package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utils.*;
import Model.Values.IntValue;
import Model.Values.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStmt implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();

    public AwaitStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> heapTbl = state.getHeap();
        MyIStack<IStmt> stack = state.getStk();
        MyILatch latchTbl = state.getLatchTable();


        if (symTbl.isDefined(var)) {
            IntValue foundValue = (IntValue) symTbl.lookup(var);

            if (latchTbl.getLatch().isDefined(foundValue.getVal())) {
                lock.lock();
                int latchVal = latchTbl.getLatch().lookup(foundValue.getVal());

                if (latchVal != 0) {
                    stack.push(this);
                }
                lock.unlock();
            } else throw new MyException("Variable is not defined in the latch table");

        } else throw new MyException("Variable is not defined");

        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.isDefined(var) || !typeEnv.lookup(var).equals(new IntType())) {
            throw new MyException("Variable not defined or not int type3");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "await(" + var + ")";
    }
}
