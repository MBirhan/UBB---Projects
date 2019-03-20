package Model.Statements;

import Model.ProgramState;
import Model.Utils.*;

public class CompoundStatement implements Statement {

    private  Statement first, second;


    public CompoundStatement(Statement f, Statement s){
        first = f;
        second = s;
    }


    @Override
    public ProgramState execute(ProgramState state) {
        IMyStack<Statement> stack = state.getStack();
        stack.add(second);
        stack.add(first);

        return null;
    }


    @Override
    public String toString(){
        return "[" + first + " , " + second + "]";
    }
}
