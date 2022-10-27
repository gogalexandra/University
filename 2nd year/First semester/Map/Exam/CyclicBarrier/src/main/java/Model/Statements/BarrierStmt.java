package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
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

public class BarrierStmt implements IStmt{
    private String var;
    private Exp exp;
    private static Lock lock = new ReentrantLock();

    public BarrierStmt(String var, Exp exp){
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> heapTbl = state.getHeap();
        MyIBarrier barrierTbl =  state.getBarrier();

        if (symTbl.isDefined(var)) {

            if (exp.eval(symTbl, heapTbl).getType().equals(new IntType())) {
                lock.lock();
                int semFreePos = barrierTbl.getCurrentFreeAddress();
                IntValue number1 = (IntValue) exp.eval(symTbl, heapTbl);
                barrierTbl.put(semFreePos, new Pair<Integer, ArrayList<Integer>>(number1.getVal(), new ArrayList<Integer>()));
                symTbl.update(var, new IntValue(semFreePos));
                lock.unlock();
            }else throw new MyException("Exp is not of type int");

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
        return "newBarrier(" + var + "; " + exp.toString() + ")";
    }
}
