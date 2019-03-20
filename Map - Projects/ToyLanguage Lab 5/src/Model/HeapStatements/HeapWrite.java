package Model.HeapStatements;

import Exceptions.UndefinedVariable;
import Model.Expressions.ExpressionInterface;
import Model.ProgramState;
import Model.Statements.Statement;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;

public class HeapWrite implements Statement {

    String varName;
    ExpressionInterface exp;

    public HeapWrite(String varName, ExpressionInterface exp) {
        this.varName = varName;
        this.exp = exp;
    }


    @Override
    public ProgramState execute(ProgramState state) {
        IMyDictionary<String,Integer> dict = state.getSymTable();
        IMyHeap<Integer,Integer> heap = state.getHeap();

        if(!dict.exists(varName))
            throw new UndefinedVariable("This key is not in our symTable");

        int heapID = dict.getValue(varName);

        if(!heap.contains(heapID))
            throw new UndefinedVariable("This key is not in our heapTable");

        int val = exp.evaluate(dict, heap);
        heap.update(heapID, val);

        return state;
    }


    @Override
    public String toString() {
        return "heapWriting("+varName+","+exp+")";
    }


}
