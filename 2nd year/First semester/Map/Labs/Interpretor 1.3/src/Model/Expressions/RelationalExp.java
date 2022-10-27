package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op;

    public RelationalExp(Exp e1, Exp e2, String op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        v2 = e2.eval(tbl, hp);
        if (!v1.getType().equals(new IntType()))
            throw new MyException("first operand is not int");
        else {
            if (!v2.getType().equals(new IntType()))
                throw new MyException("second operand is not int");
            else {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                switch (op) {
                    case "<":
                        return new BoolValue(n1 < n2);
                    case "<=":
                        return new BoolValue(n1 <= n2);
                    case "==":
                        return new BoolValue(n1 == n2);
                    case "!=":
                        return new BoolValue(n1 != n2);
                    case ">":
                        return new BoolValue(n1 > n2);
                    case ">=":
                        return new BoolValue(n1 >= n2);
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

