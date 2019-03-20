package Model.BooleanExpressions;

import Exceptions.DivisionBy0;
import Exceptions.InvalidOperator;
import Model.Expressions.ExpressionInterface;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;

public class BoolExp implements ExpressionInterface {

    private ExpressionInterface left,right;
    private String operator;


    public BoolExp(String operator, ExpressionInterface left, ExpressionInterface right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public int evaluate(IMyDictionary<String, Integer> dic, IMyHeap<Integer, Integer> heap) {
        int left = this.left.evaluate(dic, heap);
        int right = this.right.evaluate(dic, heap);

        switch (operator){
            case "<":
                if(left < right)
                    return 1;
                else
                    return 0;
            case "<=":
                if(left <= right)
                    return 1;
                else
                    return 0;
            case ">":
                if(left > right)
                    return 1;
                else
                    return 0;
            case ">=":
                if(left >= right)
                    return 1;
                else
                    return 0;
            case "!=":
                if(left != right)
                    return 1;
                else
                    return 0;
            case "==":
                if(left == right)
                    return 1;
                else
                    return 0;
        }
        throw new InvalidOperator("Invalid operator!");
    }

    @Override
    public String toString() {
        return "BoolExp(" + left + operator + right + ") ";
    }
}
