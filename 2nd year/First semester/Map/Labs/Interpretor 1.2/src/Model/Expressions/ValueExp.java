package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Utils.MyIDictionary;
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
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        return e;
    }

    @Override
    public String toString(){
        return this.e.toString();
    }
}
