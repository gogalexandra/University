package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyList;
import Model.Values.Value;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp exp ){
        this.exp = exp;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyList<Value> output =  state.getOutput();
        output.add(exp.eval(state.getSymTable(), state.getHeap()));
        state.setOutput(output);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){
        return "print(" + exp.toString() + ")";
    }

}
