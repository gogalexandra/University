package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.RefValue;
import Model.Values.Value;

public class rH implements Exp{
    private Exp exp;

    public rH(Exp exp){
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value expEval;
        expEval = exp.eval(tbl, hp);
        int addr;
        if (expEval instanceof RefValue){
            addr = ((RefValue) expEval).getAddress();
            if (hp.lookup(addr)){
                return hp.getAddr(addr);
            }
            else
                throw new MyException("Address does not exists");
        }
        else
            throw new MyException("Not a RefValue");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = exp.typecheck(typeEnv);

        if (type instanceof RefType) {
            RefType t = (RefType) type;
            return t.getInner();
        } else throw new MyException("The variable must be of RefType");
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() +
                ')';
    }
}
