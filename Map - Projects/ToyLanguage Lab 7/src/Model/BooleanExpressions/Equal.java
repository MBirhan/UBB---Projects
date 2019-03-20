package Model.BooleanExpressions;

import Model.Expressions.ExpressionInterface;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;

public class Equal implements ExpressionInterface {

    ExpressionInterface left,right;


    public Equal(ExpressionInterface _left, ExpressionInterface _right) {

        this.left = _left;
        this.right = _right;
    }


    @Override
    public int evaluate(IMyDictionary<String, Integer> dic, IMyHeap<Integer, Integer> heap) {
        if(left.evaluate(dic,heap) == right.evaluate(dic,heap))
            return 1;
        else
            return 0;
    }


    @Override
    public String toString() {
        return "Equal(" + left + "==" + right + ") ";
    }
}
