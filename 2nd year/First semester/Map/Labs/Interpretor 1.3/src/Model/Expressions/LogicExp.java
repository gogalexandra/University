package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class LogicExp implements Exp {
    private Exp e1;
    private Exp e2;
    private String op;

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        v2 = e2.eval(tbl, hp);
        if (!v1.getType().equals(new BoolType()))
            throw new MyException("first operand is not bool");
        else {
            if (!v2.getType().equals(new BoolType()))
                throw new MyException("second operand is not bool");
            else {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = b1.getVal();
                n2 = b2.getVal();
                switch (op) {
                    case "and":
                        return new BoolValue(n1 && n2);
                    case "or":
                        return new BoolValue(n1 || n2);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return e1.toString() + this.op + e2.toString() ;
    }

}