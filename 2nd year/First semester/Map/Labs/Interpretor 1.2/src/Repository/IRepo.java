package Repository;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Utils.MyList;

public interface IRepo {
    //void addPrg(PrgState newPrg);
    PrgState getCrtPrg();
    //public MyList<PrgState> getPrgStates();
    void logPrgStateExec(PrgState prg) throws MyException;
}
