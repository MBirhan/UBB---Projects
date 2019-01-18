package Model.Statements;

import Model.ProgramState;
import Model.Utils.*;

public class CompareStatement implements Statement {

    private  Statement first, second;


    public CompareStatement(Statement f, Statement s){
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
