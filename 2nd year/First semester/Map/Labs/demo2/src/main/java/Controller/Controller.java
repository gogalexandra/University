package Controller;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Utils.MyIList;
import Model.Utils.MyIStack;
import Model.Utils.MyList;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepo;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepo repo;
    private ExecutorService executor;

    public Controller(IRepo repo){
        this.repo = repo;
    }

    public IRepo getRepo(){return this.repo;}

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws Exception{

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> { return p.oneStep(); }))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try { return future.get(); }
                    catch (Exception e) {
                        return null;
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);
        for(PrgState prg : prgList) {
            repo.logPrgStateExec(prg);
        }

        MyIList<PrgState> copyPrgList = new MyList<>();
        //copyPrgList.add(prgList);
        repo.setPrgList(prgList);
    }

    public void allStep() throws Exception{
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

        while (prgList.size() > 0) {
//            for (PrgState prg : prgList) {
//                prg.getHeap().setContent(GarbageCollector.safeGarbageCollector(
//                        GarbageCollector.getAddrFromTable(prg.getSymTable().values()),
//                        prg.getHeapTable().getContent()));
//            }
            oneStepForAllPrg(prgList);

            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();

        MyIList<PrgState> copyPrgList = new MyList<>();
        //copyPrgList.add(prgList);
        repo.setPrgList(prgList);
    }

    public void executeOneStep() throws Exception {
        executor = Executors.newFixedThreadPool(8);
        removeCompletedPrg(repo.getPrgList());
        List<PrgState> programStates = repo.getPrgList();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAllPrg(repo.getPrgList());
            } catch (InterruptedException e) {
                System.out.println();
            }
            programStates.forEach(e -> {
                try {
                    repo.logPrgStateExec(e);
                } catch (Exception e1) {
                    System.out.println();
                }
            });
            removeCompletedPrg(repo.getPrgList());
            executor.shutdownNow();
        }
    }
//    public Set<Integer> getAddressesFromSymTable(Collection<Value> symTableValues){
//        return symTableValues.stream().filter(v -> v instanceof RefValue)
//                .map( v ->{ return ((RefValue)v).getAddress();})
//                .collect(Collectors.toSet());
//    }
//
//    public Map<Integer, Value> garbageCollector(Set<Integer> usedSymbolTableAddresses, Map<Integer, Value> heap) {
//        Map<Integer, Value> newHeap = heap.entrySet().stream()
//                .filter(v -> usedSymbolTableAddresses.contains(v.getKey()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//        return newHeap;
//    }
//
//    public void allStep() throws InterruptedException {
//        executor = Executors.newFixedThreadPool(2);
//        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
//        while(prgList.size() > 0){
//            oneStepForAllPrograms(prgList);
//            prgList = removeCompletedPrg(repo.getPrgList());
//        }
//        executor.shutdownNow();
//        repo.setPrgList(prgList);
//    }
//
//    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
//        return inPrgList.stream()
//            .filter(PrgState::isNotCompleted)
//            .collect(Collectors.toList()); }
//
//    public void displayPrg(PrgState prg) {System.out.print(prg.toString()); }
//
//
//    public void oneStepForAllPrograms(List<PrgState> prgList) throws InterruptedException {
//
//        prgList.forEach(prg ->repo.logPrgStateExec(prg));
//        List<Callable<PrgState>> callList = prgList.stream()
//                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
//                .collect(Collectors.toList());
//        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
//                .map(future -> {
//                    try {
//                        return future.get();
//                    } catch (Exception e) {
//                        System.out.println(e);
//                    }
//
//                    return null;
//                })
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//        prgList.addAll(newPrgList);
//        //prgList.forEach(prg ->repo.logPrgStateExec(prg));
//        for(PrgState prg : prgList) {
//            repo.logPrgStateExec(prg);
//        }
//        repo.setPrgList(prgList);
//    }
}


