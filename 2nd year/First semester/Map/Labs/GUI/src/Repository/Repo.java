package Repository;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Utils.MyIList;
import Model.Utils.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Repo implements IRepo {

    private List<PrgState> myPrgStates;
    private String filePath;
    private PrintWriter printWriter;

    public Repo(List<PrgState> prg, String filePath){
        this.myPrgStates = prg;
        this.filePath = filePath;
    }


    public List<PrgState> getPrgList(){ return this.myPrgStates; }

    public void setPrgList(List<PrgState> prg){
        this.myPrgStates = prg;
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
}
