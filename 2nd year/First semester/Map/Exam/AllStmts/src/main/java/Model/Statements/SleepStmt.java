package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIStack;

import java.io.IOException;

public class SleepStmt implements IStmt{
    private Integer number;

    public SleepStmt(int number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        if (this.number != 0) {
            MyIStack<IStmt> executionStack = state.getStk();
            executionStack.push(new SleepStmt(this.number - 1));
            state.setExeStack(executionStack);
        }
        return null;
    }

    public String toString() {
        return "sleep( " + this.number.toString() + " )";
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
