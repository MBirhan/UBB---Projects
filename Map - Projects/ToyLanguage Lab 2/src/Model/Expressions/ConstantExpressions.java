package Model.Expressions;

import Model.Utils.IMyDictionary;


public class ConstantExpressions implements ExpressionInterface{

    private int number;

    public ConstantExpressions(int nr){

        number = nr;
    }

    @Override
    public int evaluate(IMyDictionary<String, Integer> dic) {
        return number;
    }

    @Override
    public String toString(){
        return "" + number;
    }
}
