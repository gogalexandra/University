package Repository;

import Model.PrgState;
import Model.Utils.MyList;

public interface IRepo {
    void addPrg(PrgState newPrg);
    PrgState getCrtPrg();
    public MyList<PrgState> getPrgStates();
}
