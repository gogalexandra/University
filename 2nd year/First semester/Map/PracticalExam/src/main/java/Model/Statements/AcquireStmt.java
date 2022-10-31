package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIStack;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
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
        MyIHeap<Value> heapTbl =  state.getHeap();
        MyIStack<IStmt> stack = state.getStk();
        MyIDictionary<Integer, Pair<Integer, ArrayList<Integer>>> semaphoreTbl = state.getSemaphore().getSemaphore();

        if (symTbl.isDefined(var)) {
            IntValue indexValue = (IntValue)symTbl.lookup(var);

            if (semaphoreTbl.isDefined(indexValue.getVal())) {
                lock.lock();
                Pair<Integer, ArrayList<Integer>> semaValue = semaphoreTbl.lookup(indexValue.getVal());

                int N1 = semaValue.getKey();
                int NL = semaValue.getValue().size();
                ArrayList<Integer> semaphoreArray = semaValue.getValue();

                if (N1 > NL) {
                    if (!semaphoreArray.contains(state.getId())) {
                        semaphoreArray.add(state.getId());
                        semaphoreTbl.update(indexValue.getVal(), new Pair<Integer, ArrayList<Integer>>(N1, semaphoreArray));
                    }
                } else stack.push(this);

                lock.unlock();
            } else throw new MyException("Semaphore index not defined");

        } else throw new MyException("Variable is not defined");


        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.isDefined(var)) {
            throw new MyException("Variable is not defined");
        }

        if (!typeEnv.lookup(var).equals(new IntType())) {
            throw new MyException("Variable is not int");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "acquire(" + var + ")";
    }
}