package Model.HeapStatements;

import Exceptions.UndefinedVariable;
import Model.Expressions.ExpressionInterface;
import Model.Statements.Statement;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;

public class HeapRead implements ExpressionInterface{


    String varName;


    public HeapRead(String varName) {
        this.varName = varName;
    }


    @Override
    public int evaluate(IMyDictionary<String, Integer> dict, IMyHeap<Integer, Integer> heap) {

        if(!dict.exists(varName)) {
            throw new UndefinedVariable("This key is not in our symTable");
        }

        int heapID = dict.getValue(varName);

        if(!heap.contains(heapID)) {
            throw new UndefinedVariable("This key is not in our heapTable");
        }

        return  heap.get(heapID);
    }


    @Override
    public String toString() { return "heapReading(" + varName +")"; }
}
