package Model.BooleanExpressions;

import Model.Expressions.ExpressionInterface;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;

public class GeaterOrEq implements ExpressionInterface {


    ExpressionInterface left,right;


    public GeaterOrEq(ExpressionInterface left, ExpressionInterface right) {
        this.left = left;
        this.right = right;
    }


    @Override
    public int evaluate(IMyDictionary<String, Integer> dic, IMyHeap<Integer, Integer> heap) {
        if(this.left.evaluate(dic,heap) >= right.evaluate(dic,heap))
            return 1;
        else
            return 0;
    }


    @Override
    public String toString() {
        return "GreaterOrEq(" + left + ">=" + right + ") ";
    }

}
