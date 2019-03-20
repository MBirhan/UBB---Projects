package Model.Statements;

import Model.Expression.BooleanExpression;
import Model.Expression.ExpressionInterface;
import Model.ProgramState;
import Model.Utils.DictionaryInterface;
import Model.Utils.HeapInterface;
import Model.Utils.StackInterface;
import javafx.beans.binding.IntegerExpression;

public class SwitchStmt implements StmtInterface {
    private ExpressionInterface condition;
    private ExpressionInterface case1;
    private StmtInterface statement1;
    private ExpressionInterface case2;
    private StmtInterface statement2;
    private StmtInterface statement3;

    public SwitchStmt(ExpressionInterface condition, ExpressionInterface case1, StmtInterface statement1, ExpressionInterface case2, StmtInterface statement2, StmtInterface statement3){
        this.condition = condition;
        this.case1 = case1;
        this.case2 = case2;
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        StackInterface<StmtInterface> executionStack = state.getStack();
        DictionaryInterface<String, Integer> symbolTable = state.getSymTable();
        HeapInterface<Integer, Integer> heapTable = state.getHeap();

        Integer exp = condition.evaluate(symbolTable, heapTable);
        Integer exp1 = case1.evaluate(symbolTable, heapTable);
        Integer exp2 = case2.evaluate(symbolTable, heapTable);
        StmtInterface newStatement;
        if(exp.equals(exp1))
            newStatement = statement1;
        else if(exp.equals(exp2))
            newStatement = statement2;
        else
            newStatement = statement3;
        executionStack.add(newStatement);
        state.setStack(executionStack);
        return null;
    }

    @Override
    public String toString(){
        return "switch( " + condition.toString() + ") \n(case( " + case1.toString() + " ) " + statement1.toString() + ")\n(case( " + case2.toString() + " ) " + statement2.toString() + ")\n(default " + statement3.toString() + " )";
    }
}
