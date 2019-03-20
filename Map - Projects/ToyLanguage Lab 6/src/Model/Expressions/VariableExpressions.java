package Model.Expressions;

import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;


public class VariableExpressions  implements ExpressionInterface{

    String id;

    public VariableExpressions(String key){
        id = key;
    }

    @Override
    public int evaluate(IMyDictionary<String, Integer> dic, IMyHeap<Integer,Integer> heap) {
        return dic.getValue(id);
    }

    @Override
    public String toString(){
        return id;
    }
}