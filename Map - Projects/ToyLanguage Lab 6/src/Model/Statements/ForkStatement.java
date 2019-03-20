package Model.Statements;

import Model.ProgramState;
import Model.FileHandeler.FileData;
import Model.FileHandeler.IFileTable;
import Model.Utils.*;

public class ForkStatement implements Statement{

    Statement stmt;


    public ForkStatement(Statement _stmt){
        stmt = _stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IMyStack<Statement> stack2 = new MyStack<>();
        IMyDictionary<String,Integer> dict2 = state.getSymTable().copy();
        IMyList<Integer> list2 = state.getList();
        IMyHeap<Integer,Integer> heap2 = state.getHeap();
        IFileTable<Integer,FileData> file2 = state.getFileTable();

        stack2.add(stmt);
        int ID = ForkID.getID();

        return new ProgramState(stack2,dict2,list2,null,file2,heap2,ID);
    }

    @Override
    public String toString(){
        return "fork("+stmt+")";
    }
}
