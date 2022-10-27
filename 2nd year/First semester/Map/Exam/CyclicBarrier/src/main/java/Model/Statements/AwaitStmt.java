package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utils.*;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStmt implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();


    public AwaitStmt(String var){
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> heapTbl = state.getHeap();
        MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> barrierTable = state.getBarrier().getBarrier();
        MyIStack<IStmt> stack = state.getStk();

        if (symTbl.isDefined(var)) {
            IntValue barrierIndex = (IntValue)symTbl.lookup(var);
            if (barrierTable.isDefined(barrierIndex.getVal())) {
                lock.lock();
                Pair<Integer, ArrayList<Integer>> entry = barrierTable.lookup(barrierIndex.getVal());
                Integer N1 = entry.getKey();
                ArrayList<Integer> list = entry.getValue();
                Integer NL = list.size();

                if (N1 > NL) {
                    if (!entry.getValue().contains(state.getId())) {
                        list.add(state.getId());
                        barrierTable.update(barrierIndex.getVal(), new Pair<Integer, ArrayList<Integer>>(N1, list));
                    }
                    stack.push(this);
                }

                lock.unlock();
            } else throw new MyException("Barrier Index is not defined.");

        } else throw new MyException("Variable is not defined");

        return null;    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.isDefined(var)) {
            throw new MyException("Variable is not defined");
        }

        if (!typeEnv.lookup(var).equals(new IntType())) {
            throw new MyException("Variable is not defined");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "awaitBarrier(" + var + ")";
    }
}
