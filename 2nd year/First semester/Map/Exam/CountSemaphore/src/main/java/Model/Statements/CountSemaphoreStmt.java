package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyISemaphore;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountSemaphoreStmt implements IStmt{
    private String var;
    private Exp exp;
    private static Lock lock = new ReentrantLock();


    public CountSemaphoreStmt(String var, Exp exp){
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> heapTbl = state.getHeap();
        MyISemaphore semaphoreTbl =  state.getSemaphore();

        if (symTbl.isDefined(var)) {
            lock.lock();
            int semFreePos = semaphoreTbl.getCurrentFreeAddress();

            IntValue number1 = (IntValue) exp.eval(symTbl, heapTbl);

            semaphoreTbl.put(semFreePos, new Pair<Integer, ArrayList<Integer>>(number1.getVal(), new ArrayList<Integer>()));
            symTbl.update(var, new IntValue(semFreePos));
            lock.unlock();
        } else throw new MyException("Variable is not defined");

        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.isDefined(var)) {
            throw new MyException("Variable is not defined.");
        }

        if (!typeEnv.lookup(var).equals(new IntType())){
            throw new MyException("Variable type is not int");
        }
        if(!exp.typecheck(typeEnv).equals(new IntType())) {
            throw new MyException("Expression type is not int");
        }

        return typeEnv;
    }

    @Override
    public String toString() {
        return "Semaphore(" + var + "; " + exp.toString() + ")";
    }
}
