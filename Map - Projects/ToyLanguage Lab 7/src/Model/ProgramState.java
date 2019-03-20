package Model;

import Exceptions.EmptyStack;
import Model.FileHandeler.FileData;
import Model.FileHandeler.IFileTable;
import Model.Statements.Statement;
import Model.Utils.*;

public class ProgramState {
    private IMyStack<Statement> stack;
    private IMyDictionary<String, Integer> SymTable;
    private IMyList<Integer> list;
    private Statement root;
    private IFileTable<Integer,FileData> fileTable;
    private IMyHeap<Integer, Integer> heap;
    private Integer ID;



    public ProgramState(IMyStack<Statement> stack, IMyDictionary<String, Integer> symTable, IMyList<Integer> list, Statement s,
                        IFileTable<Integer,FileData> _fileTable, IMyHeap<Integer, Integer> _heap, Integer _ID) {
        this.stack = stack;
        SymTable = symTable;
        this.list = list;
        this.root = s;
        this.fileTable = _fileTable;
        this.heap = _heap;
        this.ID = _ID;
    }

    public IMyStack<Statement> getStack() {
        return stack;
    }

    public void setStack(IMyStack<Statement> stack) {
        this.stack = stack;
    }

    public IMyDictionary<String, Integer> getSymTable() {
        return SymTable;
    }

    public void setSymTable(IMyDictionary<String, Integer> symTable) {
        SymTable = symTable;
    }

    public IMyList<Integer> getList() {
        return list;
    }

    public void setList(IMyList<Integer> list) {
        this.list = list;
    }

    public IFileTable<Integer, FileData> getFileTable() { return fileTable; }

    public void setFileTable(IFileTable<Integer, FileData> fileTable) { this.fileTable = fileTable; }

    public IMyHeap<Integer, Integer> getHeap() { return heap; }

    public Integer getID(){ return ID; }

    public void setID(Integer _ID){ ID = _ID; }

    public void setHeap(IMyHeap<Integer, Integer> heap) { this.heap = heap; }


    public boolean isNotCompleted(){
        return !stack.isEmpty();
    }


    public ProgramState oneStep(){

        if(stack.isEmpty())
            return null;

        Statement st = stack.pop();

        return st.execute(this);
    }


    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();

        bld.append("\nExeStack: \n").append(this.ID).append("\n");
        for(Statement s : stack.getElements()) {
            bld.append(s).append("\n");
        }

        bld.append("\nSymTable\n");
        for(String key : SymTable.getElements()){
            bld.append(key).append(" : ").append(SymTable.getValue(key)).append("\n");
        }

        bld.append("\nOut:\n");
        for(Integer i : list.getElements()){
            bld.append(i).append("\n");
        }

        bld.append("\nFileTable:\n");
        for(Integer i: fileTable.getAllKeys())
            bld.append(i).append(fileTable.get(i).getFileName()).append("\n");

        bld.append("\nHeap:\n");
        for(Integer key: heap.getAll())
            bld.append(key).append(heap.get(key)).append("\n");

        bld.append("\n");

        return bld.toString();
    }


}
