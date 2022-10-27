package Model.Statements;

import Model.PrgState;

import Model.Exceptions.MyException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, IOException;
}
