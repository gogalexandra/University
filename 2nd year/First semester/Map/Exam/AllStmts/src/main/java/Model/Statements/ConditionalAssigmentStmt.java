package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIStack;

import java.io.IOException;

public class ConditionalAssigmentStmt implements IStmt{
    private String var;
    private Exp condition;
    private Exp trueCondition;
    private Exp falseCondition;

    public ConditionalAssigmentStmt(String var, Exp condition, Exp trueCondition, Exp falseCondition){
        this.var = var;
        this.condition = condition;
        this.trueCondition = trueCondition;
        this.falseCondition = falseCondition;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> executionStack = state.getStk();
        IStmt newStatement = new IfStmt(condition, new AssignStmt(var, trueCondition), new AssignStmt(var, falseCondition));
        executionStack.push(newStatement);
 //       state.setExeStack(executionStack);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return var + " = " + condition.toString() + " ? " + trueCondition.toString() + " : " + falseCondition.toString();
    }
}
