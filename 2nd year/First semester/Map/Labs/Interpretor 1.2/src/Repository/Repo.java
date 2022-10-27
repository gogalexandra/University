package Repository;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Utils.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Repo implements IRepo {

    private PrgState myPrgState;
    private String filePath;
    private PrintWriter printWriter;

    public Repo(PrgState prg, String filePath){
        this.myPrgState = prg;
        this.filePath = filePath;
    }

    public PrgState getCrtPrg() {
        return myPrgState;
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException {
        try {
            this.printWriter = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath, true)));
            printWriter.println(prg.toString());
            printWriter.close();
        } catch (java.io.IOException e) {
            System.out.println(e.toString());
        }
    }

//    @Override
//    public void addPrg(PrgState newPrg) {
//        this.myPrgStates.add(newPrg);
//    }
//
//    @Override
//    public PrgState getCrtPrg() {
//        return this.myPrgStates.pop();
//    }
}
