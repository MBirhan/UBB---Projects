package Model.Statements;

import Model.ProgramState;
import Model.Expressions.ExpressionInterface;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;


public class AssignStatement implements Statement {
    private String id;
    private ExpressionInterface expr;


    public AssignStatement(String key, ExpressionInterface expression){
        id = key;
        expr = expression;
    }


    @Override
    public ProgramState execute(ProgramState state) {

        IMyDictionary<String, Integer> dic = state.getSymTable();
        IMyHeap<Integer,Integer> heap = state.getHeap();
        int val = expr.evaluate(dic,heap);
        dic.setValue(id, val);
        return state;
    }


    @Override
    public String toString(){
        return id + "=" + expr + "; ";
    }
}

