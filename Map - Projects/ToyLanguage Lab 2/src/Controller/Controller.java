package Controller;

import Model.ProgramState;
import Model.Statements.Statement;
import Model.Utils.IMyStack;
import Repository.*;
import Exceptions.EmptyStack;


public class Controller {

    private IRepository repo;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public ProgramState oneStep(ProgramState state){
        IMyStack<Statement> stack = state.getStack();
        if(stack.isEmpty())
            throw new EmptyStack("The stack is empty!");
        Statement currentStmt = stack.pop();
        return currentStmt.execute(state);
    }

    public void allSteps(){
        ProgramState prg = repo.getCurrentPrg();
        while(!prg.getStack().isEmpty()){
            oneStep(prg);
            System.out.println("/////////////");
            System.out.println(prg);
        }
    }

    @Override
    public String toString(){
        return "Controller: " + repo.toString();
    }
}
