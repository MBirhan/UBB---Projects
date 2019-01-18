package Model.Expressions;

import Model.Utils.IMyDictionary;


public class VariableExpressions  implements ExpressionInterface{

    String id;

    public VariableExpressions(String key){
        id = key;
    }

    @Override
    public int evaluate(IMyDictionary<String, Integer> dic) {
        return dic.getValue(id);
    }

    @Override
    public String toString(){
        return id;
    }
}