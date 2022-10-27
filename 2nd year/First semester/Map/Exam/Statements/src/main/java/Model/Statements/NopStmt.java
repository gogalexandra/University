package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyDictionary;

public class NopStmt implements IStmt{

    public PrgState execute(PrgState state){
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString(){
        return "no operation";
    }
}
