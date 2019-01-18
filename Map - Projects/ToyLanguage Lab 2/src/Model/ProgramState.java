package Model;

import Model.Statements.Statement;
import Model.Utils.*;

public class ProgramState {
    private IMyStack<Statement> stack;
    private IMyDictionary<String, Integer> SymTable;
    private IMyList<Integer> list;


    public ProgramState(IMyStack<Statement> stack, IMyDictionary<String, Integer> symTable, IMyList<Integer> list) {
        this.stack = stack;
        SymTable = symTable;
        this.list = list;
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

        return bld.toString();
    }
}
