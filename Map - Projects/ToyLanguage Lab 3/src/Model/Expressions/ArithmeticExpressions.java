package Model.Expressions;

import Model.Utils.IMyDictionary;
import Exceptions.*;

public class ArithmeticExpressions implements ExpressionInterface {

    private ExpressionInterface left, right;
    private String operator;

    public ArithmeticExpressions(String op, ExpressionInterface l, ExpressionInterface r){
        operator = op;
        left = l;
        right = r;
    }

    @Override
    public int evaluate(IMyDictionary<String, Integer> dic) {
        int left = this.left.evaluate(dic);
        int right = this.right.evaluate(dic);

        switch (operator){
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                if(right == 0)
                    throw new DivisionBy0("Division by 0 is not permited!");
                return left/right;
        }
        throw new InvalidOperator("Invalid operator!");
    }

    @Override
    public String toString(){
        return "" + left + operator + right;
    }
}
