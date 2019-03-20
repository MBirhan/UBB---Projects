package Model.Expressions;

import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;


public interface ExpressionInterface {

    int evaluate(IMyDictionary<String, Integer> dic, IMyHeap<Integer, Integer> heap);
}
