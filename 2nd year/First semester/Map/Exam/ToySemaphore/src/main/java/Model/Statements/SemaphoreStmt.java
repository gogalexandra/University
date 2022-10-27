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
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreStmt implements IStmt{
    private String var;
    private Exp exp1;
    private Exp exp2;
    private static Lock lock = new ReentrantLock();

    public SemaphoreStmt(String var, Exp exp1, Exp exp2){
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> heapTbl = state.getHeap();
        MyIStack<IStmt> stack = state.getStk();
        MyISemaphore toySemaphoreTbl = state.getSemaphore();

        if (symTbl.isDefined(var)) {
            Value number1 = exp1.eval(symTbl, heapTbl);
            Value number2 = exp2.eval(symTbl, heapTbl);

            if (number1.getType().equals(new IntType()) && number2.getType().equals(new IntType())) {
                lock.lock();
                int freePosition = toySemaphoreTbl.getCurrentFreeAddress();
                toySemaphoreTbl.put(freePosition, new MyTriplet<>(
                        ((IntValue)number1).getVal(), new ArrayList<>(), ((IntValue)number2).getVal()
                ));
                symTbl.update(var, new IntValue(freePosition));
                lock.unlock();
            } else throw new MyException("Expression type is not int");
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

        if (!exp1.typecheck(typeEnv).equals(new IntType()) || !exp2.typecheck(typeEnv).equals(new IntType())) {
            throw new MyException("Expressions type is not int");
        }

        return typeEnv;
    }


    @Override
    public String toString() {
        return "Semaphore( " + var + ", " + exp1.toString() + ", " + exp2.toString() + ")";
    }
}
