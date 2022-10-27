package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Value> hp) throws MyException;
    Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
