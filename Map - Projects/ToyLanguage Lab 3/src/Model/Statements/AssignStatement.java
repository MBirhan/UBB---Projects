package Model.Statements;

import Model.ProgramState;
import Model.Expressions.ExpressionInterface;
import Model.Utils.IMyDictionary;


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
        int val = expr.evaluate(dic);
        dic.setValue(id, val);
        return null;
    }


    @Override
    public String toString(){
        return id + "=" + expr + "; ";
    }
}

