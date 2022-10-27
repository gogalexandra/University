package Controller;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Utils.MyIStack;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepo;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller {
    private IRepo repo;

    public Controller(IRepo repo){
        this.repo = repo;
    }

    public PrgState oneStep(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk = state.getStk();
        if (stk.isEmpty())
            throw new MyException("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public Set<Integer> getAddressesFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream().filter(v -> v instanceof RefValue)
                .map( v ->{ return ((RefValue)v).getAddress();})
                .collect(Collectors.toSet());
    }

    public Map<Integer, Value> garbageCollector(Set<Integer> usedSymbolTableAddresses, Map<Integer, Value> heap) {
        Map<Integer, Value> newHeap = heap.entrySet().stream()
                .filter(v -> usedSymbolTableAddresses.contains(v.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return newHeap;
    }

    public void allStep() throws IOException {
        PrgState prg = repo.getCrtPrg();
        repo.logPrgStateExec(prg);
        while (!prg.getStk().isEmpty()){
            oneStep(prg);
            repo.logPrgStateExec(prg);
            prg.getHeap().setContent(garbageCollector(getAddressesFromSymTable(prg.getSymTable().values()
            ), prg.getHeap().getContent()));
            repo.logPrgStateExec(prg);
        }
    }

    public void displayCrtPrg(){
        System.out.print(repo.getCrtPrg().toString());
    }
    public void displayPrg(PrgState prg) {System.out.print(prg.toString()); }
}


