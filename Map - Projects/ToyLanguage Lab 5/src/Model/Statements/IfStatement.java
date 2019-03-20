package Model.Statements;

import Model.ProgramState;
import Model.Expressions.ExpressionInterface;
import Model.Utils.*;

public class IfStatement implements Statement{
    private ExpressionInterface expr;
    private Statement thenStmt;
    private Statement elseStmt;

    public IfStatement(ExpressionInterface expression, Statement thenS,  Statement elseS){
        expr = expression;
        thenStmt = thenS;
        elseStmt = elseS;
    }

    public ProgramState execute(ProgramState state){
        IMyDictionary<String, Integer> dic = state.getSymTable();
        IMyStack<Statement> stack = state.getStack();
        IMyHeap<Integer, Integer> heap = state.getHeap();
        int val = expr.evaluate(dic,heap);

        if(val != 0)
            stack.add(thenStmt);
        else
            stack.add(elseStmt);
        return state;
    }

    @Override
    public String toString(){
        return "if(" + expr + ") then(" + thenStmt +  ")else(" + elseStmt + ") ;";
    }
}

