package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.Value;

public class VarExp implements Exp {
    private String id;

    public VarExp(String id){
        this.id = id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public String toString() {
        return this.id;
    }
}
