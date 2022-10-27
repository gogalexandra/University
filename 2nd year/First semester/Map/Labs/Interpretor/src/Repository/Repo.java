package Repository;

import Model.PrgState;
import Model.Utils.MyList;

public class Repo implements IRepo {

    MyList<PrgState> myPrgStates;

    public Repo(){
        this.myPrgStates = new MyList<PrgState>();
    }

    public MyList<PrgState> getPrgStates() {
        return myPrgStates;
    }

    @Override
    public void addPrg(PrgState newPrg) {
        this.myPrgStates.add(newPrg);
    }

    @Override
    public PrgState getCrtPrg() {
        return this.myPrgStates.pop();
    }
}
