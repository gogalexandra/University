package Model.Statements;

import Model.PrgState;

public class NopStmt implements IStmt{

    public PrgState execute(PrgState state){
        return state;
    }

    @Override
    public String toString(){
        return "no operation";
    }
}
