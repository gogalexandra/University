package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Values.Value;

public class VarDeclStmt implements IStmt {
    String name;
    Type type;

    public VarDeclStmt(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        if (symTbl.isDefined(name))
                throw new MyException("Variable is already declared");
        else{
            symTbl.add(name, type.defaultValue());
        }

        return state;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
}