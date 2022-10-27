package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIStack;
import Model.Values.Value;

public class IfStmt implements IStmt{
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el){
        this.exp = e;
        this.thenS = t;
        this.elseS = el;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        Value cond = exp.eval(symTbl, state.getHeap());
        if (cond.getType().equals(new BoolType()))
            stk.push(thenS);
        else
            stk.push(elseS);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck((MyDictionary<String, Type>) typeEnv.deepCopy());
            elseS.typecheck((MyDictionary<String, Type>) typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }

    @Override
    public String toString(){
        return "(if("+ exp.toString()+") then(" +thenS.toString() +")else("+elseS.toString()+"))";
    }
}
