package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utils.*;
import Model.Values.IntValue;
import Model.Values.Value;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LatchStmt implements IStmt{
    private String var;
    private Exp exp;
    private static Lock lock = new ReentrantLock();

    public LatchStmt(String var, Exp exp){
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> heapTbl = state.getHeap();
        MyIStack<IStmt> stack = state.getStk();
        MyILatch latchTbl = state.getLatchTable();

        IntValue val = (IntValue)exp.eval(symTbl, heapTbl);
        if (val.getType().equals(new IntType())){
            IntValue varValue = (IntValue)symTbl.lookup(var);
            lock.lock();
            int latchTablePosition = latchTbl.getCurrentFreeAddress();
            latchTbl.getLatchAddress();
            latchTbl.put(latchTablePosition, varValue.getVal());
            symTbl.update(var, new IntValue(latchTablePosition));
            lock.unlock();

            return null;
        }else throw new MyException("Type should be int");
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.isDefined(var)) {
            throw new MyException("Variable is not defined1");
        }

        if (!typeEnv.lookup(var).equals(new IntType()) || !exp.typecheck(typeEnv).equals(new IntType())) {
            throw new MyException("Type should be int");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "Latch(" + var + "; " + exp.toString() + ")";
    }
}
