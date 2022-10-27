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
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStmt implements IStmt{
    private String var;
    private static Lock lock = new ReentrantLock();

    public AcquireStmt(String var){
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
                int N1 = entry.getValue0();
                int N2 = entry.getValue2();
                ArrayList<Integer> list = entry.getValue1();
                int NL = list.size();
                if (N1 - N2 > NL) {
                    if (!list.contains(state.getId())) {
                        list.add(state.getId());
                    }
                } else {
                    stack.push(this);
                }

                lock.unlock();
            } else throw new MyException("Semaphore index is not defined");
        } else throw new MyException("Variable is not defined");
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.isDefined(var)) {
            throw new MyException("Variable is not defined");
        }

        if (!typeEnv.lookup(var).equals(new IntType())) {
            throw new MyException("Variable is not of type int");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "acquire( " + var + " )";
    }
}
