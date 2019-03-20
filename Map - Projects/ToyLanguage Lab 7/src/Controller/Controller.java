package Controller;

import Exceptions.CtrlExcep;
import Model.ProgramState;
import Repository.*;
import Model.Utils.IMyHeap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;


public class Controller {

    private IRepository repo;
    private ExecutorService executor;


    public Controller(IRepository _repo){
        repo = _repo;
    }

    public List<ProgramState> removeCompletedPrg(List<ProgramState> l){
        return l.stream().filter(ProgramState::isNotCompleted)
                         .collect(Collectors.toList());
    }


    public void executeAll(List<ProgramState> list){

        List<Callable<ProgramState>> callList = list.stream()
                                                .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep))
                                                .collect(Collectors.toList());

        List<ProgramState> newList = null;

        try {
            newList = executor.invokeAll(callList)
                    .stream()
                    .map(  future ->
                    {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new CtrlExcep(e.getMessage());
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new CtrlExcep(e.getMessage());
        }
        list.forEach(prg-> repo.logPrgStateExec(prg));
        list.addAll(newList);
        repo.setPrgList(list);
    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(1);

        List<ProgramState> prgList = removeCompletedPrg(repo.getPrgList());

        prgList.forEach(prg->repo.logPrgStateExec(prg));

        while(prgList.size() > 0){
            executeAll(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());

        }
        executor.shutdownNow();

        prgList.forEach(p -> { p.getFileTable().getAllValues().forEach(e->{
                try{
                    e.getReader().close();
                }
                catch(IOException e1){
                    e1.printStackTrace();
                }
            });
        });

        repo.setPrgList(prgList);
    }



    private Map<Integer,Integer> conservativeGarbageCollector(List<Integer> symTableValues, IMyHeap<Integer,Integer> heap){

        return heap.entrySet().stream()
                .filter(e->symTableValues.  contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }
}
