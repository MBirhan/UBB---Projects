package Model.Expressions;

import Model.Utils.IMyDictionary;


public interface ExpressionInterface {

    int evaluate(IMyDictionary<String, Integer> dic);
}
