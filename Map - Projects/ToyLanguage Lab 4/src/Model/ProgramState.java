package Model;

import Model.FileHandeler.FileData;
import Model.FileHandeler.IFileTable;
import Model.Statements.Statement;
import Model.Utils.*;

public class ProgramState {
    private IMyStack<Statement> stack;
    private IMyDictionary<String, Integer> SymTable;
    private IMyList<Integer> list;
    private IFileTable<Integer,FileData> fileTable;
    private IMyHeap<Integer, Integer> heap;



    public ProgramState(IMyStack<Statement> stack, IMyDictionary<String, Integer> symTable, IMyList<Integer> list, Statement s,
                        IFileTable<Integer,FileData> _fileTable, IMyHeap<Integer, Integer> _heap) {
        this.stack = stack;
        SymTable = symTable;
        this.list = list;
        this.fileTable = _fileTable;
        this.heap = _heap;
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

    public void setHeap(IMyHeap<Integer, Integer> heap) { this.heap = heap; }


    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();

        bld.append("\nExeStack\n");
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
            bld.append("").append(i).append(fileTable.get(i).getFileName()).append("\n");

        bld.append("\nHeap:\n");
        for(Integer key: heap.getAll())
            bld.append("").append(key).append(heap.get(key)).append("\n");

        bld.append("\n");

        return bld.toString();
    }


}
