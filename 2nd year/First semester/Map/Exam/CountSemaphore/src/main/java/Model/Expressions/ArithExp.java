package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithExp implements Exp {

    private Exp e1;
    private Exp e2;
    private char op;

    public ArithExp(char op, Exp valueExp, Exp valueExp1) {
        this.op = op;
        this.e1 = valueExp;
        this.e2 = valueExp1;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        v2 = e2.eval(tbl, hp);
        if (!v1.equals(new IntValue()))
            throw new MyException("first operand is not an integer");
        else {
            if (!v2.getType().equals(new IntType()))
                throw new MyException("second operand is not an integer");
            else {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                switch (op) {
                    case '+':
                        return new IntValue(n1 + n2);
                    case '-':
                        return new IntValue(n1 - n2);
                    case '*':
                        return new IntValue(n1 * n2);
                    case '/':
                        if (n2 == 0)
                            throw new MyException("division by zero");
                        else
                            return new IntValue(n1 / n2);
                }
            }
        }
        //??? not necessary
        return v1;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = e1.typecheck(typeEnv);
        type2 = e2.typecheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            } else
            throw new MyException("second operand is not an integer");
        }else
        throw new MyException("first operand is not an integer");
    }

    @Override
    public String toString() {
        return e1.toString() + this.op + e2.toString() ;
    }
}