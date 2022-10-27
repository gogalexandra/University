package Model.Expressions;

import Model.Exceptions.MyException;
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
        Value v;
        v = exp.eval(tbl, hp);
        int addr;
        if (v instanceof RefValue){
            addr = ((RefValue) v).getAddress();
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
    public String toString() {
        return "rH(" + exp.toString() +
                ')';
    }
}
