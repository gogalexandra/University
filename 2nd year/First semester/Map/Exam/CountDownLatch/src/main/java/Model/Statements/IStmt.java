package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyDictionary;

import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, IOException;
    MyDictionary<String, Type> typecheck(MyDictionary<String,Type> typeEnv) throws MyException;
}
