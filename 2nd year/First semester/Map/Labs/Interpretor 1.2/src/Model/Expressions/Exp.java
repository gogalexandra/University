package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Utils.MyIDictionary;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl) throws MyException;
}
