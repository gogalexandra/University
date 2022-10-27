package Model.Statements;

import Model.*;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Utils.MyList;
import Model.Values.Value;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp exp ){
        this.exp = exp;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyList<Value> output =  state.getOutput();
        output.add(exp.eval(state.getSymTable()));
        state.setOutput(output);
        return state;
    }

    @Override
    public String toString(){
        return "print(" + exp.toString() + ")";
    }

}
