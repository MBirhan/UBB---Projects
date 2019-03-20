package Model.Statements;

import Model.Expressions.ExpressionInterface;
import Model.ProgramState;
import Model.*;
import Model.Utils.IMyDictionary;
import Model.Utils.IMyHeap;
import Model.Utils.IMyStack;

public class WhileStatement implements Statement{

    ExpressionInterface exp;
    Statement statement;

    public WhileStatement(ExpressionInterface exp, Statement stm) {
        this.exp = exp;
        this.statement = stm;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        IMyStack<Statement> stack = state.getStack();
        IMyDictionary<String,Integer> dict = state.getSymTable();
        IMyHeap<Integer,Integer> heap = state.getHeap();

        int val = exp.evaluate(dict,heap);

        if(val != 0) {
            stack.add(new WhileStatement(exp, statement));
            stack.add(statement);
        }

        return state;
    }

    @Override
    public String toString(){
        return "While(" + exp + "){" + statement + "}";
    }
}
