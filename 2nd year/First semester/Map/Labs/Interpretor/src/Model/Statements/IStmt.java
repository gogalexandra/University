package Model.Statements;

import Model.PrgState;

import Model.Exceptions.MyException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
}
