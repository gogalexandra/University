package Repository;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Utils.MyIList;
import Model.Utils.MyList;

import java.util.List;

public interface IRepo {
    //void addPrg(PrgState newPrg);
    public List<PrgState> getPrgList();
    void setPrgList(List<PrgState> prg);
    void logPrgStateExec(PrgState prg) throws MyException;

    PrgState getProgramStateWithId(int currentId);
}
