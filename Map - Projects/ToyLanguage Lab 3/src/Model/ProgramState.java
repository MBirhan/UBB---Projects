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



    public ProgramState(IMyStack<Statement> stack, IMyDictionary<String, Integer> symTable, IMyList<Integer> list, Statement s,
                        IFileTable<Integer,FileData> _fileTable) {
        this.stack = stack;
        SymTable = symTable;
        this.list = list;
        this.fileTable = _fileTable;
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

        return bld.toString();
    }
}
