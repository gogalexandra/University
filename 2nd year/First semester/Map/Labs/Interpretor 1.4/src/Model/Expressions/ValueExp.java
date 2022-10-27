package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.IntValue;
import Model.Values.Value;

public class ValueExp implements Exp{
    private Value e;

    public ValueExp(Value v) {
        this.e = v;
    }

    public Value getValue() {
        return e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        return e;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public String toString(){
        return this.e.toString();
    }
}
