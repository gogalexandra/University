package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Expressions.ValueExp;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.*;
import Model.Values.Value;

public class AssignStmt implements IStmt{
    String id;
    Exp exp;

    public AssignStmt(String s, Exp valueExp) {
        this.id = s;
        this.exp = valueExp;
    }


    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl);
            Type typId = symTbl.lookup(id).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match");
        }
        else
            throw new MyException("the used variable" +id + " was not declared before");
        return state;
        }

    @Override
    public String toString(){ return id + "=" + exp.toString();}

}