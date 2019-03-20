package Controller;

import Model.FileHandeler.FileData;
import Model.FileHandeler.IFileTable;
import Model.ProgramState;
import Model.Statements.Statement;
import Model.Utils.IMyStack;
import Repository.*;
import Exceptions.EmptyStack;
import Model.Utils.IMyHeap;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Controller {

    private IRepository repo;

    public Controller(IRepository _repo){
        repo = _repo;
    }

    public void executeOneStep(){

        ProgramState program = repo.getCurrentPrg();
        IMyStack<Statement> stack = program.getStack();
        if(stack.isEmpty())
            throw new EmptyStack("Stack is Empty\n");

        //repo.logPrgStateExec();
        Statement st = stack.pop();
        st.execute(program);

        program.getHeap().setContent(this.conservativeGarbageCollector(program.getSymTable().getAllValues(),program.getHeap()));

        repo.logPrgStateExec();
    }

    public void executeAll(){
        ProgramState state = repo.getCurrentPrg();
        IMyStack<Statement> stack = state.getStack();
        IFileTable<Integer, FileData> file = state.getFileTable();

        repo.logPrgStateExec();
        try {
            while (!stack.isEmpty())
                executeOneStep();
        }
        catch (EmptyStack e){
            System.out.println(e);
        }
        finally {
            file.getAllValues().stream().forEach(e-> {
                try {
                    e.getReader().close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    private Map<Integer,Integer> conservativeGarbageCollector(List<Integer> symTableValues, IMyHeap<Integer,Integer> heap){

        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }
}
