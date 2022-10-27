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

public class CountDownStmt implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();

    public CountDownStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIList<Value> output = state.getOutput();

        MyILatch latchTbl = state.getLatchTable();


        IntValue foundIndex = (IntValue)symTbl.lookup(var);

        if (latchTbl.getLatch().isDefined(foundIndex.getVal())) {
            lock.lock();
            int latchVal = latchTbl.getLatch().lookup(foundIndex.getVal());
            if (latchVal > 0) {
                latchTbl.getLatch().update(foundIndex.getVal(), latchVal - 1);
                output.add(new IntValue(state.getId()));
            } else output.add(new IntValue(state.getId()));
            lock.unlock();
        } else throw new MyException("Latch index is not defined");

        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.isDefined(var) || !typeEnv.lookup(var).equals(new IntType())) {
            throw new MyException("Variable not defined or not int type");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "countDown(" + var + ")";
    }
}
