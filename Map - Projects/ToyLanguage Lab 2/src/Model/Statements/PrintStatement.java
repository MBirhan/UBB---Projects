package Model.Statements;

import Model.Expressions.ExpressionInterface;
import Model.Utils.*;
import Model.ProgramState;

public class PrintStatement implements Statement{

    private ExpressionInterface expr;

    public PrintStatement(ExpressionInterface expression){
        expr = expression;
    }

    @Override
    public ProgramState execute(ProgramState state){
        IMyList<Integer> list = state.getList();
        IMyDictionary<String, Integer> dic = state.getSymTable();
        int val = expr.evaluate(dic);
        list.add(val);
        return null;
    }

    @Override
    public String toString(){
        return "print(" + expr + ");";
    }
}

