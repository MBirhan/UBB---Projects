package Model.Expressions;

import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;


public class ConstantExpressions implements ExpressionInterface{

    private int number;

    public ConstantExpressions(int nr){

        number = nr;
    }

    @Override
    public int evaluate(IMyDictionary<String, Integer> dic, IMyHeap<Integer, Integer> heap) {
        return number;
    }

    @Override
    public String toString(){
        return "" + number;
    }
}
