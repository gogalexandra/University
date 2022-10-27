package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.*;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStmt implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();

    public ReleaseStmt(String var){
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> heapTbl = state.getHeap();
        MyIStack<IStmt> stack = state.getStk();
        MyISemaphore toySemaphoreTbl = state.getSemaphore();

        if (symTbl.isDefined(var)) {
            int foundIndex = ((IntValue)symTbl.lookup(var)).getVal();
            if (toySemaphoreTbl.getSemaphore().isDefined(foundIndex)) {
                lock.lock();
                MyTriplet<Integer, ArrayList<Integer>, Integer> entry = toySemaphoreTbl.getSemaphore().lookup(foundIndex);
                ArrayList<Integer> list = entry.getValue1();
                if (list.contains(state.getId())) {
                    list.remove(Integer.valueOf(state.getId()));
                }

                lock.unlock();
            } else throw new MyException("Semaphore index is not defined");
        } else throw new MyException("Variable is not defined");

        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "release( " + var + " )";
    }
}
