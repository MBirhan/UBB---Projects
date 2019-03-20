package Model.HeapStatements;

import Model.*;
import Model.Expressions.ExpressionInterface;
import Model.Statements.Statement;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;

public class NewStatement implements Statement{


    String varName;
    ExpressionInterface exp;


    public NewStatement(String varName, ExpressionInterface exp) {
        this.varName = varName;
        this.exp = exp;
    }


    @Override
    public ProgramState execute(ProgramState state) {

        IMyDictionary<String,Integer> dict = state.getSymTable();
        IMyHeap<Integer,Integer> heap = state.getHeap();

        int id = HeapGeneratorInteger.genID();
        int val = exp.evaluate(dict,heap);

        heap.add(id, val);
        dict.setValue(varName, id);
        return state;
    }


    @Override
    public String toString() { return "NewH(" + varName + ", " + exp + ")"; }
}
