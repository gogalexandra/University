package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIStack;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.io.IOException;

public class WhileStmt implements IStmt{
    private Exp exp;
    private IStmt stmt;


    public WhileStmt(Exp exp, IStmt stmt){
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        Value val = exp.eval(symTbl, state.getHeap());
        if (val.equals(new BoolValue())){
            BoolValue cond = (BoolValue) val;
            if (cond.getVal())
            {
                stk.push(this);
                stk.push(this.stmt);
            }
        }
        else
            throw new MyException("condition exp is not a boolean");
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            stmt.typecheck((MyDictionary<String, Type>) typeEnv.deepCopy());
            return typeEnv;
        } else throw new MyException("The conditional expression is not boolean");
    }

    @Override
    public String toString() {
        return "while(" + exp + ") do ( " +stmt + " )";
    }
}
