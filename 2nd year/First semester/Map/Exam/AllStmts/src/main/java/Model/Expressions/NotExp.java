package Model.Expressions;

import Model.Exceptions.MyException;
import Model.Statements.IStmt;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIStack;
import Model.Values.BoolValue;
import Model.Values.Value;

public class NotExp implements Exp{
    private Exp exp;

    public NotExp(Exp exp){
        this.exp = exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        RelationalExp re = (RelationalExp) this.exp;
        String newOp = "";
        switch (re.getOp()) {
            case "<":
                newOp = ">=";
            case "<=":
                newOp = ">";
            case "==":
                newOp = "!=";
            case "!=":
                newOp = "==";
            case ">":
                newOp = "<=";
            case ">=":
                newOp = "<";
        }
        RelationalExp expRez = new RelationalExp(re.getE1(), re.getE2(), newOp);
        return expRez.eval(tbl, hp);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    public String toString() {
        return "!(" + this.exp.toString() + ")";
    }
}
