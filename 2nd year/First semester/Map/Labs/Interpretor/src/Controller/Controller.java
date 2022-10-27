package Controller;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Utils.MyIStack;
import Repository.IRepo;

public class Controller {
    private IRepo repo;

    public Controller(IRepo repo){
        this.repo = repo;
    }

    public void addProgram(PrgState newPrg) {
        repo.addPrg(newPrg);
    }

    public PrgState getPrgState(int index){
        return  repo.getPrgStates().get(index);
    }

    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        if (stk.isEmpty())
            throw new MyException("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep(PrgState prg){
        //PrgState prg = repo.getCrtPrg(); // repo is the controller field of type MyRepoInterface
        //displayCrtPrg();
        displayPrg(prg);
        while (!prg.getStk().isEmpty()){
            oneStep(prg);
            //here you can display the prg state
            //displayCrtPrg();
            displayPrg(prg);
        }
    }

    public void displayCrtPrg(){
        System.out.print(repo.getCrtPrg().toString());
    }
    public void displayPrg(PrgState prg) {System.out.print(prg.toString()); }
}


