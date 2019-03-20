package Model.Expression;

import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;

public class NotExpression implements ExpressionInterface {

    private ExpressionInterface expr;

    public NotExpression(ExpressionInterface expr) {
        this.expr = expr;
    }

    @Override
    public int evaluate(DictionaryInterface<String, Integer> dic, HeapInterface<Integer, Integer> heap) {
        int val = expr.evaluate(dic, heap);

        if(val > 0)
            return 0;

        return 1;
    }

    @Override
    public String toString(){
        return "!" + expr;
    }
}
