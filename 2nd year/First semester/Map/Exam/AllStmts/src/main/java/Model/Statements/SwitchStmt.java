package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Expressions.RelationalExp;
import Model.PrgState;
import Model.Types.Type;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIStack;
import Model.Values.Value;

import java.io.IOException;

public class SwitchStmt implements IStmt{
    private Exp exp1;
    private Exp exp2;
    private Exp exp3;
    private IStmt case1;
    private IStmt case2;
    private IStmt case3;


    public SwitchStmt(Exp exp1, Exp exp2, Exp exp3, IStmt iStmt1, IStmt iStmt2, IStmt iStmt3){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.case1 = iStmt1;
        this.case2 = iStmt2;
        this.case3 = iStmt3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symbTbl = state.getSymTable();
        MyIHeap<Value> heapTable = state.getHeap();
        MyIStack<IStmt> executionStack = state.getStk();
        Value exp1 = this.exp1.eval(symbTbl, heapTable);
        Value exp2 = this.exp2.eval(symbTbl, heapTable);
        Value exp3 = this.exp3.eval(symbTbl, heapTable);
        IStmt newStatement;
        if (exp1.equals(exp1)) {
            newStatement = this.case1;
        } else if (exp2.equals(exp3)) {
            newStatement = this.case2;
        } else {
            newStatement = this.case3;
        }
        executionStack.push(newStatement);
 //       state.setExeStack(executionStack);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    public String toString() {
        return "switch( " + this.exp1.toString() + ") \n(case( " + this.exp2.toString() + " ) " + this.case1.toString() + ")\n(case( " + this.exp3.toString() + " ) " + this.case2.toString() + ")\n(default " + this.case3.toString() + " )";
    }
}
